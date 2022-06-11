package ludwiniak.wiktor.Lab.L13;

import java.awt.*;

public class PointUtils {
    public static double getDistance(Point startPoint, Point point) {
        return Math.hypot(Math.abs(point.y - startPoint.y), Math.abs(point.x - startPoint.x));
    }

    public static double getAngle(Point startPoint, Point point) {
        double a = point.x - startPoint.x;
        double c = getDistance(startPoint, point);
        return a < 0 ? 180 - Math.toDegrees(Math.acos(Math.abs(a) / c)) : Math.toDegrees(Math.acos(a / c)) ;
    }

    public static int vectorProduct(Point p1, Point p2) {
        return p1.x * p2.y - p2.x * p1.y;
    }

    public static Point subtract(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }


}
