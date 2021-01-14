package bank.services;

import bank.models.Account;
import bank.models.Client;
import bank.repositories.AccountDao;
import bank.repositories.ClientDao;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AccountService {
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String findAllAccounts() {
        List<Account> rsl = accountDao.findAll();
        rsl.sort(Comparator.comparing(Account::getNumber));
        Gson gson = new Gson();
        return gson.toJson(rsl);
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
