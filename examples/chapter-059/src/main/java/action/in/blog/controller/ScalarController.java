package action.in.blog.controller;

import action.in.blog.domain.AllTypes;
import action.in.blog.domain.Car;
import action.in.blog.domain.Product;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Controller
public class ScalarController {

    @QueryMapping
    public Mono<AllTypes> allTypes() {
        return Mono.just(
                AllTypes.create(
                        UUID.randomUUID(),
                        100,
                        99.1f,
                        "Seoul",
                        true,
                        Car.BMW,
                        10002010201L,
                        Byte.parseByte("12"),
                        Short.parseShort("100"),
                        BigDecimal.valueOf(123456789.123456789),
                        BigInteger.valueOf(1234567890),
                        LocalDate.now(),
                        LocalTime.now(),
                        OffsetDateTime.now()
                )
        );
    }

    @QueryMapping
    public Flux<Product> products() {
        return Flux.just(
                Product.create(
                        "banana",
                        Map.of(
                                "key", "value",
                                "hello", "world"
                        )
                ),
                Product.create(
                        "mac",
                        Map.of(
                                "cpu", "8",
                                "ram", "32G"
                        )
                )
        );
    }
}
