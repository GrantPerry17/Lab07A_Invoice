import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ProductTest
{
    @Test
    public void testProductName()
    {
        Product product = new Product("Keyboard", 49.99);

        assertEquals("Keyboard", product.getName());
    }

    @Test
    public void testProductUnitPrice()
    {
        Product product = new Product("Keyboard", 49.99);

        assertEquals(49.99, product.getUnitPrice(), 0.001);
    }
}