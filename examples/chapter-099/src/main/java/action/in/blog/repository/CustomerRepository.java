package action.in.blog.repository;

import action.in.blog.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
