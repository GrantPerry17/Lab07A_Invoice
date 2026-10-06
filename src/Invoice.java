import java.util.ArrayList;

public class Invoice
{
    private String title;
    private String customerAddress;
    private ArrayList<LineItem> lineItems;

    public Invoice(String title, String customerAddress)
    {
        this.title = title;
        this.customerAddress = customerAddress;
        lineItems = new ArrayList<>();
    }

    public String getTitle()
    {
        return title;
    }

    public String getCustomerAddress()
    {
        return customerAddress;
    }

    public void addLineItem(LineItem item)
    {
        lineItems.add(item);
    }

    public ArrayList<LineItem> getLineItems()
    {
        return lineItems;
    }

    public double getTotalAmountDue()
    {
        double total = 0.0;

        for (LineItem item : lineItems)
        {
            total += item.getTotal();
        }

        return total;
    }
}