import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String line = consoleReader.readLine();
        List<CollisionObject> objects = new ArrayList<>();

        addObjects(consoleReader, line, objects);
        Collections.sort(objects);

        line = consoleReader.readLine();
        int tick = 1;
        while (!line.equals("end")) {
            if (line.startsWith("move")) {
                moveObject(line, objects);
            }
            objects = sweepAndPrune(objects, tick);
            tick++;
            line = consoleReader.readLine();
        }
    }

    private static void addObjects(BufferedReader consoleReader, String line, List<CollisionObject> collisionObjects) throws IOException {
        while (!line.equals("start")) {
            String[] params = line.split("\\s+");
            String name = params[1];
            int x1 = Integer.parseInt(params[2]);
            int y1 = Integer.parseInt(params[3]);
            collisionObjects.add(new CollisionObject(name, x1, y1));
            line = consoleReader.readLine();
        }
    }

    private static void moveObject(String line, List<CollisionObject> objcets) {
        String objectParams[] = line.split(" ");
        String objectName = objectParams[1];
        int x1 = Integer.parseInt(objectParams[2]);
        int y1 = Integer.parseInt(objectParams[3]);
        objcets.forEach(o -> {
            if (o.getName().equals(objectName)) {
                o.setX1(x1);
                o.setY1(y1);
            }
        });
    }

    private static List<CollisionObject> sweepAndPrune(List<CollisionObject> objects, int tick) {
        Collections.sort(objects);
        for (int i = 0; i < objects.size(); i++) {
            CollisionObject current = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {
                CollisionObject candidateCollisionObject = objects.get(j);
                if (current.getX2() < candidateCollisionObject.getX1()) {
                    break;
                }
                if ((current.getY1() <= candidateCollisionObject.getY2() && current.getY2() >= candidateCollisionObject.getY1())) {
                    System.out.printf("(%d) %s collides with %s%n", tick, current.getName(), candidateCollisionObject.getName());
                }
            }
        }
        return objects;
    }
}
