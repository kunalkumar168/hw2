package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;
import java.util.ArrayList;
import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;


public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton applyFilterBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JTextField filterCategoryField;
  private JFormattedTextField filterMinAmountField;
  private JFormattedTextField filterMaxAmountField;
  private JComboBox<String> filterTypeComboBox;
  private List<Boolean> rowHighlights;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(900, 500); // Make GUI larger
    rowHighlights = new ArrayList<>();
    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    applyFilterBtn = new JButton("Apply Filter");
    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();
    format.setGroupingUsed(false);

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);
    JLabel filterCategoryLabel = new JLabel("Filter Category: ");
    filterCategoryField = new JTextField(10);
    JLabel filterMinAmountLabel = new JLabel("Filter Amount (Min):");
    filterMinAmountField = new JFormattedTextField(format);
    filterMinAmountField.setColumns(10);
    JLabel filterMaxAmountLabel = new JLabel("Filter Amount (Max):");
    filterMaxAmountField = new JFormattedTextField(format);
    filterMaxAmountField.setColumns(10);

        // Create filter type combo box
    filterTypeComboBox = new JComboBox<>(new String[]{"Category", "Amount"});
    filterTypeComboBox.addActionListener(e -> {
        String selectedFilterType = filterTypeComboBox.getSelectedItem().toString();
        if (selectedFilterType.equals("Category")) {
            // Display category input field
            filterCategoryLabel.setVisible(true);
            filterMinAmountLabel.setVisible(false);
            filterMaxAmountLabel.setVisible(false);
            filterCategoryField.setVisible(true);
            filterMinAmountField.setVisible(false);
            filterMaxAmountField.setVisible(false);
        } else if (selectedFilterType.equals("Amount")) {
            // Display amount input fields (min and max)
            filterCategoryLabel.setVisible(false);
            filterMinAmountLabel.setVisible(true);
            filterMaxAmountLabel.setVisible(true);
            filterCategoryField.setVisible(false);
            filterMinAmountField.setVisible(true);
            filterMaxAmountField.setVisible(true);
        }
    });

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    inputPanel.add(applyFilterBtn);
    inputPanel.add(new JLabel("Filter Type:"));
    inputPanel.add(filterTypeComboBox);
    inputPanel.add(filterCategoryLabel);
    inputPanel.add(filterCategoryField);
    inputPanel.add(filterMinAmountLabel);
    inputPanel.add(filterMinAmountField);
    inputPanel.add(filterMaxAmountLabel);
    inputPanel.add(filterMaxAmountField);
    // Set initial visibility for filter components

    filterCategoryLabel.setVisible(false);
    filterMinAmountLabel.setVisible(false);
    filterMaxAmountLabel.setVisible(false);
    filterCategoryField.setVisible(false);
    filterMinAmountField.setVisible(false);
    filterMaxAmountField.setVisible(false);
  
  
    
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(applyFilterBtn);
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  


// Add these methods to get input values from the view:
  public String getSelectedFilterType() {
    return filterTypeComboBox.getSelectedItem().toString();
  }

  public String getFilterCategory() {
    return filterCategoryField.getText();
  }

  public double getMinAmountFilter() {
    // Parse the min amount field as double
    if(filterMinAmountField.getText().isEmpty()) {
      return 0;
    } else {
    String minAmountText = filterMinAmountField.getText();
    return minAmountText.isEmpty() ? 1.0 : Double.parseDouble(minAmountText);
    }
  }


  public double getMaxAmountFilter() {
    // Parse the max amount field as double
    if(filterMaxAmountField.getText().isEmpty()) {
      return 0;
    } else {
    String maxAmountText = filterMaxAmountField.getText();
    return maxAmountText.isEmpty() ? 1000.0 : Double.parseDouble(maxAmountText);
  }}

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  public JButton getapplyFilterBtn() {
    return applyFilterBtn;
  }

  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}