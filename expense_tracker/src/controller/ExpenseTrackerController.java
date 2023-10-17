package controller;
import javax.swing.JOptionPane;
import view.ExpenseTrackerView;
import java.util.List;
import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private TransactionFilter currentFilter;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods

  public void applyFilter() {
    List<Transaction> transactions = model.getTransactions();
    String selectedFilterType = view.getSelectedFilterType();
    String categoryFilter = view.getFilterCategory();
    double minAmountFilter = view.getMinAmountFilter();
    double maxAmountFilter = view.getMaxAmountFilter();

    // Check the selected filter type and apply the filter
    if (selectedFilterType.equals("Category")) {
        // Apply category filter
        if (!InputValidation.isValidCategory(categoryFilter)) {
            JOptionPane.showMessageDialog(view, "Invalid filter category. Please enter a valid category.");
            return;
        }
        currentFilter = new CategoryFilter(categoryFilter);
    } else if (selectedFilterType.equals("Amount")) {
        if (!InputValidation.isValidAmount(minAmountFilter)) {
          JOptionPane.showMessageDialog(view, "Invalid minimum amount. Please enter a valid amount.");
          return;
        }
        if (!InputValidation.isValidAmount(maxAmountFilter)) {
          JOptionPane.showMessageDialog(view, "Invalid maximum amount. Please enter a valid amount.");
          return;   
        }
        if (minAmountFilter > maxAmountFilter) {
          JOptionPane.showMessageDialog(view, "Minimum amount cannot be greater than maximum amount. Please enter a valid range.");
          return;
        }

        // Apply amount filter
        currentFilter = new AmountFilter(minAmountFilter, maxAmountFilter);
    }

    if (currentFilter != null) {
        List<Transaction> filteredTransactions = currentFilter.filter(model.getTransactions());

        // Highlight filtered transactions in green
        view.refreshTableWithHighlightedRows(transactions, filteredTransactions);
    }

}

}

