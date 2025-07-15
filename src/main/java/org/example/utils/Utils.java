package org.example.utils;

import org.example.model.Point;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Point avgPoint(List<Point> points) {
        // TODO: Manage this exception
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("List can not be null or empty");
        }

        double sumX = 0;
        double sumY = 0;

        for (Point p: points) {
            sumX += p.x();
            sumY += p.y();
        }

        double avgX = sumX / points.size();
        double avgY = sumY / points.size();

        return new Point(avgX, avgY);
    }

    public static double getDistanceBetweenPoints(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x() - p2.x(), 2) + Math.pow(p1.y() - p2.y(), 2));
    }

    public static List<Double> getDistancesToPoint(List<Point> points, Point c) {
        List<Double> distances = new ArrayList<>();
        for (Point p: points) {
            distances.add(getDistanceBetweenPoints(p, c));
        }

        return distances;
    }

    public static double stdDeviation(List<Double> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("List can not be null or empty");
        }

        double avg = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        double sqrtSum = values.stream()
                .mapToDouble(v -> Math.pow(v - avg, 2))
                .sum();

        return Math.sqrt(sqrtSum / values.size());
    }
}
