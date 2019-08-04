package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeServiceMap petTypeServiceMap;
    private final PetService petService;

    public OwnerServiceMap(PetTypeServiceMap petTypeServiceMap, PetService petService) {
        this.petTypeServiceMap = petTypeServiceMap;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findall();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {

        if(object != null) {
            if(object.getPets() != null){
                object.getPets().forEach(pet -> {
                    if(pet.getPetType() != null){
                        if(pet.getId() == null){
                            pet.setPetType(petTypeServiceMap.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required!");
                    }

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
