/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ut4_accesodatos2;

import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author Kid A
 */
public class UT4_AccesoDatos2 {

    /**
     * @param args the command line arguments
     */
    private static final String URL = "jdbc:mysql://localhost:3306/censo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Scanner reader;

    public static void main(String[] args) {
        reader = new Scanner(System.in);

        try (Connection conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("Conexión exitosa a la base de datos");

            int opcion;
            do {
                System.out.println("---- GESTIÓN DE CENSO DE PERSONAS ----");
                System.out.println("1. Alta de persona");
                System.out.println("2. Baja de persona");
                System.out.println("3. Modificación de persona");
                System.out.println("4. Consulta de personas");
                System.out.println("5. Consulta de persona mediante DNI");
                System.out.println("6. Salir");
                System.out.print("Elige una opción: ");
                System.out.println("\n\n");

                opcion = reader.nextInt();
                limpiarBuffer();

                switch (opcion) {
                    case 1:
                        insertarPersona(conexion);
                        System.out.println("\n\n");
                        break;
                    case 2:
                        eliminarPersona(conexion);
                        System.out.println("\n\n");
                        break;
                    case 3:
                        actualizarPersona(conexion);
                        System.out.println("\n\n");
                        break;
                    case 4:
                        mostrarPersonas(conexion);
                        System.out.println("\n\n");
                        break;
                    case 5:
                        mostrarPersonaPorDNI(conexion);
                        System.out.println("\n\n");
                        break;
                    case 6:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción inválida. Inténtalo de nuevo.");
                        break;
                }
            } while (opcion != 6);
        } catch (SQLException e) {
            System.err.println("Error en la conexión a la base de datos: " + e.getMessage());
        }
    }

    private static void limpiarBuffer() {
        reader.nextLine();
    }

    private static void insertarPersona(Connection conexion) throws SQLException {
        String dni, nombre, fechaNacimiento, direccion;
        int telefono, filasInsertadas;

        System.out.println("---- ALTA DE PERSONA ----");

        System.out.println("Introduce los datos de la persona:");

        System.out.print("DNI: ");
        dni = reader.nextLine();
        dni = dni.toUpperCase();

        System.out.print("Nombre: ");
        nombre = reader.nextLine();

        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
        fechaNacimiento = reader.nextLine();

        System.out.print("Dirección: ");
        direccion = reader.nextLine();

        System.out.print("Teléfono: ");
        telefono = reader.nextInt();
        limpiarBuffer();

        String insertQuery = "INSERT INTO personas (DNI, NOMBRE, FECNAC, DIR, TFNO) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = conexion.prepareStatement(insertQuery);
        insertStatement.setString(1, dni);
        insertStatement.setString(2, nombre);
        insertStatement.setString(3, fechaNacimiento);
        insertStatement.setString(4, direccion);
        insertStatement.setInt(5, telefono);

        filasInsertadas = insertStatement.executeUpdate();

        System.out.println("Se ha insertado correctamente " + filasInsertadas + " fila(s).");
    }

    private static void eliminarPersona(Connection conexion) throws SQLException {
        String dni;
        int filasEliminadas;

        System.out.println("---- BAJA DE PERSONA ----");

        System.out.print("Introduce el DNI de la persona a eliminar: ");
        dni = reader.nextLine();
        dni = dni.toUpperCase();

        String deleteQuery = "DELETE FROM personas WHERE DNI = ?";
        PreparedStatement deleteStatement = conexion.prepareStatement(deleteQuery);
        deleteStatement.setString(1, dni);

        filasEliminadas = deleteStatement.executeUpdate();

        System.out.println("Se ha eliminado correctamente " + filasEliminadas + " fila(s).");
    }

    private static void actualizarPersona(Connection conexion) throws SQLException {
        String dni, nuevoNombre, nuevaFechaNacimiento, nuevaDireccion;
        int nuevoTelefono, filasActualizadas;

        System.out.println("---- MODIFICACIÓN DE PERSONA ----");
        System.out.print("Introduce el DNI de la persona a modificar: ");
        dni = reader.nextLine();
        dni = dni.toUpperCase();

        String selectQuery = "SELECT * FROM personas WHERE DNI = ?";
        PreparedStatement selectStatement = conexion.prepareStatement(selectQuery);
        selectStatement.setString(1, dni);

        ResultSet rs = selectStatement.executeQuery();

        if (rs.next()) {
            System.out.println("Introduce los nuevos datos de la persona:");

            System.out.print("Nombre: ");
            nuevoNombre = reader.nextLine();

            System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
            nuevaFechaNacimiento = reader.nextLine();

            System.out.print("Dirección: ");
            nuevaDireccion = reader.nextLine();

            System.out.print("Teléfono: ");
            nuevoTelefono = reader.nextInt();
            limpiarBuffer();

            String actualizar = "UPDATE personas SET NOMBRE = ?, FECNAC = ?, DIR = ?, TFNO = ? WHERE DNI = ?";
            PreparedStatement updateStatement = conexion.prepareStatement(actualizar);
            updateStatement.setString(1, nuevoNombre);
            updateStatement.setString(2, nuevaFechaNacimiento);
            updateStatement.setString(3, nuevaDireccion);
            updateStatement.setInt(4, nuevoTelefono);
            updateStatement.setString(5, dni);

            filasActualizadas = updateStatement.executeUpdate();

            System.out.println("Se ha actualizado correctamente " + filasActualizadas + " fila(s).");
        } else {
            System.out.println("No se encontró ninguna persona con el DNI proporcionado.");
        }
    }

    private static void mostrarPersonas(Connection conexion) throws SQLException {
        String dni, nombre, fechaNacimiento, direccion;
        int telefono;

        System.out.println("---- CONSULTA DE PERSONAS ----");

        String selectQuery = "SELECT * FROM personas";
        PreparedStatement selectStatement = conexion.prepareStatement(selectQuery);

        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            dni = resultSet.getString("DNI");
            nombre = resultSet.getString("NOMBRE");
            fechaNacimiento = resultSet.getString("FECNAC");
            direccion = resultSet.getString("DIR");
            telefono = resultSet.getInt("TFNO");

            System.out.println("DNI: " + dni);
            System.out.println("Nombre: " + nombre);
            System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
            System.out.println("Dirección: " + direccion);
            System.out.println("Teléfono: " + telefono);
            System.out.println();
        }
    }

    private static void mostrarPersonaPorDNI(Connection conexion) throws SQLException {
        String dni, nombre, fechaNacimiento, direccion;
        int telefono;
        
        System.out.println("---- CONSULTA DE PERSONA POR DNI ----");
        System.out.print("Introduce el DNI de la persona: ");
        dni = reader.nextLine();
        dni = dni.toUpperCase();

        String selectQuery = "SELECT * FROM personas WHERE DNI = ?";
        PreparedStatement selectStatement = conexion.prepareStatement(selectQuery);
        selectStatement.setString(1, dni);

        ResultSet resultSet = selectStatement.executeQuery();
        
        if (resultSet.next()) {
            nombre = resultSet.getString("NOMBRE");
            fechaNacimiento = resultSet.getString("FECNAC");
            direccion = resultSet.getString("DIR");
            telefono = resultSet.getInt("TFNO");

            System.out.println("DNI: " + dni);
            System.out.println("Nombre: " + nombre);
            System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
            System.out.println("Dirección: " + direccion);
            System.out.println("Teléfono: " + telefono);
        } else {
            System.out.println("No se encontró ninguna persona con el DNI proporcionado.");
        }
    }
}
