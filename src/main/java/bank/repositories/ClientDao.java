package bank.repositories;

import bank.models.Client;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ClientDao {
    private JdbcTemplate jdbc;

    public ClientDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Client> findAll() {
        return jdbc.query(
                "select * from clients",
                (rs, rowNum) -> {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setName(rs.getString("name"));
                    return client;
                });
    }

    public Client save(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into clients(name) " +
                            "values(?)", new String[]{"id"});
            ps.setString(1, client.getName());
            return ps;
        }, keyHolder);
        client.setId((int) keyHolder.getKey());
        return client;
    }

    public static void main(String[] args) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://127.0.0.1:5432/bank");
        ds.setUsername("postgres");
        ds.setPassword("root");
        ClientDao store = new ClientDao(new JdbcTemplate(ds));
        System.out.println(store.save(new Client("ZZZ")));
    }
}
