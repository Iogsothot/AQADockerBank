package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FinancialDBAccess {
    private static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Bank;user=sa;password=YourStrong!Passw0rd;encrypt=false;";

    public static void main(String[] args) {
        try {
            // Adding a new bank account
            addBankAccount("223456789", 1, 1000.00);
            // Select and display bank accounts
            selectBankAccounts();
            // Updating the balance of the bank account
            updateBankAccountBalance("223456789", 1500.00);
            // Select and display bank accounts after update
            selectBankAccounts();
            // Deleting the bank account
            deleteBankAccount("223456789");
            // Select and display bank accounts to confirm deletion
            selectBankAccounts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addBankAccount(String accountNumber, int ownerId, double balance) throws Exception {
        String sql = "INSERT INTO BankAccount (AccountNumber, AccountOwnerId, Balance, CreationDate) VALUES (?, ?, ?, GETDATE())";
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            pstmt.setInt(2, ownerId);
            pstmt.setDouble(3, balance);
            pstmt.executeUpdate();
            System.out.println("Bank account added successfully.");
        }
    }

    private static void updateBankAccountBalance(String accountNumber, double newBalance) throws Exception {
        String sql = "UPDATE BankAccount SET Balance = ? WHERE AccountNumber = ?";
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            pstmt.executeUpdate();
            System.out.println("Bank account balance updated successfully.");
        }
    }

    private static void deleteBankAccount(String accountNumber) throws Exception {
        String sql = "DELETE FROM BankAccount WHERE AccountNumber = ?";
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            pstmt.executeUpdate();
            System.out.println("Bank account deleted successfully.");
        }
    }

    private static void selectBankAccounts() throws Exception {
        String sql = "SELECT AccountNumber, AccountOwnerId, Balance, CreationDate FROM BankAccount";
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String accountNumber = rs.getString("AccountNumber");
                int ownerId = rs.getInt("AccountOwnerId");
                double balance = rs.getDouble("Balance");
                java.sql.Date creationDate = rs.getDate("CreationDate");
                System.out.println("Account Number: " + accountNumber + ", Owner ID: " + ownerId + ", Balance: " + balance + ", Creation Date: " + creationDate);
            }
        }
    }
}
