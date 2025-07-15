package org.example.model.element;

import javafx.scene.paint.Color;
import org.example.model.Point;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LineEq implements Element {
    private final double a;
    private final double b;
    private final double c;
    private Point start = null;
    private Point end = null;
    private Color color;

    public LineEq(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public LineEq(Point p1, Point p2) {
        this.a = p2.y() - p1.y();
        this.b = p1.x() - p2.x();
        this.c = p2.x() * p1.y() - p1.x() * p2.y();
    }

    public LineEq(Point p1, Point p2, Color color) {
        this.a = p2.y() - p1.y();
        this.b = p1.x() - p2.x();
        this.c = p2.x() * p1.y() - p1.x() * p2.y();
        this.color = color;
    }

    public Point intersectWithVertical(double x) {
        if (b == 0) return null;
        double y = (-a * x - c) / b;
        return new Point(x, y);
    }

    public Point intersectWithHorizontal(double y) {
        if (a == 0) return null;
        double x = (-b * y - c) / a;
        return new Point(x, y);
    }

    public void delimit(double xMin, double xMax, double yMin, double yMax) {
        List<Point> points = new ArrayList<>();
        List<Point> tempPoints = new ArrayList<>();

        tempPoints.add(intersectWithVertical(xMin));
        tempPoints.add(intersectWithVertical(xMax));
        tempPoints.add(intersectWithHorizontal(yMin));
        tempPoints.add(intersectWithHorizontal(yMax));

        for (Point p : tempPoints) {
            if (p != null && p.x() >= xMin && p.x() <= xMax && p.y() >= yMin && p.y() <= yMax) {
                points.add(p);
            }
        }

        if (points.size() >= 2) {
            points = points.stream().limit(2).toList();
            start = points.get(0);
            end = points.get(1);
        } else {
            start = null;
            end = null;
        }
    }

    public Point getIntersect(LineEq l2) {
        double a1, b1, c1;
        a1 = a;
        b1 = b;
        c1 = c;

        double a2, b2, c2;
        a2 = l2.getA();
        b2 = l2.getB();
        c2 = l2.getC();

        double D = a1 * b2 - a2 * b1;
        if (D == 0) {
            return null;
        }

        double Dx = -c1 * b2 + c2 * b1;
        double Dy = -c2 * a1 + c1 * a2;

        double x = Dx / D;
        double y = Dy / D;

        return new Point(x, y);
    }

    public static Point vanishingPoint(List<LineEq> lines) {
        List<Point> intersections = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                Point pt = lines.get(i).getIntersect(lines.get(j));
                if (pt != null)
                    intersections.add(pt);
            }
        }

        if (intersections.isEmpty())
            return null;

        Point tempPoint = Utils.avgPoint(intersections);

        List<Double> distances = Utils.getDistancesToPoint(intersections, tempPoint);

        double threshold = Utils.stdDeviation(distances) * 1.5;
        List<Point> filteredIntersections = new ArrayList<>();
        for (int i = 0; i < intersections.size(); i++) {
            if (distances.get(i) <= threshold)
                filteredIntersections.add(intersections.get(i));
        }

        return Utils.avgPoint(filteredIntersections);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "LineEq{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
