package action.in.blog.controller;

import action.in.blog.domain.CustomerWithOrder;
import action.in.blog.service.CustomerService;
import action.in.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class CustomerRestController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @GetMapping("/customers")
    public Flux<CustomerWithOrder> customersOrders() {
        return customerService.customers()
                .flatMap(customer ->
                        orderService.orderByClauseName(customer.getName())
                                .collectList()
                                .map(orders -> CustomerWithOrder.create(
                                                customer.getId(),
                                                customer.getName(),
                                                customer.getAge(),
                                                customer.getCity(),
                                                orders
                                        )
                                )
                );
    }
}
