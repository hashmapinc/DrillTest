package com.hashmapinc.tempus.witsml.DrillTest;

import com.hashmapinc.tempus.witsml.DrillTest.controller.CapabilitiesController;
import com.hashmapinc.tempus.witsml.DrillTest.controller.TokenBrokerController;
import com.hashmapinc.tempus.witsml.DrillTest.controller.WellController;
import com.hashmapinc.tempus.witsml.DrillTest.controller.WellboreController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {
		WellController.class,
		WellboreController.class,
		CapabilitiesController.class,
		TokenBrokerController.class})
public class DrillTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrillTestApplication.class, args);
	}
}
