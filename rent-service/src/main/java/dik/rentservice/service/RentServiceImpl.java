package dik.rentservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import dik.rentservice.model.Rent;
import dik.rentservice.repository.RentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RentServiceImpl implements RentService {

    @Value("${animal.disabled}")
    private String animalDisabled;

    private final RentRepository rentRepository;

    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @HystrixCommand(commandKey="getRentsKey", fallbackMethod="buildFallbackRents")
    @Override
    public List<Rent> getRentsByAnimal(String animal) {
        sleepRandomly();
        List<Rent> rents = rentRepository.findRentsByAnimal(animal);
        if (!CollectionUtils.isEmpty(rents)) {
            rents = rents.stream().filter(flight -> !animalDisabled.equals(flight.getAnimal()))
                    .collect(Collectors.toList());
        }
        return rents;
    }

    public List<Rent> buildFallbackRents(String animal) {
        Rent rent = new Rent();
        rent.setId(0L);
        rent.setAnimal(animal);
        rent.setCustomer("N/A");
        rent.setStartDate("N/A");
        rent.setEndDate("N/A");
        List<Rent> rents = new ArrayList<>();
        rents.add(rent);
        return rents;
    }

    private void sleepRandomly() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if(randomNum == 3) {
            System.out.println("It is a chance for demonstrating Hystrix action");
            try {
                System.out.println("Start sleeping...." + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hystrix thread interupted...." + System.currentTimeMillis());
                e.printStackTrace();
            }
        }
    }
}
