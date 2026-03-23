package org.example;

import java.util.Scanner;

public class Ejercicio10 {
    public static void main (String[]args){
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMENÚ PRINCIPAL:");
            System.out.println("1. Generar fichero de jugadores alojados en el Hotel (H)");
            System.out.println("2. Generar fichero de jugadores de la Comunitat Valenciana (CV)");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":

                    break;
                case "2":

                    break;
                case "3":
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción incorrecta. Inténtalo de nuevo.");
                    break;
            }
        }
        sc.close();
    }
}
