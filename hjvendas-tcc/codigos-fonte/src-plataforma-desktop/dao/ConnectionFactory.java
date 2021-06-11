package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection criarConexao() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/HJVendas",
                    "postgres",
                    "impacto");
        } catch (ClassNotFoundException ex) {
            System.out.println("erro de classe");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return connection;
    }
}
