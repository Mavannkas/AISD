package ludwiniak.wiktor.Lab.L13;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ConvexHull {
    public static List<Point> solve(List<Point> points) throws IllegalArgumentException {
        List<Point> pointList = new ArrayList<>(points);
        Point startPoint = cutFirstPoint(pointList);
        preparePointsList(pointList, startPoint);

        if (pointList.size() < 2) {
            throw new IllegalArgumentException();
        }

        Stack<Point> pointStack = getStack(startPoint, pointList);
        solve(pointList, pointStack);

        return pointStack;
    }

    private static void solve(List<Point> points, Stack<Point> pointStack) {
        for (int i = 3; i < points.size(); i++) {
            findNextPoint(points, pointStack, i);
        }
        pointStack.push(pointStack.get(0));
    }

    private static void findNextPoint(List<Point> points, Stack<Point> pointStack, int index) {
        while (
                PointUtils.vectorProduct(
                        PointUtils.subtract(
                                getFromEnd(pointStack, 1),
                                getFromEnd(pointStack, 0)
                        ),
                        PointUtils.subtract(
                                points.get(index),
                                getFromEnd(pointStack, 0)
                        )
                ) >= 0
        ) {
            pointStack.pop();
        }

        pointStack.push(points.get(index));
    }

    private static Point getFromEnd(Stack<Point> pointStack, int i) {
        return pointStack.get(pointStack.size() - (i + 1));
    }

    private static Stack<Point> getStack(Point startPoint, List<Point> points) {
        Stack<Point> pointStack = new Stack<>();
        pointStack.push(startPoint);
        pointStack.push(points.get(0));
        pointStack.push(points.get(1));
        return pointStack;
    }

    private static void preparePointsList(List<Point> points, Point startPoint) {
        Comparator<Point> comparator = new PolarPointComparator(startPoint);
        points.sort(comparator);
        removeDuplicates(points, comparator);
    }

    private static void removeDuplicates(List<Point> points, Comparator<Point> comparator) {
        for (int i = 1; i < points.size(); i++) {
            int compareResult = comparator.compare(points.get(i - 1), points.get(i));
            if (compareResult == -2 || compareResult == 0) {
                points.remove(i - 1);
                i--;
            }
        }
    }

    private static Point cutFirstPoint(List<Point> points) {
        Iterator<Point> pointIterator = points.iterator();
        Point resultPoint = pointIterator.next();

        while (pointIterator.hasNext()) {
            Point currentPoint = pointIterator.next();
            if (isPointClosestCenter(currentPoint, resultPoint)) {
                resultPoint = currentPoint;
            }
        }

        while(points.remove(resultPoint));
        return resultPoint;
    }

    private static boolean isPointClosestCenter(Point point1, Point point2) {
        return point1.y < point2.y || point1.y == point2.y && point1.x < point2.x;
    }
}
