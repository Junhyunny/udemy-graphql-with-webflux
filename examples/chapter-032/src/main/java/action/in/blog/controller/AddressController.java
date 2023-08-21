package action.in.blog.controller;

import action.in.blog.domain.Address;
import action.in.blog.domain.Customer;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {
    
    @SchemaMapping
    public Mono<Address> address(Customer customer) {
        return Mono.just(
                Address.create(customer.getName() + " street", customer.getName() + " city")
        );
    }
}
