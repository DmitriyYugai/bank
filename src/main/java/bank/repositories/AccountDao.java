package bank.repositories;

import bank.models.Account;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDao {
    private JdbcTemplate jdbc;

    public AccountDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Account> findAll() {
        return jdbc.query(
                "select * from accounts",
                (rs, rowNum) -> {
                    Account account = new Account();
                    account.setNumber(rs.getInt("number"));
                    account.setSum(rs.getInt("sum"));
                    account.setClientId(rs.getInt("client_id"));
                    return account;
                });
    }

    public List<Account> getByClientId(int clientId) {
        return jdbc.query(
                "select * from accounts " +
                        "where client_id = ?",
                (rs, rowNum) -> {
                    Account account = new Account();
                    account.setNumber(rs.getInt("number"));
                    account.setSum(rs.getInt("sum"));
                    account.setClientId(rs.getInt("client_id"));
                    return account;
                }, clientId);
    }

    public Optional<Account> findByNumber(int number) {
        return jdbc.query("select * " +
                "from accounts " +
                "where number = ?", rs -> {
            if (!rs.next()) {
                return Optional.empty();
            }
            Account account = new Account();
            account.setNumber(rs.getInt("number"));
            account.setSum(rs.getInt("sum"));
            account.setClientId(rs.getInt("client_id"));
            return Optional.of(account);
        }, number);
    }

    public Account save(Account account) {
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accounts(number, sum, client_id) " +
                            "values(?, ?, ?)");
            ps.setInt(1, account.getNumber());
            ps.setInt(2, account.getSum());
            ps.setInt(3, account.getClientId());
            return ps;
        });
        return account;
    }

    public boolean update(Account account) {
        int updated = jdbc.update("update accounts set " +
                        "sum = ? " +
                        "where number = ?",
                account.getSum(),
                account.getNumber());
        return updated > 0;
    }

    public static void main(String[] args) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://127.0.0.1:5432/bank");
        ds.setUsername("postgres");
        ds.setPassword("root");
        AccountDao store = new AccountDao(new JdbcTemplate(ds));
        System.out.println(store.save(new Account(123, 1000, 1)));
        System.out.println(store.save(new Account(456, 2000, 1)));
//        System.out.println(store.getAll());
//        System.out.println(store.getByClientId(1));
//        System.out.println(store.findByNumber(567878));
//        store.update(new Account(567878, 2000, 1));
//        System.out.println(store.findByNumber(567878));
    }
}
