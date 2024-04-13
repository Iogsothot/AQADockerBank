package UserApi;



import Interface.AccountDao;
import model.BaseAccount;
import model.BusinessAccount;
import model.IndividualAccount;

import java.util.List;
import java.util.Optional;

public class UserApi {
    private AccountDao accountDao;

    public UserApi(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void createAccount(BaseAccount account) {
        accountDao.createAccount(account);
    }
    public void deleteAccount(long id) {
        accountDao.deleteAccount(id);
    }

    public List<BaseAccount> getAllAccounts() {
        return accountDao.getAccounts();
    }

    public Optional<BaseAccount> getAccount(long id) {
        return Optional.ofNullable(accountDao.getAccount(id));
    }
}
