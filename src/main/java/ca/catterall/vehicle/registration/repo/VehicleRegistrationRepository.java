package ca.catterall.vehicle.registration.repo;

import ca.catterall.vehicle.registration.model.VehicleRegistrationDTO;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRegistrationRepository extends CrudRepository<VehicleRegistrationDTO, Integer>{

}
