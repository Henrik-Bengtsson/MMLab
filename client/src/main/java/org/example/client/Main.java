package org.example.client;

import org.example.service.CurrencyConverter;
import org.example.service.annotations.CurrencyString;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

public class Main extends JFrame{

    ServiceLoader<CurrencyConverter> serviceLoader = ServiceLoader.load(CurrencyConverter.class);
    ButtonGroup group;

    public Main() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);

        northPanel();
        westPanel();
        centerPanel();
    }

    private void northPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 50));
        JLabel welcomeLabel = new JLabel("Welcome to your Currency converter");
        welcomeLabel.setFont(new Font("", Font.ITALIC, 25));
        panel.add(welcomeLabel, CENTER_ALIGNMENT);
        add(panel, BorderLayout.NORTH);
    }

    private void westPanel(){
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(100,150));
        JLabel selectFrom = new JLabel("Exchange to: ");
        panel.add(selectFrom, TOP_ALIGNMENT);
        group = new ButtonGroup();

        addRadioButton(panel, group);
        add(panel, BorderLayout.WEST);
    }

    private void centerPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        JPanel panelNorth = new JPanel();
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(300, 150));
        label.setFont(new Font("", Font.BOLD, 20));
        panelNorth.add(label);
        panel.add(panelNorth, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel();
        JTextField textField = new JTextField();
        JLabel amount = new JLabel("Input SEK  ");
        amount.setFont(new Font("", Font.ITALIC, 20));
        JButton button = new JButton("Submit");
        textField.setFont(new Font("", Font.ITALIC, 20));
        textField.setPreferredSize(new Dimension(100,50));
        panelCenter.add(amount);
        panelCenter.add(textField);
        panelCenter.add(button);
        panel.add(panelCenter, BorderLayout.CENTER);


        button.addActionListener(e -> {
            try {
                double value = Double.parseDouble(textField.getText());
                String selectedButton = Objects.requireNonNull(getSelectedButton()).getText();

                if (!textField.getText().isEmpty() && group.getSelection().isSelected()) {
                    for (var converter : getCurrencyConverter(selectedButton)) {
                        label.setText("Converted to " + selectedButton + ": " + converter.getConvertedCurrency(value));
                    }
                }
            }catch (NullPointerException exception){
                label.setText("Choose currency");
            }catch (NumberFormatException exception){
                label.setText("Input is incorrect");
            }
        });
    }

    private List<CurrencyConverter> getCurrencyConverter(String text) {
        return serviceLoader.stream()
                .filter(c -> c.type().isAnnotationPresent(CurrencyString.class) &&
                        c.type().getAnnotation(CurrencyString.class).value().equals(text))
                .map(ServiceLoader.Provider :: get)
                .toList();
    }

    private void addRadioButton(JPanel panel, ButtonGroup group) {
        for (var converter : serviceLoader) {
            JRadioButton button = new JRadioButton(converter.getClass().getAnnotation(CurrencyString.class).value());
            panel.add(button);
            group.add(button);
        }
    }

    private JRadioButton getSelectedButton(){
        for (Enumeration<AbstractButton> e = group.getElements(); e.hasMoreElements();) {
            JRadioButton b = (JRadioButton) e.nextElement();
            if (b.getModel() == group.getSelection()) {
                return b;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }

}
