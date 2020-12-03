package ca.catterall.vehicleRegistrationDTO.repo;

import ca.catterall.vehicleRegistrationDTO.model.VehicleRegistration;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRegistrationRepository extends CrudRepository<VehicleRegistration, Integer>{

}
