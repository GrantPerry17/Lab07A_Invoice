import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class InvoiceTest
{
    @Test
    public void testInvoiceTitle()
    {
        Invoice invoice = new Invoice("Customer Invoice", "123 Main Street");

        assertEquals("Customer Invoice", invoice.getTitle());
    }

    @Test
    public void testInvoiceAddress()
    {
        Invoice invoice = new Invoice("Customer Invoice", "123 Main Street");

        assertEquals("123 Main Street", invoice.getCustomerAddress());
    }

    @Test
    public void testInvoiceTotal()
    {
        Invoice invoice = new Invoice("Customer Invoice", "123 Main Street");

        Product product1 = new Product("Keyboard", 49.99);
        Product product2 = new Product("Mouse", 24.99);

        invoice.addLineItem(new LineItem(product1, 2));
        invoice.addLineItem(new LineItem(product2, 1));

        assertEquals(124.97, invoice.getTotalAmountDue(), 0.001);
    }
}