package bank.services;

import bank.ex.NoSuchAccountException;
import bank.ex.NotEnoughMoneyException;
import bank.models.Account;
import bank.models.Transaction;
import bank.repositories.AccountDao;
import bank.repositories.TransactionDao;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@EnableTransactionManagement
public class TransactionService {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public TransactionService(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    public List<Transaction> findAllTransactions() {
        List<Transaction> rsl = transactionDao.findAll();
        rsl.sort(Comparator.comparing(Transaction::getId));
        return rsl;
    }

    public String findTransactionsByClientId(int clientId) {
        List<Transaction> transactions = transactionDao.findByClientId(clientId);
        transactions.sort(Comparator.comparing(Transaction::getId));
        Gson gson = new Gson();
        return gson.toJson(transactions);
    }

    @Transactional
    public void doTransaction(Transaction transaction) throws NotEnoughMoneyException, NoSuchAccountException {
        if (transaction.getType().getId() == 1) {
            topUp(transaction.getNumber(), transaction.getSum());
        } else if (transaction.getType().getId() == 2) {
            withDraw(transaction.getNumber(), transaction.getSum());
        } else {
            withDraw(transaction.getNumber(), transaction.getSum());
            topUp(transaction.getExchNumber(), transaction.getSum());
        }
        transactionDao.save(transaction);
    }

    private void topUp(int number, int sum) throws NoSuchAccountException {
        Optional<Account> opt = accountDao.findByNumber(number);
        if (!opt.isPresent()) {
            throw new NoSuchAccountException();
        }
        Account account = opt.get();
        account.setSum(account.getSum() + sum);
        accountDao.update(account);
    }

    private void withDraw(int number, int sum) throws NotEnoughMoneyException, NoSuchAccountException {
        Optional<Account> opt = accountDao.findByNumber(number);
        if (!opt.isPresent()) {
            throw new NoSuchAccountException();
        }
        Account account = opt.get();
        int remains = account.getSum() - sum;
        if (remains < 0) {
            throw new NotEnoughMoneyException();
        }
        account.setSum(remains);
        accountDao.update(account);
    }
}
