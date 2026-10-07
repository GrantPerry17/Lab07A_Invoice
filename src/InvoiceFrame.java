import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InvoiceFrame extends JFrame
{
    private JTextField titleField;
    private JTextArea addressArea;
    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextArea invoiceArea;

    private Invoice invoice;

    public InvoiceFrame()
    {
        setTitle("Invoice");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        invoice = new Invoice("", "");

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Invoice Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Customer Address:"));
        addressArea = new JTextArea(3, 20);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        inputPanel.add(addressScrollPane);

        inputPanel.add(new JLabel("Product Name:"));
        productField = new JTextField();
        inputPanel.add(productField);

        inputPanel.add(new JLabel("Unit Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        invoiceArea = new JTextArea();
        invoiceArea.setEditable(false);

        JScrollPane invoiceScrollPane = new JScrollPane(invoiceArea);

        JPanel buttonPanel = new JPanel();

        JButton addItemButton = new JButton("Add Line Item");
        JButton displayButton = new JButton("Display Invoice");
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(addItemButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        addItemButton.addActionListener(e ->
        {
            try
            {
                if (titleField.getText().trim().isEmpty())
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Please enter an invoice title."
                    );
                    return;
                }

                if (addressArea.getText().trim().isEmpty())
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Please enter a customer address."
                    );
                    return;
                }

                if (productField.getText().trim().isEmpty())
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Please enter a product name."
                    );
                    return;
                }

                double price = Double.parseDouble(
                    priceField.getText().trim()
                );

                int quantity = Integer.parseInt(
                    quantityField.getText().trim()
                );

                if (price < 0)
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Unit price cannot be negative."
                    );
                    return;
                }

                if (quantity <= 0)
                {
                    JOptionPane.showMessageDialog(
                        this,
                        "Quantity must be greater than zero."
                    );
                    return;
                }

                /*
                 * Only create a new invoice when the current
                 * invoice does not have any line items.
                 *
                 * This allows multiple line items to be added
                 * to the same invoice.
                 */
                if (invoice.getLineItems().isEmpty())
                {
                    invoice = new Invoice(
                        titleField.getText().trim(),
                        addressArea.getText().trim()
                    );
                }

                Product product = new Product(
                    productField.getText().trim(),
                    price
                );

                LineItem item = new LineItem(product, quantity);

                invoice.addLineItem(item);

                invoiceArea.append(
                    product.getName() + "    " +
                    quantity + "    $" +
                    String.format("%.2f", item.getTotal()) +
                    "\n"
                );

                productField.setText("");
                priceField.setText("");
                quantityField.setText("");
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid unit price and quantity."
                );
            }
        });

        displayButton.addActionListener(e ->
        {
            if (invoice.getLineItems().isEmpty())
            {
                JOptionPane.showMessageDialog(
                    this,
                    "Please add at least one line item."
                );
                return;
            }

            StringBuilder output = new StringBuilder();

            output.append(invoice.getTitle()).append("\n");
            output.append("----------------------------------------\n");
            output.append(invoice.getCustomerAddress()).append("\n");
            output.append("----------------------------------------\n");
            output.append("Product          Quantity       Total\n");
            output.append("----------------------------------------\n");

            for (LineItem item : invoice.getLineItems())
            {
                output.append(
                    String.format(
                        "%-17s %-14d $%.2f%n",
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getTotal()
                    )
                );
            }

            output.append("----------------------------------------\n");

            output.append(
                String.format(
                    "Total Amount Due: $%.2f%n",
                    invoice.getTotalAmountDue()
                )
            );

            invoiceArea.setText(output.toString());
        });

        clearButton.addActionListener(e ->
        {
            titleField.setText("");
            addressArea.setText("");
            productField.setText("");
            priceField.setText("");
            quantityField.setText("");
            invoiceArea.setText("");

            invoice = new Invoice("", "");
        });

        quitButton.addActionListener(e ->
        {
            System.exit(0);
        });

        add(inputPanel, BorderLayout.NORTH);
        add(invoiceScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}