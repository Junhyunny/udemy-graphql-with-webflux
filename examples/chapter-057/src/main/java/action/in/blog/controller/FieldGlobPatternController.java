package action.in.blog.controller;

import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FieldGlobPatternController {

    @QueryMapping
    public Object level1(DataFetchingFieldSelectionSet selectionSet) {
        System.out.println(selectionSet.getFields());
        // level1 객체를 기준으로 탐색하기 때문에 존재
        System.out.printf("level2: %s\n", selectionSet.contains("level2"));
        // level1 객체를 기준으로 탐색하기 때문에 미존재
        System.out.printf("level3: %s\n", selectionSet.contains("level3"));
        System.out.printf("level2: %s\n", selectionSet.contains("level2/level3"));
        System.out.printf("level2/level3: %s\n", selectionSet.contains("*/level3"));
        System.out.printf("level2/*/level5: %s\n", selectionSet.contains("level2/*/level5"));
        System.out.printf("level2/**/level5: %s\n", selectionSet.contains("level2/**/level5"));
        return null;
    }
}
