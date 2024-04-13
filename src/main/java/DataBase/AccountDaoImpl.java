package DataBase;
import Interface.AccountDao;
import model.BaseAccount;
import model.BusinessAccount;
import model.IndividualAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public void createAccount(BaseAccount account) {
        String sql = "INSERT INTO accounts (id, account_type, first_name, last_name, ogrn) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, account.getId());
            pstmt.setInt(2, account.getAccountType());
            if (account instanceof IndividualAccount) {
                IndividualAccount ia = (IndividualAccount) account;
                pstmt.setString(3, ia.getFirstName());
                pstmt.setString(4, ia.getLastName());
                pstmt.setNull(5, Types.BIGINT); // OGRN is not applicable for IndividualAccount
            } else if (account instanceof BusinessAccount) {
                BusinessAccount ba = (BusinessAccount) account;
                pstmt.setNull(3, Types.VARCHAR); // First name not applicable
                pstmt.setNull(4, Types.VARCHAR); // Last name not applicable
                pstmt.setLong(5, ba.getOgrn());
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(long id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BaseAccount> getAccounts() {
        List<BaseAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                int type = rs.getInt("account_type");
                if (type == 1) { // IndividualAccount
                    accounts.add(new IndividualAccount(
                            id, rs.getString("first_name"), rs.getString("last_name")));
                } else if (type == 2) { // BusinessAccount
                    accounts.add(new BusinessAccount(id, rs.getLong("ogrn")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public BaseAccount getAccount(long id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int type = rs.getInt("account_type");
                if (type == 1) {
                    return new IndividualAccount(
                            id, rs.getString("first_name"), rs.getString("last_name"));
                } else if (type == 2) {
                    return new BusinessAccount(id, rs.getLong("ogrn"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}