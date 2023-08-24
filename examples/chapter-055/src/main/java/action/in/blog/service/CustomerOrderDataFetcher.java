package action.in.blog.service;

import action.in.blog.domain.CustomerWithOrder;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor
@Service
public class CustomerOrderDataFetcher implements DataFetcher<Flux<CustomerWithOrder>> {

    private final CustomerService customerService;
    private final OrderService orderService;

    private UnaryOperator<Flux<CustomerWithOrder>> updateOrdersTransformer(boolean includeOrders) {
        return includeOrders ? flux -> flux.flatMapSequential(this::fetchOrders) : flux -> flux;
    }

    private Mono<CustomerWithOrder> fetchOrders(CustomerWithOrder customerWithOrder) {
        return orderService.orderByClauseName(customerWithOrder.getName())
                .collectList()
                .doOnNext(customerWithOrder::setOrders)
                .thenReturn(customerWithOrder);
    }

    @Override
    public Flux<CustomerWithOrder> get(DataFetchingEnvironment environment) throws Exception {
        var selectionSet = environment.getSelectionSet();
        var includeOrders = selectionSet.contains("orders");
        return customerService.customers()
                .map(customer -> CustomerWithOrder.create(
                        customer.getId(),
                        customer.getName(),
                        customer.getAge(),
                        customer.getCity(),
                        Collections.emptyList()
                ))
                .transform(updateOrdersTransformer(includeOrders));
    }
}
