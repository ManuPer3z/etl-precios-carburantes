package com.unir.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    private final Connection connection;

    public MySqlConnector(String host, String database) {
        try {

            String user = System.getenv("MYSQL_USER");
            String password = System.getenv("MYSQL_PASSWORD");

            // Construir la URL de conexión
            String url = "jdbc:mysql://" + host + ":3306/" + database + "?useSSL=false";

            // Establecer la conexión
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos: " + database);
        } catch (SQLException e) {
            System.err.println("Error al conectar con MySQL");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
