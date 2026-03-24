package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ejercicio11 {
    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMENÚ:");
            System.out.println("1 - Mostrar clasificación final posición, nombre, elo fide, puntos");
            System.out.println("2 - Salir del menú");
            System.out.print("Elige una opción del menú: ");

            String opcion = sc.nextLine();


            switch (opcion) {
                case "1":
                    Map<String, String> diccionarioNombres = new HashMap<>();

                    BufferedReader brJugadores = null;
                    BufferedReader brCsv = null;
                    PrintWriter pw = null;

                    try {
                        brJugadores = new BufferedReader(new FileReader("jugadores.txt"));
                        String lineaJug = brJugadores.readLine();

                        while (lineaJug != null) {
                            String[] camposJug = lineaJug.split(";");
                            if (camposJug.length >= 3) {
                                String rankingId = camposJug[0].trim();
                                String nombre = camposJug[2].trim();
                                diccionarioNombres.put(rankingId, nombre);
                            }
                            lineaJug = brJugadores.readLine();
                        }
                        brJugadores.close();


                        brCsv = new BufferedReader(new FileReader("tablaclasificación.csv"));
                        pw = new PrintWriter(new BufferedWriter(new FileWriter("clasificFinal.txt")));


                        for (int i = 0; i < 5; i++) {
                            brCsv.readLine();
                        }

                        String formato = "%-10s %-35s %-10s %-10s";
                        String cabecera = String.format(formato, "posición", "nombre", "ELO FIDE", "puntos");

                        pw.println(cabecera);

                        String lineaCsv = brCsv.readLine();

                        while (lineaCsv != null) {
                            String[] camposCsv = lineaCsv.split(";");

                            if (camposCsv.length >= 11) {
                                String posicion = camposCsv[0].trim();
                                String ranID = camposCsv[1].trim();
                                String puntos = camposCsv[3].trim().replace(",", ".");
                                String eloFide = camposCsv[10].trim();

                                String nombreJugador = diccionarioNombres.get(ranID);


                                if (nombreJugador == null) {
                                    nombreJugador = "Desconocido";
                                }

                                String fila = String.format(formato, posicion, nombreJugador, eloFide, puntos);
                                pw.println(fila);
                            }
                            lineaCsv = brCsv.readLine();
                        }

                        System.out.println("\n(Listado completado y guardado en clasificFinal.txt)");

                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        if (pw != null) pw.close();
                        try {
                            if (brCsv != null) brCsv.close();
                        } catch (IOException e) {
                            System.out.println("Error al cerrar: " + e.getMessage());
                        }
                    }
                    break;
                case "2":
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                    continue;
                default:
                    System.out.println("Opción incorrecta. Inténtalo de nuevo.");
                    continue;
            }
        }
    }
}
