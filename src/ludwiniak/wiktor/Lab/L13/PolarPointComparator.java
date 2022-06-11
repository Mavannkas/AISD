package ludwiniak.wiktor.Lab.L13;

import java.awt.*;
import java.util.Comparator;

import static ludwiniak.wiktor.Lab.L13.PointUtils.*;

public class PolarPointComparator implements Comparator<Point> {
    private final Point startPoint;

    public PolarPointComparator(Point startPoint) {
        this.startPoint = startPoint;
    }
    @Override
    public int compare(Point o1, Point o2) {
        int firstPointRadians = (int)getAngle(startPoint, o1) * 10000;
        int secondPointRadians = (int)getAngle(startPoint, o2) * 10000;
        if(firstPointRadians < secondPointRadians) {
            return -1;
        } else if (firstPointRadians > secondPointRadians) {
            return 1;
        }

        double firstPointDistance = getDistance(startPoint, o1);
        double secondPointDistance = getDistance(startPoint, o2);

        if(firstPointDistance < secondPointDistance) {
            return -2;
        } else if (firstPointDistance > secondPointDistance) {
            return 2;
        }

        return 0;
    }


}
