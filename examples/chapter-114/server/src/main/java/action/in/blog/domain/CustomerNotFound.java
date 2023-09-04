package action.in.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor(staticName = "create")
public class CustomerNotFound implements CustomerResponse {
    private final String message = "user not found";
    private int id;
}
