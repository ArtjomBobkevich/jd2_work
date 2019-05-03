package by.itacademy.database.dao;

import by.itacademy.database.connection.ConnectionPool;
import by.itacademy.database.entity.Person;
import by.itacademy.database.entity.PersonRole;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDao {

    private static final PersonDao PERSON_DAO = new PersonDao();
    private static final String FIND_ALL =
            "SELECT "
                    +
                    "p.id AS person_id, "
                    +
                    "p.avatar AS avatar, "
                    +
                    "p.login AS login, "
                    +
                    "p.first_name AS firstName, "
                    +
                    "p.last_name AS lastName, "
                    +
                    "p.age AS age, "
                    +
                    "p.mail AS mail, "
                    +
                    "p.password AS password, "
                    +
                    "p.role AS role, "
                    +
                    "pr.id AS id, "
                    +
                    "pr.role AS role_name "
                    +
                    "FROM flea_market.person p "
                    +
                    "LEFT JOIN flea_market.person_role pr "
                    +
                    "ON p.role=pr.id ";

    private static final String FIND_ONE =
            FIND_ALL
                    +
                    "WHERE p.id=?";

    @SneakyThrows
    public Optional<Person> findById(Long id) {
        Optional<Person> person = Optional.empty();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ONE)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                person = Optional.of(getPersonFromResultSet(resultSet));
            }
        }
        return person;
    }

    @SneakyThrows
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Person person = getPersonFromResultSet(resultSet);
                personList.add(person);
            }
        }
        return personList;
    }

    @SneakyThrows
    private Person getPersonFromResultSet(ResultSet resultSet) {
        return Person.builder()
                .id(resultSet.getLong("person_id"))
                .avatar(resultSet.getString("avatar"))
                .login(resultSet.getString("login"))
                .firstName(resultSet.getString("firstName"))
                .lastName(resultSet.getString("lastName"))
                .age(resultSet.getString("age"))
                .mail(resultSet.getString("mail"))
                .password(resultSet.getString("password"))
                .personRole(PersonRole.builder()
                        .id(resultSet.getLong("id"))
                        .nameOfRole(resultSet.getString("role_name"))
                        .build())
                .build();
    }

    public static PersonDao getPersonDao() {
        return PERSON_DAO;
    }
}
