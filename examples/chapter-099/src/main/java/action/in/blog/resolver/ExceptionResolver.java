package action.in.blog.resolver;

import action.in.blog.exception.ApplicationException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ExceptionResolver implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(
            Throwable throwable,
            DataFetchingEnvironment environment
    ) {
        var exception = toApplicationException(throwable);
        return Mono.just(
                List.of(
                        GraphqlErrorBuilder.newError(environment)
                                .message(exception.getMessage())
                                .errorType(exception.getErrorType())
                                .extensions(exception.getExtensions())
                                .build()
                )
        );
    }

    private ApplicationException toApplicationException(Throwable throwable) {
        return ApplicationException.class.equals(throwable.getClass()) ?
                (ApplicationException) throwable :
                new ApplicationException(
                        ErrorType.INTERNAL_ERROR,
                        throwable.getMessage(),
                        Collections.emptyMap()
                );
    }
}
