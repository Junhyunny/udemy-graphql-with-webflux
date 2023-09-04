package action.in.blog.client;

import action.in.blog.domain.CustomerDTO;
import action.in.blog.domain.CustomerNotFound;
import action.in.blog.domain.CustomerResponse;
import action.in.blog.domain.DeleteResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CustomerClient {

    private final HttpGraphQlClient client;

    public CustomerClient(@Value("${customer-service.url}") String baseUrl) {
        this.client = HttpGraphQlClient.builder()
                .webClient(
                        builder -> builder
                                .baseUrl(baseUrl)
                )
                .build();
    }

    public Mono<ClientGraphQlResponse> rawQuery(String query) {
        return client.document(query)
                .execute();
    }

//    public Mono<GenericResponse<CustomerDTO>> getCustomerById(int id) {
//        return client.documentName("customer-by-id")
//                .variable("id", id)
//                .execute()
//                .mapNotNull(cr -> {
//                    var field = cr.field("customerById");
//                    var value = field.getValue();
//                    return value != null ?
//                            GenericResponse.of(field.toEntity(CustomerDTO.class)) :
//                            GenericResponse.of(field.getErrors());
//                })
//                ;
//    }

    public Mono<CustomerResponse> getCustomerById(int id) {
        return client.documentName("customer-by-id")
                .variable("id", id)
                .execute()
                .mapNotNull(cr -> {
                    var field = cr.field("customerById");
                    var isCustomer = "Customer".equals(cr.field("customerById.typeName").getValue());
                    return isCustomer ? field.toEntity(CustomerDTO.class) : field.toEntity(CustomerNotFound.class);
                })
                ;
    }

    public Mono<List<CustomerDTO>> allCustomers() {
        return client.documentName("customer-crud")
                .operationName("GetAll")
                .retrieve("response")
                .toEntityList(CustomerDTO.class);
    }

    public Mono<CustomerDTO> customerById(int id) {
        return client.documentName("customer-crud")
                .operationName("GetCustomerById")
                .variable("id", id)
                .retrieve("response")
                .toEntity(CustomerDTO.class);
    }

    public Mono<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        return client.documentName("customer-crud")
                .variable("customer", customerDTO)
                .operationName("CreateCustomer")
                .retrieve("response")
                .toEntity(CustomerDTO.class);
    }

    public Mono<CustomerDTO> updateCustomer(int id, CustomerDTO customerDTO) {
        return client
                .mutate()
                .header("header", "value")
                .build()
                .documentName("customer-crud")
                .variable("id", id)
                .variable("customer", customerDTO)
                .operationName("UpdateCustomer")
                .retrieve("response")
                .toEntity(CustomerDTO.class);
    }

    public Mono<DeleteResponse> deleteCustomer(int id) {
        return client.documentName("customer-crud")
                .variable("id", id)
                .operationName("DeleteCustomer")
                .retrieve("response")
                .toEntity(DeleteResponse.class);
    }
}
