package action.in.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerDTO {
    private int id;
    private String name;
    private int age;
    private String city;

    public static CustomerDTO of(Customer customer) {
        return CustomerDTO.create(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getCity()
        );
    }

    public Customer toEntity() {
        return Customer.create(
                this.id,
                this.name,
                this.age,
                this.city
        );
    }
}
