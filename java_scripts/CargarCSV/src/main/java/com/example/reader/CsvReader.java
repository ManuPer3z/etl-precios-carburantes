package com.example.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    /**
     * Método para leer un archivo CSV y devolver una lista de registros.
     * Ignora la primera línea (cabecera) del archivo.
     *
     * @param filePath Ruta al archivo CSV
     * @return Lista de registros (cada registro es un array de Strings)
     */
    public List<String[]> readCsv(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // Para ignorar la cabecera
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Ignorar la primera línea
                    continue;
                }
                String[] values = line.split(","); // Cambiar el delimitador si no es una coma
                records.add(values);
            }
            System.out.println("Archivo CSV leído correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
