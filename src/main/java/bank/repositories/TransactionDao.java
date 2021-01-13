package bank.repositories;

import bank.models.Client;
import bank.models.Transaction;
import bank.models.Type;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TransactionDao {
    private JdbcTemplate jdbc;

    public TransactionDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Transaction> findAll() {
        return jdbc.query(
                "select t.id as transaction_id, " +
                        "tp.id as type_id, " +
                        "tp.name as type_name, " +
                        "t.sum, " +
                        "c.id as client_id, " +
                        "c.name as client_name, " +
                        "t.number, " +
                        "t.exch_number " +
                        "from transactions as t " +
                        "inner join clients as c on t.client_id = c.id " +
                        "inner join types as tp on t.type_id = tp.id",
                (rs, rowNum) -> {
                    Client client = new Client(rs.getInt("client_id"),
                            rs.getString("client_name"));
                    Type type = new Type(rs.getInt("type_id"), rs.getString("type_name"));
                    Transaction transaction = new Transaction();
                    transaction.setId(rs.getInt("transaction_id"));
                    transaction.setType(type);
                    transaction.setSum(rs.getInt("sum"));
                    transaction.setClient(client);
                    transaction.setNumber(rs.getInt("number"));
                    transaction.setExchNumber(rs.getInt("exch_number"));
                    return transaction;
                });
    }

    public List<Transaction> findByClientId(int clientId) {
        return jdbc.query(
                "select t.id as transaction_id, " +
                        "tp.id as type_id, " +
                        "tp.name as type_name, " +
                        "t.sum, " +
                        "c.id as client_id, " +
                        "c.name as client_name, " +
                        "t.number, " +
                        "t.exch_number " +
                        "from transactions as t " +
                        "inner join clients as c on t.client_id = c.id " +
                        "inner join types as tp on t.type_id = tp.id " +
                        "where t.client_id = ?",
                (rs, rowNum) -> {
                    Client client = new Client(rs.getInt("client_id"),
                            rs.getString("client_name"));
                    Type type = new Type(rs.getInt("type_id"), rs.getString("type_name"));
                    Transaction transaction = new Transaction();
                    transaction.setId(rs.getInt("transaction_id"));
                    transaction.setType(type);
                    transaction.setSum(rs.getInt("sum"));
                    transaction.setClient(client);
                    transaction.setNumber(rs.getInt("number"));
                    transaction.setExchNumber(rs.getInt("exch_number"));
                    return transaction;
                }, clientId);
    }

    public Transaction save(Transaction transaction) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into transactions (type_id, sum, client_id, number, exch_number) " +
                            "values(?, ?, ?, ?, ?)", new String[]{"id"});
            ps.setInt(1, transaction.getType().getId());
            ps.setInt(2, transaction.getSum());
            ps.setInt(3, transaction.getClient().getId());
            ps.setInt(4, transaction.getNumber());
            ps.setInt(5, transaction.getExchNumber());
            return ps;
        }, keyHolder);
        transaction.setId((int) keyHolder.getKey());
        return transaction;
    }

    public static void main(String[] args) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://127.0.0.1:5432/bank");
        ds.setUsername("postgres");
        ds.setPassword("root");
        TransactionDao store = new TransactionDao(new JdbcTemplate(ds));
//        System.out.println(store.save(new Transaction(
//                new Type(1, "Пополнить"),
//                1000,
//                new Client(1, "Client1"), 123, 456))
//        );
        System.out.println(store.findAll());
    }
}
