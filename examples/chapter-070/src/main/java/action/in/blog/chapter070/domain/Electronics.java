package action.in.blog.chapter070.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Electronics {
    private UUID id;
    private String description;
    private int price;
    private String brand;
}
