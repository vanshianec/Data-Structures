import java.util.*;
import java.util.stream.Collectors;

public class MicrosystemImpl implements Microsystem {

    private HashMap<Integer, Computer> idAndComputers = new HashMap<>();

    public MicrosystemImpl() {
    }

    @Override
    public void createComputer(Computer computer) {
        if (idAndComputers.containsKey(computer.getNumber())) {
            throw new IllegalArgumentException();
        }
        idAndComputers.put(computer.getNumber(), computer);
    }

    @Override
    public boolean contains(int number) {
        return idAndComputers.get(number) != null;
    }

    @Override
    public int count() {
        return idAndComputers.size();
    }

    @Override
    public Computer getComputer(int number) {
        if (!idAndComputers.containsKey(number)) {
            throw new IllegalArgumentException();
        }
        return idAndComputers.get(number);
    }

    @Override
    public void remove(int number) {
        if (!idAndComputers.containsKey(number)) {
            throw new IllegalArgumentException();
        }
        idAndComputers.remove(number);
    }

    @Override
    public void removeWithBrand(Brand brand) {
        boolean removed = idAndComputers.values().removeIf(c -> c.getBrand().equals(brand));
        if (!removed) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void upgradeRam(int ram, int number) {
        if (!idAndComputers.containsKey(number)) {
            throw new IllegalArgumentException();
        }
        int oldRam = idAndComputers.get(number).getRAM();
        if (ram > oldRam) {
            idAndComputers.get(number).setRAM(ram);
        }
    }

    @Override
    public Iterable<Computer> getAllFromBrand(Brand brand) {
        return idAndComputers.values().stream()
                .filter(c -> c.getBrand().equals(brand))
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getAllWithScreenSize(double screenSize) {
        return idAndComputers.values().stream()
                .filter(c -> c.getScreenSize() == screenSize)
                .sorted((a, b) -> b.getNumber() - a.getNumber())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getAllWithColor(String color) {
        return idAndComputers.values().stream()
                .filter(c -> c.getColor().equals(color))
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Computer> getInRangePrice(double minPrice, double maxPrice) {
        return idAndComputers.values().stream()
                .filter(c -> c.getPrice() >= minPrice && c.getPrice() <= maxPrice)
                .sorted((a, b) -> Double.compare(b.getPrice(), a.getPrice()))
                .collect(Collectors.toList());
    }
}
