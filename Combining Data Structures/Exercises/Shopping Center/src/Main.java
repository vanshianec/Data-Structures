import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        ShoppingCenter shoppingCenter = new ShoppingCenter();
        StringBuilder stringBuilder = new StringBuilder();
        int numberOfLines = Integer.parseInt(consoleReader.readLine());
        for (int i = 0; i < numberOfLines; i++) {
            String inputParams = consoleReader.readLine();
            String command = inputParams.split(" ")[0];
            String productsData = inputParams.substring(inputParams.indexOf(" ") + 1);
            String name;
            Double price;
            String producer;
            switch (command) {
                case "AddProduct":
                    String params[] = productsData.split(";");
                    name = params[0];
                    price = Double.parseDouble(params[1]);
                    producer = params[2];
                    stringBuilder.append(shoppingCenter.addProduct(name, price, producer)).append(System.lineSeparator());
                    break;
                case "FindProductsByName":
                    name = productsData;
                    addSortedProducts(shoppingCenter.findProductsByName(name), stringBuilder);
                    break;
                case "FindProductsByProducer":
                    producer = productsData;
                    addSortedProducts(shoppingCenter.findProductsByProducer(producer), stringBuilder);
                    break;
                case "FindProductsByPriceRange":
                    Double from = Double.parseDouble(productsData.split(";")[0]);
                    Double to = Double.parseDouble(productsData.split(";")[1]);
                    addSortedProducts(shoppingCenter.findProductsByRange(from, to), stringBuilder);
                    break;
                case "DeleteProducts":
                    String deleteParameters[] = productsData.split(";");
                    if (deleteParameters.length == 2) {
                        name = deleteParameters[0];
                        producer = deleteParameters[1];
                        stringBuilder.append(shoppingCenter.deleteProducts(name, producer)).append(System.lineSeparator());
                    } else {
                        stringBuilder.append(shoppingCenter.deleteProducts(productsData)).append(System.lineSeparator());
                    }
                    break;
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private static void addSortedProducts(List<Product> products, StringBuilder stringBuilder) {
        if (products == null || products.isEmpty()) {
            stringBuilder.append("No products found").append(System.lineSeparator());
            return;
        }
        products.sort(Product::compareTo);
        for (Product product : products) {
            stringBuilder.append("{")
                    .append(product.getName())
                    .append(";")
                    .append(product.getProducer())
                    .append(";")
                    .append(String.format("%.2f", product.getPrice()))
                    .append("}").append(System.lineSeparator());
        }
    }
}
