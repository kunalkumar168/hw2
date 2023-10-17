package controller;

import model.Transaction;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryFilter implements TransactionFilter {
    private String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        // Filter transactions that match the specified category
        return transactions.stream()
                .filter(transaction -> transaction.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
