package action.in.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.graphql.ResponseError;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor(staticName = "create")
public class GenericResponse<T> {
    private final T data;
    private final List<ResponseError> errors;
    private final boolean dataPresent;

    public static <T> GenericResponse<T> of(T data) {
        return GenericResponse.create(data, null, true);
    }


    public static <T> GenericResponse<T> of(List<ResponseError> errors) {
        return GenericResponse.create((T) null, errors, false);
    }
}
