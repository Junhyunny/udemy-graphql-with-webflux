package action.in.blog.service;

import action.in.blog.client.CustomerClient;
import action.in.blog.client.SubscribeClient;
import action.in.blog.domain.CustomerDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class CustomerService implements CommandLineRunner {

    private final CustomerClient customerClient;
    private final SubscribeClient subscribeClient;

    public CustomerService(CustomerClient customerClient, SubscribeClient subscribeClient) {
        this.customerClient = customerClient;
        this.subscribeClient = subscribeClient;
    }

    @Override
    public void run(String... args) {
        subscribeClient.customerEvents()
                .doOnNext(System.out::println)
                .subscribe();
        customers()
                .then(customerById(5))
                .then(createCustomer())
                .then(customerById(5))
                .then(updateCustomer(5))
                .then(customerById(5))
                .then(deleteCustomer(5))
                .then(customerById(5))
                .subscribe();
    }

    private Mono<Void> rawQuery() {
        String query = """
                {
                    myCustomer: customers {
                        id
                        name
                        age
                        city
                    }
                }
                """;

        var response = customerClient.rawQuery(query)
                .map(cr -> cr.field("myCustomer")
                        .toEntityList(CustomerDTO.class));

        return execute("rawQuery", response);
    }

    private Mono<Void> getCustomerById() {
        return execute("customerById", customerClient.getCustomerById(1));
    }

    private Mono<Void> customers() {
        return execute("customers", customerClient.allCustomers());
    }

    private Mono<Void> customerById(int id) {
        return execute("customerById", customerClient.customerById(id));
    }

    private Mono<Void> createCustomer() {
        return execute("createCustomer", customerClient.createCustomer(
                CustomerDTO.create(null, "Junhyunny", 32, "Seoul")
        ));
    }

    private Mono<Void> updateCustomer(int id) {
        return execute("updateCustomer", customerClient.updateCustomer(
                id,
                CustomerDTO.create(null, "Jua", 1, "Seoul")
        ));
    }

    private Mono<Void> deleteCustomer(int id) {
        return execute("deleteCustomer", customerClient.deleteCustomer(id));
    }

    private <T> Mono<Void> execute(String message, Mono<T> publisher) {
        return Mono.delay(Duration.ofSeconds(1))
                .doFirst(() -> System.out.println(message))
                .then(publisher)
                .doOnNext(System.out::println)
                .then()
                ;
    }
}
