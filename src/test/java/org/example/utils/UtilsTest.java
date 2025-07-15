package org.example.utils;

import org.example.model.Point;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilsTest {

    @Test
    public void testAveragePointWithOnePoint() {
        List<Point> points = List.of(
                new Point(50, 10)
        );

        Point expectedPoint = new Point(50, 10);
        Point result = Utils.avgPoint(points);

        assertEquals(expectedPoint, result);
    }

    @Test
    public void testAveragePointWithTwoPoints() {
        List<Point> points = List.of(
                new Point(10.223, 20.3),
                new Point(9.5, 21.54)
        );

        Point expectedPoint = new Point(9.8615, 20.92);
        Point result = Utils.avgPoint(points);

        assertEquals(expectedPoint.x(), result.x(), 0.01);
        assertEquals(expectedPoint.y(), result.y(), 0.01);
    }

    @Test
    public void testAveragePointWithThreePoints() {
        List<Point> points = List.of(
                new Point(10.223, 20.3),
                new Point(9.5, 21.54),
                new Point(11.2, 20.8)
        );

        Point expectedPoint = new Point(10.3076, 20.88);
        Point result = Utils.avgPoint(points);

        assertEquals(expectedPoint.x(), result.x(), 0.01);
        assertEquals(expectedPoint.y(), result.y(), 0.01);
    }

    @Test
    public void testAveragePointWithFourPoints() {
        List<Point> points = List.of(
                new Point(-10.22, 20.3),
                new Point(9.5, -21.54),
                new Point(-11.2, 20.8),
                new Point(10.4, -21.8)
        );

        Point expectedPoint = new Point(-0.38, -0.56);
        Point result = Utils.avgPoint(points);

        assertEquals(expectedPoint.x(), result.x(), 0.01);
        assertEquals(expectedPoint.y(), result.y(), 0.01);
    }

    @Test
    public void testStdDeviationRegularCase() {
        List<Double> data = Arrays.asList(2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0);
        double result = Utils.stdDeviation(data);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testStdDeviationWithOneElement() {
        List<Double> data = Collections.singletonList(10.0);
        double result = Utils.stdDeviation(data);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testStdDeviationWithNegativeNumbers() {
        List<Double> data = Arrays.asList(-2.0, -4.0, -4.0, -4.0, -5.0, -5.0, -7.0, -9.0);
        double result = Utils.stdDeviation(data);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testStdDeviationEmptyList() {
        List<Double> data = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> Utils.stdDeviation(data));
    }

    @Test
    public void testStdDeviationNullList() {
        assertThrows(IllegalArgumentException.class, () -> Utils.stdDeviation(null));
    }
}
