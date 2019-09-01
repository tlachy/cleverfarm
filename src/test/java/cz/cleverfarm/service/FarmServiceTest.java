package cz.cleverfarm.service;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import static cz.cleverfarm.CZPolygon.CZ_POLYGON;
import static org.junit.Assert.*;

public class FarmServiceTest {

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

    @Test
    public void overlapsOtherField() {

        Coordinate[] coords = new Coordinate[]{
                new Coordinate(3, 3),
                new Coordinate(6, 3),
                new Coordinate(6, 6),
                new Coordinate(3, 6),
                new Coordinate(3, 3)
        };

        Coordinate[] overlapCoordinates = new Coordinate[]{
                new Coordinate(2, 2),
                new Coordinate(5, 2),
                new Coordinate(5, 5),
                new Coordinate(2, 5),
                new Coordinate(2, 2)
        };

        Coordinate[] withinCoordinates = new Coordinate[]{
                new Coordinate(4, 4),
                new Coordinate(5, 4),
                new Coordinate(5, 5),
                new Coordinate(4, 5),
                new Coordinate(4, 4)
        };

        Polygon polygon = geometryFactory.createPolygon(coords);
        Polygon overlapPolygon = geometryFactory.createPolygon(overlapCoordinates);
        Polygon withinPolygon = geometryFactory.createPolygon(withinCoordinates);

        assertEquals(polygon.getArea(), 9, 0.0);
        assertTrue(overlapPolygon.overlaps(polygon));
        assertTrue(withinPolygon.within(polygon));
        assertTrue(polygon.contains(withinPolygon));
    }

    @Test
    public void isPragueInCzechRepublic() {
        Point vienna = geometryFactory.createPoint(new Coordinate(16.363449, 48.210033));
        assertFalse(CZ_POLYGON.contains(vienna));
    }

    @Test
    public void isViennaInCzechRepublic() {
        Point prague = geometryFactory.createPoint(new Coordinate(14.418540, 50.073658));
        assertTrue(CZ_POLYGON.contains(prague));
    }


}