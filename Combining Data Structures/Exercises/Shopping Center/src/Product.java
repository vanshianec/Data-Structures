public class Product implements Comparable<Product> {

    private String name;
    private Double price;
    private String producer;

    public Product(String name, Double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public int compareTo(Product o) {
        int cmp = this.name.compareTo(o.name);
        if (cmp == 0) {
            cmp = this.producer.compareTo(o.producer);
            if (cmp == 0) {
                cmp = this.price.compareTo(o.price);
            }
        }
        return cmp;
    }
}
