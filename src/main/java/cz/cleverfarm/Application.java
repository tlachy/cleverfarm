package cz.cleverfarm;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class Application {


    @Bean
    public Validator validatorFactory() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public GeometryFactory getGeometryFactory() {
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        return geometryFactory;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}