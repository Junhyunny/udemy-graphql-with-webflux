package action.in.blog.service;

import action.in.blog.domain.CustomerDTO;
import action.in.blog.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Flux<CustomerDTO> customers() {
        return customerRepository.findAll()
                .map(CustomerDTO::of);
    }

    public Mono<CustomerDTO> customerById(int id) {
        return customerRepository.findById(id)
                .map(CustomerDTO::of);
    }

    public Mono<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        return customerRepository.save(customerDTO.toEntity())
                .map(CustomerDTO::of);
    }
}
