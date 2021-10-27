package ca.equifax.vehicle.registration.suites.controller;

import ca.equifax.vehicle.registration.TestCategories;
import ca.equifax.vehicle.registration.controller.MockVehicleRegistrationControllerTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Suite.SuiteClasses({MockVehicleRegistrationControllerTests.class})
@Categories.IncludeCategory({TestCategories.RegistrationRequiredTest.class})
public class RequiredMockControllerSuite {
}
