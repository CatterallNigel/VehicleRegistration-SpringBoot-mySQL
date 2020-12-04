package ca.catterall.vehicle.registration.model;


import ca.catterall.vehicle.registration.Utils.Converters;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VehicleRegistrationDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String registrationId;
    private String carManufacturer;
    private String carModel;

    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;

    private int yearOfManufacture;

    public int getId() {
        return id;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId.replaceAll("[\\n\\t ]", "");
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer.replaceAll("[\\n\\t ]", "");
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel.replaceAll("[\\n\\t ]", "");
    }

    public String getDateOfRegistration() { return Converters.parseDateToString(dateOfRegistration); }

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
