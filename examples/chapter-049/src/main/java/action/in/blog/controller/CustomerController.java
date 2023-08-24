package action.in.blog.controller;

import action.in.blog.domain.CustomerWithOrder;
import action.in.blog.service.CustomerOrderDataFetcher;
import action.in.blog.service.CustomerService;
import action.in.blog.service.OrderService;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final CustomerOrderDataFetcher dataFetcher;

//    @QueryMapping
//    public Flux<Customer> customers() {
//        return customerService.customers();
//    }
//
//    @SchemaMapping
//    public Flux<CustomerOrder> orders(Customer customer) {
//        return orderService.orderByClauseName(customer.getName());
//    }

    @QueryMapping
    public Flux<CustomerWithOrder> customers(DataFetchingFieldSelectionSet selectionSet) {
        return dataFetcher.customersOrders(selectionSet);
    }
}
