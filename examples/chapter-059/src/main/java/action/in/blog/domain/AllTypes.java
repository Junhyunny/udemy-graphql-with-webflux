package action.in.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class AllTypes {

    // known
    private UUID id;
    private int height;
    private float temperature;
    private String city;
    private boolean isValid;
    private Car car;

    // unknown
    private long distance;
    private byte ageInYears;
    private short ageInMonths;
    private BigDecimal money;
    private BigInteger bigInteger;
    private LocalDate date;
    private LocalTime time;
    private OffsetDateTime dateTime;
}
