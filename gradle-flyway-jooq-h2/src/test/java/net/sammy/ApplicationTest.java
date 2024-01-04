package net.sammy;

import net.sammy.tables.Person;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.sql.Statement;
import java.util.List;

class ApplicationTest {
    final static String DB_URL = "jdbc:h2:file:~/.h2Databases/testDB";

    @Test
    void testUsingRawSql() {
        try (Connection conn = DriverManager.getConnection(DB_URL, "sa", "");
             Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery("select * from PERSON");

            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + " ");
                System.out.println(resultSet.getString(2));
                System.out.print(resultSet.getInt("ID") + " ");
                System.out.println(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testFetchUsingJooq() {
        try (Connection conn = DriverManager.getConnection(DB_URL, "sa", "")) {
            DSLContext context = DSL.using(conn, SQLDialect.H2);
            Result<Record> result = context.select().from(Person.PERSON).fetch();
            List<String> listOfNames = result.map(record -> record.getValue(Person.PERSON.NAME));
            System.out.println(listOfNames);
            result.forEach(record -> System.out.println(record.getValue(1)));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}