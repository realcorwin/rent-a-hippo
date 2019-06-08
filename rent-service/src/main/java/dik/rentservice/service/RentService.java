package dik.rentservice.service;

import dik.rentservice.model.Rent;

import java.util.List;

public interface RentService {

    List<Rent> getRentsByAnimal(String animal);
}
