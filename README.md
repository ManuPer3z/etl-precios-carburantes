# Análisis y Carga de Datos del Ministerio de Carburantes

Este proyecto realiza un proceso ETL completo sobre los datos oficiales del Ministerio de Industria sobre estaciones de servicio y precios de carburantes en España.

## 🧰 Tecnologías utilizadas

- **Python**: limpieza y transformación de datos (ETL).
- **Java + JDBC**: carga de datos formateados en una base de datos relacional MySQL.
- **MySQL**: motor de base de datos.
- **Spark Structured Streaming (PySpark)**: simulación de flujo de precios en tiempo real.
- **Jupyter Notebooks**: exploración, desarrollo y visualización.
- **DBeaver**: consultas SQL y modelo relacional.

---

## 📁 Estructura del repositorio

app_gasolinas/
├── Limpieza.ipynb # Código de limpieza y formateo con pandas
├── spark_streaming.ipynb # Simulación de precios en streaming con PySpark
├── scripts_java/ # Código Java para insertar en MySQL
├── datos/csv/ # CSVs formateados
├── datos/input/ # Carpeta de entrada para Spark Streaming
├── ddl/ # Scripts SQL para crear tablas
├── consultas_sql/ # Consultas sobre los datos

---

## 🚀 Qué hace el proyecto

1. Limpia y estructura los datos del Ministerio.
2. Normaliza los precios de los combustibles.
3. Carga los datos limpios en una base de datos relacional.
4. Ejecuta un **streaming simulado** de precios para análisis en tiempo real.

---

## 📦 Cómo ejecutar el streaming

1. Instala PySpark: `pip install pyspark`
2. Ejecuta el notebook `spark_streaming.ipynb`
3. Ve copiando CSVs de ejemplo en la carpeta `datos/input/` para simular datos en vivo.

---

## 📸 Modelo relacional



---

## 🧠 Autor

Manuel Pérez Luque  
