package action.in.blog.controller;

import action.in.blog.domain.AgeRangeFilter;
import action.in.blog.domain.Customer;
import action.in.blog.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping
    public Flux<Customer> customersAll() {
        return customerService.allCustomers();
    }

    @QueryMapping
    public Mono<Customer> customerById(@Argument Integer id) {
        return customerService.customersById(id);
    }

    @QueryMapping
    public Flux<Customer> customersNameContains(@Argument String name) {
        return customerService.customersNameContains(name);
    }

    @QueryMapping
    public Flux<Customer> customersByAgeRange(@Argument AgeRangeFilter filter) {
        return customerService.customersByAgeRange(filter);
    }
}
