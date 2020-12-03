package ca.catterall.vehicleRegistrationDTO.model;


import ca.catterall.vehicleRegistrationDTO.Utils.Converters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VehicleRegistration {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String registrationId;
    private String carManufacturer;
    private String carModel;


    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;

    private int yearOfManufacture;

    public Integer getId() {
        return id;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getDateOfRegistration() {
        return Converters.parseDateToString(dateOfRegistration);
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }
}
