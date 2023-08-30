package action.in.blog.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class HeaderCheckFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var isGraphiQLPath = exchange.getRequest().getURI().getPath().equals("/graphiql");
        if (isGraphiQLPath) {
            return chain.filter(exchange);
        }
        var isEmpty = exchange.getRequest().getHeaders().getOrEmpty("caller-id").isEmpty();
        return isEmpty ?
                Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(BAD_REQUEST)) :
                chain.filter(exchange);
    }
}
