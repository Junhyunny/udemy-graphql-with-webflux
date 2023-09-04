package action.in.blog.service;

import action.in.blog.domain.CustomerDTO;
import lombok.Data;

@Data
public class MultiCustomerAssignment {

    private CustomerDTO a;
    private CustomerDTO b;
}
