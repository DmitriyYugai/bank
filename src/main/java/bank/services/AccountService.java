package bank.services;

import bank.models.Account;
import bank.models.Client;
import bank.repositories.AccountDao;
import bank.repositories.ClientDao;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AccountService {
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> findAccountsByClientId(int clientId) {
        List<Account> rsl = accountDao.getByClientId(clientId);
        rsl.sort(Comparator.comparing(Account::getNumber));
        return rsl;
    }

    public Account saveAccount(Account account) {
        return accountDao.save(account);
    }

}
