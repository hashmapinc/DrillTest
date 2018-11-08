package com.hashmapinc.tempus.witsml.DrillTest;

import com.hashmapinc.tempus.witsml.DrillTest.controller.WellController;
import com.hashmapinc.tempus.witsml.DrillTest.controller.WellboreController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {WellController.class, WellboreController.class})
public class DrillTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrillTestApplication.class, args);
	}
}
