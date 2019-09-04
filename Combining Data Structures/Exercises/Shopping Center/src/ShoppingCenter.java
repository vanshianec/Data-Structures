import java.util.*;

public class ShoppingCenter {

    private HashMap<String, List<Product>> productsByName = new HashMap<>();
    private HashMap<String, List<Product>> productsByProducer = new HashMap<>();
    private TreeMap<Double, List<Product>> productsByPrice = new TreeMap<>();
    private HashMap<String, List<Product>> producerAndProductName = new HashMap<>();

    public ShoppingCenter() {
    }

    public String addProduct(String name, Double price, String producer) {
        Product product = new Product(name, price, producer);
        String nameAndProducerKey = name + "|" + producer;
        addProductsByName(name, product);
        addProductsByProducer(producer, product);
        addProductsByPrice(price, product);
        addProducerAndProductName(nameAndProducerKey, product);
        return "Product added";
    }

    public String deleteProducts(String producer) {
        if (productsByProducer.get(producer) == null || productsByProducer.get(producer).size() == 0) {
            return "No products found";
        }
        int productsDeleted = productsByProducer.get(producer).size();
        List<Product> productsToBeRemoved = this.productsByProducer.get(producer);
        for (Product product : productsToBeRemoved) {
            productsByName.get(product.getName()).remove(product);
            productsByPrice.get(product.getPrice()).remove(product);
            producerAndProductName.get(product.getName() + "|" + product.getProducer()).remove(product);
        }
        productsByProducer.remove(producer);
        return String.format("%d products deleted", productsDeleted);
    }

    public String deleteProducts(String name, String producer) {
        String key = name + "|" + producer;
        if (producerAndProductName.get(key) == null || producerAndProductName.get(key).size() == 0) {
            return "No products found";
        }
        int productsDeleted = producerAndProductName.get(key).size();
        List<Product> productsToBeRemoved = this.producerAndProductName.get(key);
        for (Product product : productsToBeRemoved) {
            productsByName.get(product.getName()).remove(product);
            productsByPrice.get(product.getPrice()).remove(product);
            productsByProducer.get(product.getProducer()).remove(product);
        }
        producerAndProductName.remove(key);
        return String.format("%d products deleted", productsDeleted);
    }

    public List<Product> findProductsByName(String name) {
        return productsByName.get(name);
    }

    public List<Product> findProductsByProducer(String producer) {
        return productsByProducer.get(producer);
    }

    public List<Product> findProductsByRange(Double fromPrice, Double toPrice) {
        List<Product> result = new ArrayList<>();
        productsByPrice.subMap(fromPrice, true, toPrice, true)
                .forEach((k, v) -> result.addAll(v));
        return result;
    }

    private void addProducerAndProductName(String nameAndProducerKey, Product product) {
        producerAndProductName.putIfAbsent(nameAndProducerKey, new ArrayList<>());
        producerAndProductName.get(nameAndProducerKey).add(product);
    }

    private void addProductsByPrice(Double price, Product product) {
        productsByPrice.putIfAbsent(price, new ArrayList<>());
        productsByPrice.get(price).add(product);
    }

    private void addProductsByProducer(String producer, Product product) {
        productsByProducer.putIfAbsent(producer, new ArrayList<>());
        productsByProducer.get(producer).add(product);
    }

    private void addProductsByName(String name, Product product) {
        productsByName.putIfAbsent(name, new ArrayList<>());
        productsByName.get(name).add(product);
    }
}
