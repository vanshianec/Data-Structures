import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CTest24 {

    private Agency agency;

    @Before
    public void setup() {
        this.agency = new AgencyImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwInvoiceInPeriod_should_throw_exception() {


        Invoice inv1 = new Invoice("b48ab542-3aba-4588-8450-c00c2f58d276",
                "HRS",
                125D, Department.INCOMES,
                LocalDate.of(2018, 2, 12),
                LocalDate.of(2018, 3, 13));

        Invoice inv2 = new Invoice("43c96303-3032-4a7f-9924-054d148c3787",
                "SoftUni",
                1000D, Department.INCOMES,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 12));

        Invoice inv3 = new Invoice("a65e6062-4e0d-48e0-8018-b76095b71d52",
                "SoftUni",
                1000D, Department.INCOMES,
                LocalDate.of(2017, 2, 12),
                LocalDate.of(2018, 3, 13));

        this.agency.create(inv1);
        this.agency.create(inv2);
        this.agency.create(inv3);

        this.agency.throwInvoiceInPeriod(LocalDate.of(2020, 10, 28), LocalDate.of(2020, 12, 21));

    }
}