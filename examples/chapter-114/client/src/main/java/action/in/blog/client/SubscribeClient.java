package action.in.blog.client;

import action.in.blog.domain.CustomerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.WebSocketGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Flux;

@Service
public class SubscribeClient {

    private final WebSocketGraphQlClient client;

    public SubscribeClient(@Value("${customer-service.subscription-url}") String baseUrl) {
        this.client = WebSocketGraphQlClient
                .builder(baseUrl, new ReactorNettyWebSocketClient())
                .build();
    }

    public Flux<CustomerEvent> customerEvents() {
        return this.client.document("""
                            subscription {
                                customerEvents {
                                    id
                                    action
                                }
                            }
                        """)
                .retrieveSubscription("customerEvents")
                .toEntity(CustomerEvent.class);
    }
}
