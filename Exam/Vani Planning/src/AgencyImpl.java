import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AgencyImpl implements Agency {

    private HashMap<String, Invoice> idAndInvoices = new HashMap<>();
    private HashMap<LocalDate, HashSet<Invoice>> dueDateAndInvoice = new HashMap<>();
    private HashSet<Invoice> payedInvoice = new HashSet<>();

    public AgencyImpl() {

    }

    @Override
    public void create(Invoice invoice) {
        if (contains(invoice.getNumber())) {
            throw new IllegalArgumentException();
        }
        idAndInvoices.put(invoice.getNumber(), invoice);
        dueDateAndInvoice.putIfAbsent(invoice.getDueDate(), new HashSet<>());
        dueDateAndInvoice.get(invoice.getDueDate()).add(invoice);
    }

    @Override
    public boolean contains(String number) {
        return idAndInvoices.get(number) != null;
    }

    @Override
    public int count() {
        return idAndInvoices.size();
    }

    @Override
    public void payInvoice(LocalDate dueDate) {
        if (!dueDateAndInvoice.containsKey(dueDate)) {
            throw new IllegalArgumentException();
        }
        dueDateAndInvoice.get(dueDate).forEach(i -> {
            i.setSubtotal(0);
            idAndInvoices.get(i.getNumber()).setSubtotal(0);
            payedInvoice.add(i);
        });
    }

    @Override
    public void throwInvoice(String number) {
        if (!contains(number)) {
            throw new IllegalArgumentException();
        }

        Invoice invoice = idAndInvoices.get(number);
        dueDateAndInvoice.get(invoice.getDueDate()).remove(invoice);
        payedInvoice.remove(invoice);
        idAndInvoices.remove(number);
    }

    @Override
    public void throwPayed() {
        payedInvoice.forEach(i -> {
            dueDateAndInvoice.get(i.getDueDate()).remove(i);
            idAndInvoices.remove(i.getNumber());
        });
        payedInvoice.clear();
    }

    @Override
    public Iterable<Invoice> getAllInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {
        return idAndInvoices.values().stream()
                .filter(i -> i.getIssueDate().compareTo(startDate) >= 0 && i.getDueDate().compareTo(endDate) <= 0)
                .sorted(Comparator.comparing(Invoice::getIssueDate).thenComparing(Invoice::getDueDate))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> searchByNumber(String number) {
        List<Invoice> result = new ArrayList<>();
        idAndInvoices.keySet().forEach(k -> {
            if (k.contains(number)) {
                result.add(idAndInvoices.get(k));
            }
        });
        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @Override
    public Iterable<Invoice> throwInvoiceInPeriod(LocalDate startDate, LocalDate endDate) {
        List<Invoice> result = new ArrayList<>();
        idAndInvoices.values().forEach(i -> {
            if ((i.getDueDate().compareTo(startDate) > 0 && i.getDueDate().compareTo(endDate) < 0)) {
                result.add(i);
            }
        });
        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (Invoice invoice : result) {
            idAndInvoices.remove(invoice.getNumber());
            dueDateAndInvoice.remove(invoice.getDueDate());
            payedInvoice.remove(invoice);
        }
        return result;
    }

    @Override
    public Iterable<Invoice> getAllFromDepartment(Department department) {
        return idAndInvoices.values().stream()
                .filter(i -> i.getDepartment().equals(department))
                .sorted(Comparator.comparing(Invoice::getSubtotal).reversed().thenComparing(Comparator.comparing(Invoice::getIssueDate)))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Invoice> getAllByCompany(String companyName) {
        return idAndInvoices.values().stream()
                .filter(i -> i.getCompanyName().equals(companyName))
                .sorted((a, b) -> b.getNumber().compareTo(a.getNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public void extendDeadline(LocalDate endDate, int days) {
        if (!dueDateAndInvoice.containsKey(endDate)) {
            throw new IllegalArgumentException();
        }
        HashSet<Invoice> updatedSet = dueDateAndInvoice.get(endDate);
        updatedSet.forEach(i -> {
            idAndInvoices.get(i.getNumber()).setDueDate(endDate.plusDays(days));
            payedInvoice.remove(i);
            i.setDueDate(endDate.plusDays(days));
            payedInvoice.add(i);
        });
    }
}
