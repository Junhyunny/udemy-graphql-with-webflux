package action.in.blog.controller;

import action.in.blog.domain.Customer;
import action.in.blog.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping
    public Flux<Customer> customers(DataFetchingEnvironment environment) {
        System.out.printf(
                "customers: %s\n", environment.getDocument()
        );
        return customerService.customers();
    }
}
