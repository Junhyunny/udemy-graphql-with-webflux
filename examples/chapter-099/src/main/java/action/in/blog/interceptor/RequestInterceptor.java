package action.in.blog.interceptor;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class RequestInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(
            WebGraphQlRequest request,
            Chain chain
    ) {
        System.out.println(request.getHeaders());
        var headers = request.getHeaders().getOrEmpty("caller-id");
        var callerId = headers.isEmpty() ? "" : headers.get(0);
        request.configureExecutionInput(
                (e, b) -> b.graphQLContext(Map.of("caller-id", callerId)).build()
        );
        return chain.next(request);
    }
}
