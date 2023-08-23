package action.in.blog.controller;

import action.in.blog.domain.Address;
import action.in.blog.domain.Customer;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping
    public Mono<Address> address(Customer customer, DataFetchingEnvironment environment) {
        System.out.printf(
                "address: %s\n", environment.getDocument()
        );
        System.out.printf(
                "address operation definition: %s\n", environment.getOperationDefinition()
        );
        return Mono.just(
                Address.create(customer.getName() + " street", customer.getName() + " city")
        );
    }
}
