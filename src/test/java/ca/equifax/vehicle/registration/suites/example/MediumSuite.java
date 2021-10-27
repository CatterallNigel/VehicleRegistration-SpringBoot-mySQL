package ca.equifax.vehicle.registration.suites.example;


import ca.equifax.vehicle.registration.TestCategories;
import ca.equifax.vehicle.registration.dto.category.VehicleRegistrationAddDeleteTests;
import ca.equifax.vehicle.registration.dto.category.VehicleRegistrationDeleteTests;
import ca.equifax.vehicle.registration.dto.category.VehicleRegistrationGetTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({VehicleRegistrationAddDeleteTests.class,
        VehicleRegistrationGetTests.class, VehicleRegistrationDeleteTests.class})
@Categories.ExcludeCategory({TestCategories.RegistrationDeleteTest.class})
public class MediumSuite {
}
