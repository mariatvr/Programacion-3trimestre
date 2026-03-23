package org.example;

import java.io.*;
import java.util.Scanner;

public class Ejercicio10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("=== GESTOR DE TORNEO DE JUGADORES ===");

        while (continuar) {
            System.out.println("\nMENÚ PRINCIPAL:");
            System.out.println("1. Generar fichero de alojados en el Hotel (H)");
            System.out.println("2. Generar fichero de federados en la CV (CV)");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            String opcion = sc.nextLine();

            // Variables vacías que rellenaremos según lo que elija el usuario
            String archivoSalida = "";
            String filtroBuscado = "";

            switch (opcion) {
                case "1":
                    archivoSalida = "alojados.txt";
                    filtroBuscado = "H";
                    break;
                case "2":
                    archivoSalida = "jugadoresCV.txt";
                    filtroBuscado = "CV";
                    break;
                case "3":
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                    continue;
                default:
                    System.out.println("Opción incorrecta. Inténtalo de nuevo.");
                    continue;
            }


            BufferedReader br = null;
            FileReader fr = null;
            PrintWriter pw = null;
            int contador = 0;


            try {
                fr = new FileReader("jugadores.txt");
                br = new BufferedReader(fr);
                pw = new PrintWriter(new BufferedWriter(new FileWriter(archivoSalida)));

                String linea = br.readLine();

                while (linea != null) {
                    String[] campos = linea.split(";");

                    if (campos.length >= 2) {

                        String info = campos[campos.length - 1].trim();

                        if (info.toUpperCase().contains(filtroBuscado.toUpperCase())) {
                            String ranking = campos[0].trim();
                            String nombre = campos[2].trim();

                            pw.println("Ranking: " + ranking + " - Nombre: " + nombre);
                            contador++;
                        }
                    }
                    linea = br.readLine();
                }

                System.out.println("¡Éxito! Se han guardado " + contador + " jugadores en '" + archivoSalida + "'.");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                if (pw != null) pw.close();

                try {
                    if (br != null) br.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        sc.close();
    }
}
