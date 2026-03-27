package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class EjemploPersona {
    static Scanner sc;
    static Connection cnx;

    static {
        try {
            cnx = getConnexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            //Connection cnx = getConnexion();
            sc = new Scanner(System.in);
            int opcion;
            do {
                menu();
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        ListarTodos();
                        break;
                    case 2:
                        listarPersona();
                        break;
                    case 3:
                        altaPersona();
                        break;
                    case 4:
                       actualizarPersona();
                       break;
                    case 5:
                        bajaPersona();
                        break;
                }
            } while (opcion != 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } //main
    private static Connection getConnexion() throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/demo_java";
        String user = "admin";
        String password = "admin";
        return DriverManager.getConnection(url, user, password);
    }

    public static void menu() {

        System.out.println("SISTEMA DE GESTIÓN DE PERSONAS");
        System.out.println("===============================");
        System.out.println("0. Salir");
        System.out.println("1. Listar todos las personas");
        System.out.println("2. Listar persona por su ID");
        System.out.println("3. Insertar una nueva persona");
        System.out.println("4. Actualizar una persona");
        System.out.println("5. Eliminar una persona");
    }
    private static void ListarTodos() throws SQLException {
        //Statement stm = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY))
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * from personas");
            while (rs.next()) {
                int id =rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaDenacimiento = rs.getDate("fechaDenacimiento");
                LocalDate fechaDenacimientoLD = fechaDenacimiento.toLocalDate();

                //System.out.printf("%s %s\t \t(%s) \n" ,nombre,apellido,fechaDenacimiento);
                // saldría 1966-10-07
                // hay que darle formato local y estilo de presentación:

                System.out.printf("%d %s %s\t \t(%s) \n",id, nombre, apellido, fechaDenacimientoLD.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            }
            rs.close();
            stm.close();

    }

    private static void actualizarPersona() {
        System.out.println("Actualización de una persona");
        System.out.print("Introduzca el ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Persona p1 = null;
        try {
            p1 = findByPk(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (p1 == null) {
            System.out.println("La persona a modificar no está registrado en la base de datos");
        } else {
            System.out.print("Introduzca el nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Introduzca los apellidos: ");
            String apellidos = sc.nextLine();
            System.out.print("Introduzca la fecha de nacimiento (dd/mm/aaaa) : ");
            String strFecha = sc.nextLine();
            LocalDate fecha = LocalDate.parse(strFecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            p1.setNombre(nombre);
            p1.setApellido(apellidos);
            p1.setFechaDenacimiento(fecha);
            try {
                update(p1);
                System.out.println("Registro actualizado");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        System.out.println("");
    }
    private static void listarPersona() {
        System.out.println("Datos una persona");
        System.out.print("Introduzca el ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Persona p1 = null;
        try {
            p1 = findByPk(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (p1 == null) {
            System.out.println("La persona a consultar no está registrado en la base de datos");
        } else {

            String nombre = p1.getNombre();
            String apellidos = p1.getApellido();
            //LocalDate fecha = LocalDate.parse(strFecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fecha = p1.getFechaDenacimiento();
            System.out.printf("%d %s %s\t \t(%s) \n",id, nombre, apellidos,fecha.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        }
        System.out.println("");
    }
    private static Persona findByPk(int id) throws SQLException {
                PreparedStatement ps = cnx.prepareStatement("SELECT * FROM personas WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Persona result = null;
        if (rs.next()) {
            result = new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),
                    rs.getDate("fechaDenacimiento").toLocalDate());
        }
        return result;
    }
    private static void update(Persona p) throws SQLException {

        if (p.getId() == 0)
            return;
        PreparedStatement ps = cnx.prepareStatement(
                "UPDATE personas SET nombre = ?, apellido = ?, fechaDenacimiento = ? WHERE id = ?");
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getApellido());
        ps.setDate(3, Date.valueOf(p.getFechaDenacimiento()));
        ps.setInt(4, p.getId());
        ps.executeUpdate();
        ps.close();
    }
    private static void bajaPersona() throws SQLException {
        System.out.println("Baja de una persona");
        System.out.print("Introduzca el ID: ");
        int id = Integer.parseInt(sc.nextLine());
        if (id <= 0)
            return;
        Connection cnx = getConnexion();
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM personas WHERE id = ?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        // Si encuentra fila evito excepciones --> se podría hacer directamente DELETE
        // Aquí se deberían presentar los datos y preguntar confirmación de baja

        if (rs.next())
        {
            PreparedStatement ps1 = cnx.prepareStatement("DELETE FROM personas WHERE id = ?");
            ps1.setInt(1,id);
            ps1.executeUpdate();
            ps1.close();}
        ps.close();

    }
    private static  void altaPersona() throws SQLException, IOException {

        PreparedStatement ps = cnx.prepareStatement(
                "INSERT INTO personas (nombre, apellido, fechaDenacimiento) VALUES (?, ?, ?)");

        // Utilizar esta clase para leer de la entrada exige una claúsula Catch (IO Exception)
        // Es una alternativa a la clase Scanner
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nombre: ");
        String nom = br.readLine();
        System.out.println("Apellidos: ");
        String ape = br.readLine();
        System.out.println("Fecha de nacimiento (dd/mm/aaaa) : ");
        String strFecha = br.readLine();
        LocalDate fecha = LocalDate.parse(strFecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        ps.setString(1, nom);
        ps.setString(2, ape);
        ps.setDate(3, Date.valueOf(fecha));
        ps.executeUpdate();
        ps.close();
    }


    }

