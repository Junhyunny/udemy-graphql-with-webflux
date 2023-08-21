package action.in.blog.domain;

import lombok.Data;

@Data
public class AgeRangeFilter {
    private Integer min;
    private Integer max;

    public boolean contains(Customer customer) {
        return min <= customer.getAge() && customer.getAge() <= max;
    }
}
