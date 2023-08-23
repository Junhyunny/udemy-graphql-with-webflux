package action.in.blog.controller;

import action.in.blog.domain.Account;
import action.in.blog.domain.Customer;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AccountController {

    @BatchMapping
    public Flux<Account> account(List<Customer> customers) {
        return Flux.fromIterable(customers)
                .map(customer -> {
                    var type = ThreadLocalRandom.current().nextBoolean() ? "CHECKING" : "SAVING";
                    return Account.create(
                            UUID.randomUUID(),
                            ThreadLocalRandom.current().nextInt(1000, 3000),
                            type
                    );
                });
    }
}
