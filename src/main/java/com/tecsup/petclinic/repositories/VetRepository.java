package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VetRepository extends CrudRepository<Vet, Integer> {

    // Fetch vets by first name
    List<Vet> findByFirstName(String firstName);

    // Fetch vets by last name
    List<Vet> findByLastName(String lastName);

    // Fetch all vets
    @Override
    List<Vet> findAll();
}
