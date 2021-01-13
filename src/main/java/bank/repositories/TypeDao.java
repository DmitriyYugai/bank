package bank.repositories;

import bank.models.Client;
import bank.models.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeDao {
    private JdbcTemplate jdbc;

    public TypeDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Type> findAll() {
        return jdbc.query(
                "select * from types",
                (rs, rowNum) -> {
                    Type type = new Type();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

}
