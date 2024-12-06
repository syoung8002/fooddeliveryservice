package fooddeliveryservice.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "EventCollector_table")
@Data
public class EventCollector {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String type;
    private String correlationKey;
    private String payload;
    private Date timestamp;
}
