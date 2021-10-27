package ca.equifax.vehicle.registration.repo;

import ca.equifax.vehicle.registration.model.VehicleRegistrationDTO;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRegistrationRepository extends CrudRepository<VehicleRegistrationDTO, Integer>{

}
