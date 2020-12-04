package ca.catterall.vehicleRegistrationDTO.repo;

import ca.catterall.vehicleRegistrationDTO.model.VehicleRegistrationDTO;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRegistrationRepository extends CrudRepository<VehicleRegistrationDTO, Integer>{

}
