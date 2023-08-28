package action.in.blog.service;

import action.in.blog.domain.CustomerDTO;
import action.in.blog.domain.CustomerEvent;
import action.in.blog.domain.DeleteResponseDTO;
import action.in.blog.domain.Status;
import action.in.blog.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static action.in.blog.domain.Action.*;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerEventService customerEventService;

    public Flux<CustomerDTO> allCustomers() {
        return customerRepository.findAll()
                .map(CustomerDTO::of);
    }

    public Mono<CustomerDTO> customerById(int id) {
        return customerRepository.findById(id)
                .map(CustomerDTO::of);
    }

    public Mono<CustomerDTO> createCustomer(CustomerDTO customer) {
        return Mono.just(customer)
                .map(CustomerDTO::toEntity)
                .flatMap(customerRepository::save)
                .map(CustomerDTO::of)
                .doOnNext(customerDTO ->
                        customerEventService.emitEvent(
                                CustomerEvent.create(customer.getId(), CREATED)
                        )
                );
    }

    public Mono<CustomerDTO> updateCustomer(int id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(customer -> customerDTO.toEntity(id))
                .flatMap(customerRepository::save)
                .map(CustomerDTO::of)
                .doOnNext(dto ->
                        customerEventService.emitEvent(
                                CustomerEvent.create(dto.getId(), UPDATED)
                        )
                );
    }

    public Mono<DeleteResponseDTO> deleteCustomer(int id) {
        return customerRepository.deleteById(id)
                .doOnSuccess(customer ->
                        customerEventService.emitEvent(
                                CustomerEvent.create(id, DELETED)
                        )
                )
                .thenReturn(DeleteResponseDTO.create(id, Status.SUCCESS))
                .onErrorReturn(DeleteResponseDTO.create(id, Status.FAILURE))
                ;
    }
}
