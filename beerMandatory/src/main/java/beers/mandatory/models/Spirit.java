package beers.mandatory.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="spirits")
@Entity
public class Spirit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long spiritId;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private Float alcPercent;

    @Column
    private String description;

}
