package dik.rentfare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rentfare")
public class RentFare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String animal;

    @Column(name = "rentfare")
    private BigDecimal rentFare;

    private String currency;

    public RentFare(Long id, String animal, BigDecimal rentFare) {
        this.id = id;
        this.animal = animal;
        this.rentFare = rentFare;
    }
}
