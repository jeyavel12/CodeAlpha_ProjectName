import java.util.*;

public class ObjectDetectionTrackingSimulation {

    static class DetectedObject {
        int id;
        String label;
        int x, y, width, height;

        DetectedObject(int id, String label, int x, int y, int w, int h) {
            this.id = id;
            this.label = label;
            this.x = x;
            this.y = y;
            this.width = w;
            this.height = h;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Object Detection and Tracking Simulation ===");

        List<DetectedObject> objects = new ArrayList<>();

        // Simulated detections
        objects.add(new DetectedObject(1, "Person", 100, 150, 50, 120));
        objects.add(new DetectedObject(2, "Car", 300, 200, 120, 60));

        for (int frame = 1; frame <= 3; frame++) {

            System.out.println("\nFrame " + frame + ":");

            for (DetectedObject obj : objects) {

                // Simulate movement (tracking)
                obj.x += 10;
                obj.y += 5;

                System.out.println(
                        "Object ID: " + obj.id +
                        ", Label: " + obj.label +
                        ", Bounding Box: [x=" + obj.x +
                        ", y=" + obj.y +
                        ", w=" + obj.width +
                        ", h=" + obj.height + "]"
                );
            }
        }

        System.out.println("\nObject detection and tracking completed.");
    }
}