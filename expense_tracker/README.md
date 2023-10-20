# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

## Understandability: Documentation
----------------------------
### New Functionality
In this assignment, we have modified `View` and `Controller` component of the `ExpenseTrackerApp` to add the filtering component using `Amount` and `Category` values. We performed following steps to accomodate the changes :
1. Implemented strategies for the two filters: `CategoryFilter` and `AmountFilter`. For this, we updated the `ExpenseTrackerController` and `ExpenseTrackerView` java modules add an `applyFilter` function.
2. Applied encapsulation for the list of transactions.
3. Applied immutability on the list of transactions when the getter method is invoked.
4. Applied changes to the `Transaction` class to prevent external data modification.

Now, in order to implement the `applyFilter` function to incorporate two filter strategies. Following steps were performed : 
1. Created a `TransactionFilter` interface using filter method.
2. Two filter class `CategoryFilter` and `AmountFilter` for filtering based of `Category` and `Amount` value respectively are implemented separately extending `TransactionFilter` interface.
3. Added UI elements on `ExpenseTrackerView` class for filter specification. 
    3.1 Here we have `getSelectedFilterType` function to create drop down based on Amount or Category.
    3.2 Added `getapplyFilterBtn` drop-down functionality to `getSelectedFilterType` from filter button on which filter is applied.
    3.3 We have `getFilterCategory` to get input in text to filter out Category.
    3.4 We have `getMinAmountFilter` and `getMaxAmountFilter` as minimum and maximum value respectively filter for Amount.
4. Updated `refreshTableWithHighlightedRows` method in `ExpenseTrackerView` class to display filtered value with highlighted `Light Green` colour.
5. Updated the `ExpenseTrackerApp` to set up an action listener for the `applyFilterButton` in the UI.
6. Added `applyFilter` method `ExpenseTrackerController` module.
7. Updated `ExpenseTrackerController` to add inputValidation logic for amount and category in the `applyFilter()` method. 
