package ca.equifax.vehicle.registration.suites.example.tag;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("ca.equifax.vehicle.registration.dto.tag")
@IncludeTags({"RegistrationRequiredTest", "RegistrationFastTest"})
public class AddAndFastTagSuite{
}
