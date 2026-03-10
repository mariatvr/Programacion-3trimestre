package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    static void main (String[]args){
        String url = "jdbc:mariadb://localhost:3306/pruebas";
        String user="admin";
        String password="admin";
        try{
            Connection cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Estado de la conexion");
            System.out.println(cnx.isClosed()?"cerrada":"abierta");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
