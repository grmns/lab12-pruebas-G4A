package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.exception.VetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetServiceTest {

    @Autowired
    private VetService vetService;

    @Test
    public void testCreateVet() {

        String FIRST_NAME = "John";
        String LAST_NAME = "Doe";

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);

        Vet vetCreated = vetService.create(vet);

        System.out.println("VET CREATED: " + vetCreated.toString());

        assertNotNull(vetCreated.getId());
        assertEquals(FIRST_NAME, vetCreated.getFirstName());
        assertEquals(LAST_NAME, vetCreated.getLastName());
    }

    @Test
    public void testUpdateVet() {

        String FIRST_NAME = "John";
        String LAST_NAME = "Doe";

        String UP_FIRST_NAME = "German";
        String UP_LAST_NAME = "Sandoval";

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);

        // ------------ Create ---------------

        log.info(">" + vet);
        Vet vetCreated = this.vetService.create(vet);
        log.info(">>" + vetCreated);

        // ------------ Update ---------------

        // Prepare data for update
        vetCreated.setFirstName(UP_FIRST_NAME);
        vetCreated.setLastName(UP_LAST_NAME);

        // Execute update
        Vet upgradedVet = this.vetService.update(vetCreated);
        log.info(">>>>" + upgradedVet);

        assertEquals(UP_FIRST_NAME, upgradedVet.getFirstName());
        assertEquals(UP_LAST_NAME, upgradedVet.getLastName());
    }

    @Test
    public void testDeleteVet() {

        String FIRST_NAME = "Jared";
        String LAST_NAME = "Garcia";

        // ------------ Create ---------------

        Vet vet = new Vet(FIRST_NAME, LAST_NAME);
        vet = this.vetService.create(vet);
        log.info(("" + vet));

        // ------------ Delete ---------------

        try {
            this.vetService.delete(vet.getId());
        } catch (VetNotFoundException e) {
            fail(e.getMessage());
        }

        // ------------ Validation ---------------

        try {
            this.vetService.findById(vet.getId());
            assertTrue(false);
        } catch (VetNotFoundException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testFindVetById(){
        String First_name = "Helen";
        Integer Id = 2;
        Vet vet = null;

        try{
            vet = this.vetService.findById(Id);
        }catch (VetNotFoundException e){
            fail(e.getMessage());
        }
        assertEquals(First_name, vet.getFirstName());
    }

    @Test
    public void testFindVetByFirstName(){
        String First_name = "Helen";
        int size = 1;
        List<Vet> vets = this.vetService.findByFirstName(First_name);
        assertEquals(size,vets.size());
    }

    @Test
    public void testFindVetByLastName(){
        String Last_name = "Leary";
        int size = 1;
        List<Vet> vets = this.vetService.findByLastName(Last_name);
        assertEquals(size,vets.size());
    }
}
