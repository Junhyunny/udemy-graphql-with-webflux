package action.in.blog.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO implements CustomerResponse {
    private Integer id;
    private String name;
    private int age;
    private String city;
}
