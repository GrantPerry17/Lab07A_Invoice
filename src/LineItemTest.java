import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LineItemTest
{
    @Test
    public void testLineItemQuantity()
    {
        Product product = new Product("Keyboard", 49.99);
        LineItem item = new LineItem(product, 3);

        assertEquals(3, item.getQuantity());
    }

    @Test
    public void testLineItemTotal()
    {
        Product product = new Product("Keyboard", 49.99);
        LineItem item = new LineItem(product, 3);

        assertEquals(149.97, item.getTotal(), 0.001);
    }
}