package action.in.blog.chapter070.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Fruit {
    private String description;
    private String expiryDate;
}
