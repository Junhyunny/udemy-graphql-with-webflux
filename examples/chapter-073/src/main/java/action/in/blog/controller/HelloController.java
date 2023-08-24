package action.in.blog.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {

    @QueryMapping
    public String sayHello(@Argument String name) {
        return String.format("Hello %s World", name);
    }
}
