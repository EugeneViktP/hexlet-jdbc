import java.sql.DriverManager;
import java.sql.SQLException;

public class Statements {
public static void main(String[] args) throws SQLException {
    try (var conn = DriverManager.getConnection("jdbc:h2:mem:hexlet_test")) {

        var sql = "CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(255), phone VARCHAR(255))";
        // Чтобы выполнить запрос, создадим объект statement
        try (var statement = conn.createStatement()) {
            statement.execute(sql);
        }
        var sql2 = "INSERT INTO users (username, phone) VALUES (?, ?)";
        try (var preparedStatement = conn.prepareStatement(sql2)) {
            preparedStatement.setString(1, "Tommy");
            preparedStatement.setString(2, "123456789");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Emily");
            preparedStatement.setString(2, "74848303");
            preparedStatement.executeUpdate();
        }
        var sql3 = "SELECT * FROM users";
        try (var statement3 = conn.createStatement()) {
            // Здесь вы видите указатель на набор данных в памяти СУБД
            var resultSet = statement3.executeQuery(sql3);
            // Набор данных — это итератор
            // Мы перемещаемся по нему с помощью next() и каждый раз получаем новые значения
            while (resultSet.next()) {
                System.out.println(resultSet.getString("username"));
                System.out.println(resultSet.getString("phone"));
            }
        }
    }
    }
}
