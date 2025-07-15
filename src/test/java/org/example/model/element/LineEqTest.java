package org.example.model.element;

import org.example.model.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LineEqTest {

    @Test
    public void getIntersectTest1() {
        LineEq l1 = new LineEq(5.2, -1.95, 5.87);
        LineEq l2 = new LineEq(-0.74, -4.66, 3.06);

        Point expPoint = new Point(-0.8333, 0.78893);
        Point result = l1.getIntersect(l2);

        assertEquals(expPoint.x(), result.x(), 0.001);
        assertEquals(expPoint.y(), result.y(), 0.001);
    }

    @Test
    public void getIntersectTest2() {
        LineEq l1 = new LineEq(9866, -2823, 1696);
        LineEq l2 = new LineEq(-8610, 5984, 8277);

        Point expPoint = new Point(-0.96495, -2.7716);
        Point result = l1.getIntersect(l2);

        assertEquals(expPoint.x(), result.x(), 0.001);
        assertEquals(expPoint.y(), result.y(), 0.001);
    }

    @Test
    public void getIntersectTest3() {
        LineEq l1 = new LineEq(9866, 0, 1696);
        LineEq l2 = new LineEq(-8610, 0, 8277);

        Point result = l1.getIntersect(l2);

        assertNull(result);
    }

    @Test
    public void vanishingPointTest0() {
        List<LineEq> lines = List.of();
        Point result = LineEq.vanishingPoint(lines);

        assertNull(result);
    }

    @Test
    public void vanishingPointTest1() {
        List<LineEq> lines = List.of(
                new LineEq(1, -1, 1)
        );
        Point result = LineEq.vanishingPoint(lines);

        assertNull(result);
    }

    @Test
    public void vanishingPointTest2() {
        List<LineEq> lines = List.of(
                new LineEq(1, -1, 1),
                new LineEq(-8, -5, 10)
        );

        Point expPoint = new Point(0.38462, 1.38462);
        Point result = LineEq.vanishingPoint(lines);

        assertEquals(expPoint.x(), result.x(), 0.0001);
        assertEquals(expPoint.y(), result.y(), 0.0001);
    }

    @Test
    public void vanishingPointTest3() {
        List<LineEq> lines = List.of(
                new LineEq(-551.9594451549658, 55.395042877816195, 811010.7203528382),
                new LineEq(-556.348386786108, 52.382099276875806, 814292.4062717208),
                new LineEq(-146.5116279069767, -293.54651162790697, 116611.39020734486),
                new LineEq(-143.89534883720933, -289.3604651162791, 113586.37817054121)
        );

        Point expPoint = new Point(1435.3406825, -320.1876475);
        Point result = LineEq.vanishingPoint(lines);
        System.out.println(result);

        assertEquals(expPoint.x(), result.x(), 0.0001);
        assertEquals(expPoint.y(), result.y(), 0.0001);
    }
}
