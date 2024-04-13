package Interface;

import model.BaseAccount;

import java.util.List;

public interface AccountDao {
    void createAccount(BaseAccount account);
    void deleteAccount(long id);
    List<BaseAccount> getAccounts();
    BaseAccount getAccount(long id);
}

