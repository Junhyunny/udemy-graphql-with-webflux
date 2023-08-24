package action.in.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class CustomerWithOrder {
    private int id;
    private String name;
    private int age;
    private String city;
    private List<CustomerOrder> orders;
}
