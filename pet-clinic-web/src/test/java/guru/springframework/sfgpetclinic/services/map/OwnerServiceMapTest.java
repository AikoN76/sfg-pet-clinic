package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;
    private final Long OWNER_ID = 1L;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(1L).lastName("Smith").build());
    }

    @Test
    void findAll() {
        Set<Owner> results = ownerServiceMap.findAll();
        assertEquals(1L, results.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ID);
        assertEquals(0L, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(OWNER_ID));
        assertEquals(0L, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        final Long ID = 2L;
        Owner owner2  = Owner.builder().id(ID).build();
        Owner savedOwner = ownerServiceMap.save(owner2);
        assertEquals(ID, savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner owner = Owner.builder().build();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner result = ownerServiceMap.findById(OWNER_ID);
        assertEquals(OWNER_ID, result.getId());
    }

    @Test
    void findByLastName() {
        Owner result = ownerServiceMap.findByLastName("Smith");
        assertEquals("Smith", result.getLastName());
    }

    @Test
    void findByLastNameNotFound() {
        Owner result = ownerServiceMap.findByLastName("Carter");
        assertNull(result);
    }
}