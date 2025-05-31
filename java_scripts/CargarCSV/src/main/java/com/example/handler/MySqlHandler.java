package com.example.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlHandler {

    private final Connection connection;

    public MySqlHandler(Connection connection) {
        this.connection = connection;
    }

    // 1. Provincias
    public void insertProvincia(int id, String nombre) throws SQLException {
        String query = "INSERT INTO Provincias (id_provincia, nombre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        }
    }

    public boolean existeProvincia(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM Provincias WHERE id_provincia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // 2. Municipios
    public void insertMunicipio(int id, String nombre, int idProvincia) throws SQLException {
        String query = "INSERT INTO Municipios (id_municipio, nombre, id_provincia) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setInt(3, idProvincia);
            stmt.executeUpdate();
        }
    }

    public boolean existeMunicipio(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM Municipios WHERE id_municipio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // 3. Localidades
    public void insertLocalidad(int id, String nombre, int idMunicipio) throws SQLException {
        String query = "INSERT INTO Localidades (id_localidad, nombre, id_municipio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setInt(3, idMunicipio);
            stmt.executeUpdate();
        }
    }

    public boolean existeLocalidad(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM Localidades WHERE id_localidad = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // 4. Estaciones
    public void insertEstacion(int id, String direccion, String codigoPostal, double latitud, double longitud,
                               String tipo, String rotulo, String tipoVenta, String rem, String margen, int idLocalidad) throws SQLException {
        String query = "INSERT INTO Estaciones (id_estacion, direccion, codigo_postal, latitud, longitud, tipo, rotulo, tipo_venta, rem, margen, id_localidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, direccion);
            stmt.setString(3, codigoPostal);
            stmt.setDouble(4, latitud);
            stmt.setDouble(5, longitud);
            stmt.setString(6, tipo);
            stmt.setString(7, rotulo);
            stmt.setString(8, tipoVenta);
            stmt.setString(9, rem);
            stmt.setString(10, margen);
            stmt.setInt(11, idLocalidad);
            stmt.executeUpdate();
        }
    }

    public boolean existeEstacion(int idEstacion) throws SQLException {
        String query = "SELECT COUNT(*) FROM Estaciones WHERE id_estacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idEstacion);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }


    // 5. Combustibles
    public void insertCombustible(int id, String nombre) throws SQLException {
        String query = "INSERT INTO Combustibles (id_combustible, nombre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        }
    }

    public boolean existeCombustible(int id) throws SQLException {
        String query = "SELECT COUNT(*) FROM Combustibles WHERE id_combustible = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // 6. Precios
    public void insertPrecio(int idEstacion, int idCombustible, String fechaTomaDatos, double precio) throws SQLException {
        String query = "INSERT INTO Precios (id_estacion, id_combustible, fecha_toma_datos, precio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idEstacion);
            stmt.setInt(2, idCombustible);
            stmt.setDate(3, java.sql.Date.valueOf(fechaTomaDatos));
            stmt.setDouble(4, precio);
            stmt.executeUpdate();
        }
    }

    public boolean existePrecio(int idEstacion, int idCombustible, String fechaTomaDatos) throws SQLException {
        String query = "SELECT COUNT(*) FROM Precios WHERE id_estacion = ? AND id_combustible = ? AND fecha_toma_datos = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idEstacion);
            stmt.setInt(2, idCombustible);
            stmt.setDate(3, java.sql.Date.valueOf(fechaTomaDatos)); // Asegura el formato
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }


    // 7. Horarios
    public void insertHorario(int idEstacion, String horario, String tipoServicio) throws SQLException {
        String query = "INSERT INTO Horarios (id_estacion, horario, tipo_servicio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idEstacion);
            stmt.setString(2, horario);
            stmt.setString(3, tipoServicio);
            stmt.executeUpdate();
        }
    }

    public boolean existeHorario(int idEstacion, String horario) throws SQLException {
        String query = "SELECT COUNT(*) FROM Horarios WHERE id_estacion = ? AND horario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idEstacion);
            stmt.setString(2, horario);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
