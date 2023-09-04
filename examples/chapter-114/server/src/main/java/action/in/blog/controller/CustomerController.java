package action.in.blog.controller;

import action.in.blog.domain.CustomerDTO;
import action.in.blog.domain.CustomerNotFound;
import action.in.blog.domain.CustomerResponse;
import action.in.blog.domain.DeleteResponseDTO;
import action.in.blog.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@AllArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @QueryMapping
    public Flux<CustomerDTO> customers() {
        return customerService.allCustomers();
    }

    @QueryMapping
    public Mono<CustomerResponse> customerById(@Argument int id) {
        return customerService.customerById(id)
                .cast(CustomerResponse.class)
                .switchIfEmpty(Mono.just(CustomerNotFound.create(id)));
    }

    @MutationMapping
    public Mono<CustomerDTO> createCustomer(@Argument CustomerDTO customer) {
        return customerService.createCustomer(customer)
                .doOnNext(System.out::println)
                .delayElement(Duration.ofSeconds(1));
    }

    @MutationMapping
    public Mono<CustomerDTO> updateCustomer(@Argument int id, @Argument(name = "customer") CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO)
                .doOnNext(System.out::println)
                .delayElement(Duration.ofSeconds(2));
    }

    @MutationMapping
    public Mono<DeleteResponseDTO> deleteCustomer(@Argument int id) {
        return customerService.deleteCustomer(id);
    }
}
