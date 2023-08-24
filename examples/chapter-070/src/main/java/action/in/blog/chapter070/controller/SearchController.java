package action.in.blog.chapter070.controller;

import action.in.blog.chapter070.domain.Book;
import action.in.blog.chapter070.domain.Electronics;
import action.in.blog.chapter070.domain.Fruit;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

@Controller
public class SearchController {

    private final List<Object> items = List.of(
            Fruit.create("Banana", LocalDate.now().toString()),
            Electronics.create(UUID.randomUUID(), "MacBook", 4000, "APPLE"),
            Electronics.create(UUID.randomUUID(), "Galaxy", 2000, "SAMSUNG"),
            Book.create("Clean Code", "Bob")
    );

    @QueryMapping
    public Flux<Object> search() {
        return Mono.fromSupplier(() -> new ArrayList<>(items))
                .doOnNext(Collections::shuffle)
                .flatMapIterable(Function.identity())
                .take(ThreadLocalRandom.current().nextInt(0, items.size()));
    }
}
