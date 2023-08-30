package action.in.blog.exception;

import action.in.blog.domain.CustomerDTO;
import org.springframework.graphql.execution.ErrorType;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

public class ApplicationErrors {

    public static <T> Mono<T> noSuchUser(int id) {
        return Mono.error(
                new ApplicationException(
                        ErrorType.BAD_REQUEST,
                        "no such user",
                        Map.of(
                                "customerId", id,
                                "timestamp", LocalDateTime.now()
                        )
                )
        );
    }

    public static <T> Mono<T> mustGreaterThan18(CustomerDTO customer) {
        return Mono.error(
                new ApplicationException(
                        ErrorType.BAD_REQUEST,
                        "Must be 18 or above",
                        Map.of(
                                "customer", customer,
                                "timestamp", LocalDateTime.now()
                        )
                )
        );
    }
}
