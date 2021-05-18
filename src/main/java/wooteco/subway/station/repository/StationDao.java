package wooteco.subway.station.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import wooteco.subway.station.domain.Station;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StationDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Station> stationRowMapper = (resultSet, rowNum) -> new Station(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );

    public StationDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Station save(final Station station) {
        String query = "INSERT INTO STATION(name) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
            ps.setString(1, station.getName());
            return ps;
        }, keyHolder);

        return new Station(
                Objects.requireNonNull(keyHolder.getKey()).longValue(),
                station.getName()
        );
    }

    public List<Station> findAll() {
        String query = "SELECT id, name FROM station ORDER BY id";
        return jdbcTemplate.query(query, stationRowMapper);
    }

    public void deleteById(final Long id) {
        String query = "DELETE FROM station WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public Optional<Station> findById(final Long id) {
        String query = "SELECT id, name FROM station WHERE id = ?";
        return jdbcTemplate.query(query, stationRowMapper, id)
                .stream()
                .findFirst();
    }

    public boolean doesNameExist(final String name) {
        String query = "SELECT EXISTS (SELECT * FROM station WHERE name = ?)";
        return jdbcTemplate.queryForObject(query, Boolean.class, name);
    }

    public boolean doesIdNotExist(final Long id) {
        String query = "SELECT NOT EXISTS (SELECT * FROM station WHERE id = ?)";
        return jdbcTemplate.queryForObject(query, Boolean.class, id);
    }
}
