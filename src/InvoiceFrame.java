import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InvoiceFrame extends JFrame
{
    private JTextField titleField;
    private JTextField addressField;
    private JTextField productField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextArea invoiceArea;

    public InvoiceFrame()
    {
        setTitle("Invoice");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Invoice Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Customer Address:"));
        addressField = new JTextField();
        inputPanel.add(addressField);

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

        JScrollPane scrollPane = new JScrollPane(invoiceArea);

        JPanel buttonPanel = new JPanel();

        JButton displayButton = new JButton("Display Invoice");
        JButton clearButton = new JButton("Clear");
        JButton quitButton = new JButton("Quit");

        buttonPanel.add(displayButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}