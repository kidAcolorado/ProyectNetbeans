/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.KidAProject.ReservaException;
import java.util.ArrayList;

import dto.Vuelo;
import dto.Pasajero;
import dto.Reserva;

import java.sql.*;

/**
 *
 * @author Kid A
 */
public class viajesDAOimpl implements viajesDAO {

    private Connection conn;
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "viajes";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "ThereThere_1420";

    private static final String VUELO_ID = "id";
    private static final String VUELO_ORIGEN = "origen";
    private static final String VUELO_DESTINO = "destino";
    private static final String VUELO_FECHA = "fecha";

    private static final String PASAJERO_ID = "id";
    private static final String PASAJERO_NOMBRE = "nombre";
    private static final String PASAJERO_APELLIDO = "apellido";
    private static final String PASAJERO_FECHA_NACIMIENTO = "fecha_nacimiento";

    private static final String RESERVA_ID = "id";
    private static final String RESERVA_ID_VUELO_FK = "id_vuelo";
    private static final String RESERVA_ID_PASAJERO_FK = "id_pasajero";
    private static final String RESERVA_ASIENTO = "asiento";

    @Override
    public Connection getConn() {
        return conn;
    }

    // MËTODOS DE CONEXIÓN A BASE DE DATOS:
    /**
     * Carga el controlador de la base de datos.
     *
     * @return true si el controlador se carga correctamente, false en caso
     * contrario.
     */
    @Override
    public boolean loadDriver() {
        try {
            System.out.println("Cargando driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver cargado satisfactoriamente");
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR al cargar el driver");
            return false;
        }
    }

    /**
     * Establece una conexión con la base de datos.
     *
     * @return true si la conexión se establece correctamente, false en caso
     * contrario.
     */
    @Override
    public boolean connect() {
        try {
            System.out.println("Creando conexión a base de datos...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Conexión a base de datos satisfactoria");
            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR de conexión a base de datos");
            return false;
        }
    }

    /**
     * Cierra la conexión con la base de datos.
     *
     * @return true si la conexión se cierra correctamente, false en caso
     * contrario.
     */
    @Override
    public boolean close() {
        try {
            System.out.println("Cerrando conexión...");
            conn.close();
            System.out.println("Conexión cerrada satisfactoriamente");
            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al cerrar conexión");
            return false;
        }
    }

    // MÉTODOS CRUD DE VUELO:
    /**
     * Inserta un nuevo vuelo en la base de datos.
     *
     * @param vuelo el objeto Vuelo que se desea insertar.
     * @return true si el vuelo se inserta correctamente, false en caso
     * contrario.
     */
    @Override
    public boolean insertVuelo(Vuelo vuelo) {

        int vuelosInsertados;

        String insertQuery = "INSERT INTO vuelos (origen, destino, fecha) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
            insertStatement.setString(1, vuelo.getOrigen());
            insertStatement.setString(2, vuelo.getDestino());
            insertStatement.setString(3, vuelo.getFecha());

            vuelosInsertados = insertStatement.executeUpdate();

            System.out.println("Se ha insertado correctamente " + vuelosInsertados + " fila(s).");

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al insertar datos");
            return false;
        }
    }

    /**
     * Obtiene todos los vuelos almacenados en la base de datos.
     *
     * @return Una lista de objetos Vuelo que representa los vuelos almacenados.
     * Si ocurre un error, devuelve null.
     */
    @Override
    public ArrayList<Vuelo> getVuelos() {

        ArrayList<Vuelo> listaVuelos = new ArrayList<>();

        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM vuelos");
            while (rs.next()) {
                int id = rs.getInt(VUELO_ID);
                String origen = rs.getString(VUELO_ORIGEN);
                String destino = rs.getString(VUELO_DESTINO);
                String fecha = rs.getString(VUELO_FECHA);

                Vuelo vuelo = new Vuelo(id, origen, destino, fecha);

                listaVuelos.add(vuelo);
            }

            rs.close();

            return listaVuelos;

        } catch (SQLException ex) {
            System.out.println("ERROR getVuelos");
            ex.printStackTrace();

            return null;
        }
    }

    /**
     * Actualiza un vuelo existente en la base de datos con la información
     * proporcionada.
     *
     * @param vuelo El objeto Vuelo con la información actualizada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    @Override
    public boolean updateVuelo(Vuelo vuelo) {

        int vuelosActualizados;

        String updateQuery = "UPDATE vuelos SET origen = ?, destino = ?, fecha = ? WHERE id = ?";
        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setString(1, vuelo.getOrigen());
            updateStatement.setString(2, vuelo.getDestino());
            updateStatement.setString(3, vuelo.getFecha());
            updateStatement.setInt(4, vuelo.getId());

            vuelosActualizados = updateStatement.executeUpdate();

            if (vuelosActualizados == 0) {
                System.out.println("No se han encontrado coincidencias en la base de datos");
            } else {
                System.out.println("Se ha actualizado correctamente " + vuelosActualizados + " fila(s).");
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al actualizar datos");
            return false;
        }
    }

    /**
     * Elimina un vuelo de la base de datos basado en su ID.
     *
     * @param id El ID del vuelo a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    @Override
    public boolean deleteVuelo(int id) {

        int vuelosEliminados;

        String deleteQuery = "DELETE FROM vuelos WHERE id = ?";
        try (PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, id);

            vuelosEliminados = deleteStatement.executeUpdate();

            if (vuelosEliminados == 0) {
                System.out.println("No se han encontrado coincidencias en la base de datos");
            } else {
                System.out.println("Se ha eliminado correctamente " + vuelosEliminados + " fila(s).");
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al eliminar datos");
            return false;
        }
    }

    // MÉTODOS CRUD DE PASAJERO:
    /**
     * Inserta un pasajero en la base de datos.
     *
     * @param pasajero El objeto Pasajero a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    @Override
    public boolean insertPasajero(Pasajero pasajero) {

        int pasajerosInsertados;

        String insertQuery = "INSERT INTO pasajeros (nombre, apellido, fecha_nacimiento) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
            insertStatement.setString(1, pasajero.getNombre());
            insertStatement.setString(2, pasajero.getApellido());
            insertStatement.setString(3, pasajero.getFechaNacimiento());

            pasajerosInsertados = insertStatement.executeUpdate();

            System.out.println("Se ha insertado correctamente " + pasajerosInsertados + " fila(s).");

            return true;

        } catch (SQLException ex) {
            System.out.println("ERROR al insertar datos");
            return false;
        }
    }

    /**
     * Obtiene una lista de pasajeros desde la base de datos.
     *
     * @return Una lista de objetos Pasajero si la operación fue exitosa, o null
     * en caso de error.
     */
    @Override
    public ArrayList<Pasajero> getPasajeros() {

        ArrayList<Pasajero> listaPasajeros = new ArrayList<>();

        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM pasajeros");
            while (rs.next()) {
                int id = rs.getInt(PASAJERO_ID);
                String nombre = rs.getString(PASAJERO_NOMBRE);
                String apellido = rs.getString(PASAJERO_APELLIDO);
                String fechaNacimiento = rs.getString(PASAJERO_FECHA_NACIMIENTO);

                Pasajero pasajero = new Pasajero(id, nombre, apellido, fechaNacimiento);

                listaPasajeros.add(pasajero);
            }

            rs.close();
            return listaPasajeros;

        } catch (SQLException ex) {
            System.out.println("ERROR al obtener los pasajeros");
            ex.printStackTrace();

            return null;
        }
    }

    /**
     * Actualiza los datos de un pasajero en la base de datos.
     *
     * @param pasajero El objeto Pasajero con los nuevos datos a actualizar.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    @Override
    public boolean updatePasajero(Pasajero pasajero) {

        int pasajerosActualizados;

        String updateQuery = "UPDATE pasajeros SET nombre = ?, apellido = ?, fecha_nacimiento = ? WHERE id = ?";
        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setString(1, pasajero.getNombre());
            updateStatement.setString(2, pasajero.getApellido());
            updateStatement.setString(3, pasajero.getFechaNacimiento());
            updateStatement.setInt(4, pasajero.getId());

            pasajerosActualizados = updateStatement.executeUpdate();

            if (pasajerosActualizados == 0) {
                System.out.println("No se han encontrado coincidencias en la base de datos");
            } else {
                System.out.println("Se ha actualizado correctamente " + pasajerosActualizados + " fila(s).");
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al actualizar datos");
            return false;
        }
    }

    /**
     * Elimina un pasajero de la base de datos según su ID.
     *
     * @param id El ID del pasajero a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    @Override
    public boolean deletePasajero(int id) {

        int pasajerosEliminados;

        String deleteQuery = "DELETE FROM pasajeros WHERE id = ?";
        try (PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, id);

            pasajerosEliminados = deleteStatement.executeUpdate();

            if (pasajerosEliminados == 0) {
                System.out.println("No se han encontrado coincidencias en la base de datos");
            } else {
                System.out.println("Se ha eliminado correctamente " + pasajerosEliminados + " fila(s).");
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al eliminar datos");
            return false;
        }
    }

    //MÉTODOS CRUD DE RESERVA:
    /**
     * Inserta una reserva en la base de datos.
     *
     * @param reserva la reserva a insertar.
     * @return true si la reserva se insertó correctamente, false en caso
     * contrario.
     */
    @Override
    public boolean insertReserva(Reserva reserva) {

        int reservasInsertadas;

        String insertQuery = "INSERT INTO reservas (id_vuelo, id_pasajero, asiento) VALUES (?, ?, ?)";
        try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, reserva.getIdVuelo());
            insertStatement.setInt(2, reserva.getIdPasajero());
            insertStatement.setString(3, reserva.getAsiento());

            reservasInsertadas = insertStatement.executeUpdate();

            System.out.println("Se ha insertado correctamente " + reservasInsertadas + " fila(s).");

            return true;

        } catch (SQLException ex) {
            System.out.println("ERROR al insertar datos");
            return false;
        }
    }

    /**
     * Obtiene todas las reservas almacenadas en la base de datos.
     *
     * @return una lista de objetos Reserva que representa las reservas
     * almacenadas. Si ocurre un error, se devuelve null.
     */
    @Override
    public ArrayList<Reserva> getReservas() {

        ArrayList<Reserva> listaReservas = new ArrayList<>();

        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM reservas");
            while (rs.next()) {
                int id = rs.getInt(RESERVA_ID);
                int idVuelo = rs.getInt(RESERVA_ID_VUELO_FK);
                int idPasajero = rs.getInt(RESERVA_ID_PASAJERO_FK);
                String asiento = rs.getString(RESERVA_ASIENTO);

                Reserva reserva = new Reserva(id, idVuelo, idPasajero, asiento);

                listaReservas.add(reserva);
            }

            rs.close();
            return listaReservas;

        } catch (SQLException ex) {
            System.out.println("ERROR al obtener reservas");
            ex.printStackTrace();

            return null;
        }
    }

    /**
     * Actualiza los detalles de una reserva en la base de datos.
     *
     * @param reserva la reserva con los detalles actualizados.
     * @return true si la actualización se realizó correctamente, false si
     * ocurrió un error.
     */
    @Override
    public boolean updateReserva(Reserva reserva) {

        int reservasActualizadas;

        String updateQuery = "UPDATE reservas SET id_vuelo = ?, id_pasajero = ?, asiento = ? WHERE id = ?";
        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, reserva.getIdVuelo());
            updateStatement.setInt(2, reserva.getIdPasajero());
            updateStatement.setString(3, reserva.getAsiento());
            updateStatement.setInt(4, reserva.getId());

            reservasActualizadas = updateStatement.executeUpdate();

            if (reservasActualizadas == 0) {
                System.out.println("No se han encontrado coincidencias en la base de datos");
            } else {
                System.out.println("Se ha actualizado correctamente " + reservasActualizadas + " fila(s).");
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al actualizar datos");
            return false;
        }
    }

    /**
     * Elimina una reserva de la base de datos.
     *
     * @param id el identificador de la reserva a eliminar.
     * @return true si la eliminación se realizó correctamente, false si ocurrió
     * un error.
     */
    @Override
    public boolean deleteReserva(int id) {

        int reservasEliminadas;

        String deleteQuery = "DELETE FROM reservas WHERE id = ?";
        try (PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, id);

            reservasEliminadas = deleteStatement.executeUpdate();

            if (reservasEliminadas == 0) {
                System.out.println("No se han encontrado coincidencias en la base de datos");
            } else {
                System.out.println("Se ha eliminado correctamente " + reservasEliminadas + " fila(s).");
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR al eliminar datos");
            return false;
        }
    }

    //MÉTODOS PARA OTRAS CONSULTAS:
    /**
     * Muestra los vuelos disponibles en función de la fecha de salida, ciudad
     * de origen y ciudad de destino.
     *
     *
     * @param fecha La fecha de salida del vuelo (en formato YYYY-MM-DD).
     * @param origen La ciudad de origen del vuelo.
     * @param destino La ciudad de destino del vuelo.
     *
     *
     */
    @Override
    public void consultaVuelosDisponibles(String fecha, String origen, String destino) {

        // Prepara la consulta SQL para buscar vuelos disponibles
        String query = "SELECT * FROM VUELOS WHERE fecha = ? AND origen = ? AND destino = ?;";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, fecha);
            statement.setString(2, origen);
            statement.setString(3, destino);

            // Ejecuta la consulta
            ResultSet resultSet = statement.executeQuery();

            // Muestra los vuelos encontrados
            System.out.println("Vuelos disponibles:\n");
            while (resultSet.next()) {
                int vueloId = resultSet.getInt(VUELO_ID);
                String vueloOrigen = resultSet.getString(VUELO_ORIGEN);
                String vueloDestino = resultSet.getString(VUELO_DESTINO);
                String vueloFechaSalida = resultSet.getString(VUELO_FECHA);

                System.out.println("Vuelo ID: " + vueloId);
                System.out.println("Origen: " + vueloOrigen);
                System.out.println("Destino: " + vueloDestino);
                System.out.println("Fecha de salida: " + vueloFechaSalida);
                System.out.println("");
            }

        } catch (SQLException ex) {
            String mensaje = "Error al buscar vuelos disponibles: " + ex.getMessage();
            System.out.println(mensaje);
            /* Opcionalmente, registrar el mensaje en archivos de registro u otros medios de comunicación
            Logger.getLogger(UT4_AccesoDatos4.class.getName()).log(Level.SEVERE, null, ex);*/
        }

    }

    /**
     * Muestra la información básica de un vuelo dado su ID.
     *
     *
     * @param idVuelo el ID del vuelo a mostrar
     *
     */
    @Override
    public void consultaInformacionVuelo(int idVuelo) {

        String vueloQuery = "SELECT * FROM VUELOS WHERE id = ?";
        ResultSet vueloResult;
        try (PreparedStatement vueloStatement = conn.prepareStatement(vueloQuery)) {
            vueloStatement.setInt(1, idVuelo);
            vueloResult = vueloStatement.executeQuery();
            if (vueloResult.next()) {
                String vueloOrigen = vueloResult.getString(VUELO_ORIGEN);
                String vueloDestino = vueloResult.getString(VUELO_DESTINO);
                String vueloFechaSalida = vueloResult.getString(VUELO_FECHA);

                System.out.println("Información del vuelo (ID: " + idVuelo + "):");
                System.out.println("Origen: " + vueloOrigen);
                System.out.println("Destino: " + vueloDestino);
                System.out.println("Fecha de salida: " + vueloFechaSalida);
                System.out.println();
            } else {
                System.out.println("No se encontró ningún vuelo con el ID proporcionado.");
            }
            vueloResult.close();

        } catch (SQLException ex) {
            String mensaje = "Error al mostrar información detallada del vuelo: " + ex.getMessage();
            System.out.println(mensaje);
            /* Opcionalmente, registrar el mensaje en archivos de registro u otros medios de comunicación
            Logger.getLogger(UT4_AccesoDatos4.class.getName()).log(Level.SEVERE, null, ex);*/
        }
    }

    /**
     * Muestra los pasajeros y sus reservas en un vuelo dado su ID.
     *
     *
     * @param vueloId el ID del vuelo a mostrar
     *
     */
    @Override
    public void consultaPasajerosReservasVuelo(int vueloId) {

        String reservasQuery = "SELECT P.nombre, P.apellido, R.asiento "
                + "FROM VUELOS V "
                + "INNER JOIN RESERVAS R ON V.id = R.id_vuelo "
                + "INNER JOIN PASAJEROS P ON P.id = R.id_pasajero "
                + "WHERE V.id = ?";
        try (PreparedStatement reservasStatement = conn.prepareStatement(reservasQuery)) {
            reservasStatement.setInt(1, vueloId);

            ResultSet reservasResult = reservasStatement.executeQuery();

            System.out.println("Pasajeros con reservas en el vuelo:\n");
            while (reservasResult.next()) {
                String pasajeroNombre = reservasResult.getString(PASAJERO_NOMBRE);
                String pasajeroApellido = reservasResult.getString(PASAJERO_APELLIDO);
                String asiento = reservasResult.getString(RESERVA_ASIENTO);

                System.out.println("Nombre: " + pasajeroNombre + " " + pasajeroApellido);
                System.out.println("Asiento: " + asiento);
                System.out.println();
            }

        } catch (SQLException ex) {
            String mensaje = "Error al mostrar información detallada del vuelo: " + ex.getMessage();
            System.out.println(mensaje);
            /* Opcionalmente, registrar el mensaje en archivos de registro u otros medios de comunicación
            Logger.getLogger(UT4_AccesoDatos4.class.getName()).log(Level.SEVERE, null, ex);*/
        }
    }

    /**
     *
     * Realiza una reserva en un vuelo seleccionado, indicando el asiento y el
     * ID del pasajero.
     *
     * Se verifica previamente si el asiento está ocupado en el vuelo
     * seleccionado para mantener la integridad de los datos.
     *
     * @param idVuelo El ID del vuelo en el que se realiza la reserva.
     *
     * @param asiento El número de asiento a reservar.
     *
     * @param idPasajero El ID del pasajero que realiza la reserva.
     *
     *
     */
    @Override
    public void checkAsientoYRealizarReserva(int idVuelo, int idPasajero, String asiento) {
        try {
            // Verificar si el asiento ya está ocupado en el vuelo seleccionado
            if (asientoOcupado(idVuelo, asiento)) {
                throw new ReservaException("El asiento " + asiento + " ya está ocupado.");
            }

            // Realizar la reserva
            String query = "INSERT INTO Reservas (id_vuelo, id_pasajero, asiento) VALUES (?, ?, ?);";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, idVuelo);
                statement.setInt(2, idPasajero);
                statement.setString(3, asiento);

                int reservasInsertadas = statement.executeUpdate();
                
                
                System.out.println("Se ha realizado correctamente " + reservasInsertadas + " reserva(s).");
            }
        } catch (SQLException | ReservaException ex) {
            String mensaje = "Error al realizar la reserva del vuelo: " + ex.getMessage();
            System.out.println(mensaje);
            /* Opcionalmente, registrar el mensaje en archivos de registro u otros medios de comunicación
            Logger.getLogger(UT4_AccesoDatos4.class.getName()).log(Level.SEVERE, null, ex);*/
        }

    }

    /**
     *
     * Cancela una reserva especificada por el número de reserva.
     *
     *
     *
     * @param numeroReserva El número de reserva a cancelar.
     *
     *
     */
    @Override
    public void cancelarReserva(int numeroReserva) {

        String query = "DELETE FROM Reservas WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, numeroReserva);
            int reservaCancelada = stmt.executeUpdate();

            if (reservaCancelada > 0) {
                System.out.println("La reserva con número " + numeroReserva + " ha sido cancelada.");
            } else {
                System.out.println("No se encontró la reserva con el número " + numeroReserva + ".");
            }

        } catch (SQLException ex) {
            String mensaje = "Error al cancelar reserva: " + ex.getMessage();
            System.out.println(mensaje);
            /* Opcionalmente, registrar el mensaje en archivos de registro u otros medios de comunicación
            Logger.getLogger(UT4_AccesoDatos4.class.getName()).log(Level.SEVERE, null, ex);*/
        }
    }

    /**
     *
     * Verifica si el asiento está ocupado en el vuelo seleccionado.
     *
     * @param idVuelo El ID del vuelo.
     *
     * @param asiento El número de asiento.
     *
     * @return true si el asiento está ocupado, false si está disponible.
     *
     * @throws SQLException Si ocurre algún error al ejecutar la consulta SQL.
     */
    private boolean asientoOcupado(int idVuelo, String asiento) throws SQLException {
        int contador;
        boolean flag = false;

        // Consulta para verificar si el asiento está ocupado en el vuelo seleccionado
        String query = "SELECT COUNT(*) FROM RESERVAS WHERE id_vuelo = ? AND asiento = ?;";

        ResultSet resultSet;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idVuelo);
            statement.setString(2, asiento);
            resultSet = statement.executeQuery();
            //Nos movemos al primer registro:
            resultSet.next();
            //Cantidad de registros que cumplen las condiciones:
            contador = resultSet.getInt(1);
            if (contador > 0) {
                flag = true;
            }
        }
        resultSet.close();

        return flag;
    }
}
