package action.in.blog.controller;

import action.in.blog.domain.Customer;
import action.in.blog.domain.CustomerOrder;
import action.in.blog.service.CustomerService;
import action.in.blog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @QueryMapping
    public Flux<Customer> customers() {
        return customerService.customers();
    }

//    @BatchMapping(typeName = "Customer")
//    public Flux<List<CustomerOrder>> orders(List<Customer> customers) {
//        return orderService.ordersByCustomerNames(
//                customers.stream()
//                        .map(Customer::getName)
//                        .toList()
//        );
//    }

    @BatchMapping(typeName = "Customer")
    public Mono<Map<Customer, List<CustomerOrder>>> orders(List<Customer> customers) {
        return orderService.fetchOrdersAsMap(customers);
    }
}
