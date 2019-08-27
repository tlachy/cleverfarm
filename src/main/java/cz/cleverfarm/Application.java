package cz.cleverfarm;

import cz.cleverfarm.entity.Field;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        Field field = new Field();
        field.setName("field1");
        field.setNote("corn field");
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        Coordinate[] coords = new Coordinate[]{
                new Coordinate(49.196643, 16.595905),
                new Coordinate(49.192583, 16.599928),
                new Coordinate(49.195773, 16.602310),
                new Coordinate(49.196643, 16.595905)
        };

        Polygon polygon = geometryFactory.createPolygon(coords);

        System.out.println(polygon.getArea());

//        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}