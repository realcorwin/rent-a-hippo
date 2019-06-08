package dik.rentservice.service;

import dik.rentservice.model.Rent;
import dik.rentservice.repository.RentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {

    @Value("${animal.disabled}")
    private String animalDisabled;

    final RentRepository rentRepository;

    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public List<Rent> getRentsByAnimal(String animal) {
        List<Rent> rents = rentRepository.findRentsByAnimal(animal);
        if (!CollectionUtils.isEmpty(rents)) {
            rents = rents.stream().filter(flight -> !animalDisabled.equals(flight.getAnimal()))
                    .collect(Collectors.toList());
        }
        return rents;
    }
}
