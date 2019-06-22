package dik.rentfare.repository;

import dik.rentfare.model.RentFare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentFareRepository extends JpaRepository<RentFare, Long> {

    RentFare findFirstByAnimal(String animal);
}
