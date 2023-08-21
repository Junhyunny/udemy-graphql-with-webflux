package action.in.blog.service;

import action.in.blog.domain.AgeRangeFilter;
import action.in.blog.domain.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final Flux<Customer> flux = Flux.just(
            Customer.create(1, "Sam", 12, "Atlanta"),
            Customer.create(2, "Jake", 15, "Las vegas"),
            Customer.create(3, "Mike", 17, "Miami"),
            Customer.create(4, "John", 20, "Atlanta")
    );

    public Flux<Customer> allCustomers() {
        return flux;
    }

    public Mono<Customer> customersById(Integer id) {
        return flux
                .filter(customer -> customer.getId().equals(id))
                .next();
    }

    public Flux<Customer> customersNameContains(String name) {
        return flux
                .filter(customer -> customer.getName().contains(name));
    }

    public Flux<Customer> customersByAgeRange(AgeRangeFilter filter) {
        return flux
                .filter(filter::contains);
    }
}
