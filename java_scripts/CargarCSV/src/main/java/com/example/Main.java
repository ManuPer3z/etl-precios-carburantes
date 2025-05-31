package com.example;

import com.unir.config.MySqlConnector;
import com.example.handler.MySqlHandler;
import com.example.reader.CsvReader;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String host = "localhost";
        String database = "estaciones_db";

        try {
            // Conexión a la base de datos
            MySqlConnector connector = new MySqlConnector(host, database);
            Connection connection = connector.getConnection();
            MySqlHandler handler = new MySqlHandler(connection);
            CsvReader reader = new CsvReader();

            // 1. Cargar Provincias
            System.out.println("Cargando Provincias...");
            List<String[]> provincias = reader.readCsv("C:\\Users\\nicke\\Labo1\\csv\\provincias_actualizado.csv");
            for (String[] provincia : provincias) {
                if (!handler.existeProvincia(Integer.parseInt(provincia[0]))) {
                    handler.insertProvincia(Integer.parseInt(provincia[0]), provincia[1]);
                } else {
                    System.out.println("Provincia con ID " + provincia[0] + " ya existe. No se insertará.");
                }
            }
            System.out.println("Provincias cargadas correctamente.");

            // 2. Cargar Municipios
            System.out.println("Cargando Municipios...");
            List<String[]> municipios = reader.readCsv("C:\\Users\\nicke\\Labo1\\csv\\municipios_actualizado.csv");
            for (String[] municipio : municipios) {
                if (!handler.existeMunicipio(Integer.parseInt(municipio[0]))) {
                    handler.insertMunicipio(Integer.parseInt(municipio[0]), municipio[1], Integer.parseInt(municipio[2]));
                } else {
                    System.out.println("Municipio con ID " + municipio[0] + " ya existe. No se insertará.");
                }
            }
            System.out.println("Municipios cargados correctamente.");

            // 3. Cargar Localidades
            System.out.println("Cargando Localidades...");
            List<String[]> localidades = reader.readCsv("C:\\Users\\nicke\\Labo1\\csv\\localidades_actualizado.csv");
            for (String[] localidad : localidades) {
                if (!handler.existeLocalidad(Integer.parseInt(localidad[0]))) {
                    handler.insertLocalidad(Integer.parseInt(localidad[0]), localidad[1], Integer.parseInt(localidad[2]));
                } else {
                    System.out.println("Localidad con ID " + localidad[0] + " ya existe. No se insertará.");
                }
            }
            System.out.println("Localidades cargadas correctamente.");

            // 4. Cargar Estaciones
            System.out.println("Cargando Estaciones...");
            List<String[]> estaciones = reader.readCsv("C:\\Users\\nicke\\Labo1\\csv\\estaciones_actualizado.csv");
            for (String[] estacion : estaciones) {
                if (!handler.existeEstacion(Integer.parseInt(estacion[0]))) {
                    handler.insertEstacion(
                            Integer.parseInt(estacion[0]),
                            estacion[1],
                            estacion[2],
                            Double.parseDouble(estacion[3]),
                            Double.parseDouble(estacion[4]),
                            estacion[5],
                            estacion[6],
                            estacion[7],
                            estacion[8],
                            estacion[9],
                            Integer.parseInt(estacion[10])
                    );
                } else {
                    System.out.println("Estación con ID " + estacion[0] + " ya existe. No se insertará.");
                }
            }
            System.out.println("Estaciones cargadas correctamente.");

            // 5. Cargar Combustibles
            System.out.println("Cargando Combustibles...");
            List<String[]> combustibles = reader.readCsv("C:\\Users\\nicke\\Labo1\\csv\\combustibles_actualizado.csv");
            for (String[] combustible : combustibles) {
                if (!handler.existeCombustible(Integer.parseInt(combustible[0]))) {
                    handler.insertCombustible(Integer.parseInt(combustible[0]), combustible[1]);
                } else {
                    System.out.println("Combustible con ID " + combustible[0] + " ya existe. No se insertará.");
                }
            }
            System.out.println("Combustibles cargados correctamente.");

            // 6. Cargar Precios
            System.out.println("Cargando Precios...");
            List<String[]> precios = reader.readCsv("C:\\Users\\nicke\\Labo1\\csv\\precios_actualizado.csv");
            for (String[] precio : precios) {
                try {
                    // Validar número de columnas
                    if (precio.length != 5) {
                        System.err.println("Fila con columnas incorrectas: " + String.join(",", precio));
                        continue; // Saltar a la siguiente fila
                    }

                    int idEstacion = Integer.parseInt(precio[1].trim());
                    int idCombustible = Integer.parseInt(precio[2].trim());
                    String fechaTomaDatos = precio[3].trim();
                    double precioValor = Double.parseDouble(precio[4].trim());

                    // Verificar si `id_estacion` existe en `Estaciones`
                    if (!handler.existeEstacion(idEstacion)) {
                        System.err.println("Estación con ID " + idEstacion + " no existe. Fila ignorada: " + String.join(",", precio));
                        continue; // Saltar a la siguiente fila
                    }

                    // Validar existencia en `Precios`
                    if (!handler.existePrecio(idEstacion, idCombustible, fechaTomaDatos)) {
                        handler.insertPrecio(idEstacion, idCombustible, fechaTomaDatos, precioValor);
                    } else {
                        System.out.println("Precio con datos [" + idEstacion + ", " + idCombustible + ", " + fechaTomaDatos + "] ya existe. No se insertará.");
                    }
                } catch (Exception e) {
                    System.err.println("Error en la fila del CSV de Precios: " + String.join(",", precio));
                    e.printStackTrace();
                }
            }
            System.out.println("Precios cargados correctamente.");


            // 7. Cargar Horarios
            System.out.println("Cargando Horarios...");
            List<String[]> horarios = reader.readCsv("C:\\Users\\nicke\\Labo1\\csv\\horarios_actualizado.csv");
            for (String[] horario : horarios) {
                try {
                    // Validar número de columnas (debe ser 4)
                    if (horario.length != 4) {
                        System.err.println("Fila con columnas incorrectas: " + String.join(",", horario));
                        continue; // Saltar a la siguiente fila
                    }

                    // Ignorar la primera columna (id_horario)
                    int idEstacion = Integer.parseInt(horario[1].trim());
                    String horarioValor = horario[2].trim();
                    String tipoServicio = horario[3].trim();

                    // Verificar si `id_estacion` existe en `Estaciones`
                    if (!handler.existeEstacion(idEstacion)) {
                        System.err.println("Estación con ID " + idEstacion + " no existe. Fila ignorada: " + String.join(",", horario));
                        continue; // Saltar a la siguiente fila
                    }

                    // Validar existencia en `Horarios`
                    if (!handler.existeHorario(idEstacion, horarioValor)) {
                        handler.insertHorario(idEstacion, horarioValor, tipoServicio);
                    } else {
                        System.out.println("Horario con datos [" + idEstacion + ", " + horarioValor + "] ya existe. No se insertará.");
                    }
                } catch (Exception e) {
                    System.err.println("Error en la fila del CSV de Horarios: " + String.join(",", horario));
                    e.printStackTrace();
                }
            }
            System.out.println("Horarios cargados correctamente.");

        } catch (Exception e) {
            System.err.println("Error durante la carga de datos:");
            e.printStackTrace();
        }
    }
}
