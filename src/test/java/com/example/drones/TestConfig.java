package com.example.drones;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootApplication(scanBasePackages={"com.example.drones"})
public class TestConfig {
}
