package action.in.blog.controller;

import action.in.blog.domain.Customer;
import action.in.blog.domain.CustomerOrder;
import action.in.blog.service.CustomerService;
import action.in.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @QueryMapping
    public Flux<Customer> customers() {
        return customerService.customers();
    }

    @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrder> orders(Customer customer, @Argument Integer limit) {
        return orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }
}
