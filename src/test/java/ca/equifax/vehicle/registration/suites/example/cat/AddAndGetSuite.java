package ca.equifax.vehicle.registration.suites.example.cat;


import ca.equifax.vehicle.registration.TestCategories;
import ca.equifax.vehicle.registration.dto.category.CatVehicleRegistrationAddDeleteTests;
import ca.equifax.vehicle.registration.dto.category.CatVehicleRegistrationDeleteTests;
import ca.equifax.vehicle.registration.dto.category.CatVehicleRegistrationGetTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({CatVehicleRegistrationAddDeleteTests.class,
        CatVehicleRegistrationGetTests.class, CatVehicleRegistrationDeleteTests.class})
@Categories.ExcludeCategory({TestCategories.RegistrationDeleteTest.class})
public class AddAndGetSuite {
}
