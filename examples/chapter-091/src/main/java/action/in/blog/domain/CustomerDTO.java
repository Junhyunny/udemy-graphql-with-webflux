package action.in.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Data
public class CustomerDTO {
    private int id;
    private String name;
    private int age;
    private String city;

    public static CustomerDTO of(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getCity()
        );
    }

    public Customer toEntity() {
        return Customer.create(id, name, age, city);
    }

    public Customer toEntity(int id) {
        return Customer.create(id, name, age, city);
    }
}
