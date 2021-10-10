package beers.mandatory.models;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name="beers")
@Entity
public class Beer{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long beerId;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Float alcPercent;

    @Column
    private String description;



}

