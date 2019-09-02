import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int startClustersNumber = Integer.parseInt(consoleReader.readLine());
        int numberOfReports = Integer.parseInt(consoleReader.readLine());
        int galaxySize = Integer.parseInt(consoleReader.readLine());
        KdTree starClusters = new KdTree();
        addStarClusters(consoleReader, startClustersNumber, galaxySize, starClusters);
        executeReport(consoleReader, numberOfReports, starClusters);
    }

    private static void executeReport(BufferedReader consoleReader, int numberOfReports, KdTree starClusters) throws IOException {
        for (int i = 0; i < numberOfReports; i++) {
            String reportParams[] = consoleReader.readLine().split("\\s+");
            int x1 = Integer.parseInt(reportParams[1]);
            int y1 = Integer.parseInt(reportParams[2]);
            int width = Integer.parseInt(reportParams[3]);
            int height = Integer.parseInt(reportParams[4]);
            GalaxyArea galaxyArea = new GalaxyArea(x1, y1, width, height);
            System.out.println(starClusters.findPointsInArea(galaxyArea));
        }
    }

    private static void addStarClusters(BufferedReader consoleReader, int startClustersNumber, int galaxySize, KdTree starClusters) throws IOException {
        for (int i = 0; i < startClustersNumber; i++) {
            String starClusterParams[] = consoleReader.readLine().split("\\s+");
            int x = Integer.parseInt(starClusterParams[1]);
            int y = Integer.parseInt(starClusterParams[2]);
            Point2D starCluster = new Point2D(x, y);
            starClusters.insert(starCluster, galaxySize);
        }
    }
}
