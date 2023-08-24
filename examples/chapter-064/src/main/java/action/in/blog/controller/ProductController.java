package action.in.blog.controller;

import action.in.blog.domain.Book;
import action.in.blog.domain.Electronics;
import action.in.blog.domain.FruitDTO;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.UUID;

@Controller
public class ProductController {

    @QueryMapping
    public Flux<Object> products() {
        return Flux.just(
                FruitDTO.create(UUID.randomUUID(), "Banana", 1000, LocalDate.now()),
                Electronics.create(UUID.randomUUID(), "Macbook", 400000, "APPLE"),
                Book.create(UUID.randomUUID(), "Clean Code", 32000, "Bob")
        );
    }
}
