package org.example;

import java.io.*;
import java.util.Scanner;

public class Ejercicios1a7 {
    //EJERCICIO 1
    public static void escribirNaturales() {
        try {
            PrintWriter pw = new PrintWriter(
                    new BufferedWriter(new FileWriter("/home/marvicrui/DAW_mariateresa/ficheros/numNaturales.txt")));
            for (int i = 0; i <= 100; i++) {
                pw.println(i);
            }
            pw.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    //EJERCICIO 2
    public static int obtenerSumaNumerosArchivo(String nombreArchivo) {
        int suma = 0;
        File f = new File(nombreArchivo);

        try (Scanner entrada = new Scanner(f)) {
            while (entrada.hasNextInt()) {
                int numero = entrada.nextInt();
                suma = suma + numero;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return suma;
    }

    //Metodo auxiliar
    private static boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= numero / 2; i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    //EJERCICIO 3 (lo de leer el fichero y mostrar por pantalla lo junto con el 7)
    public static void escribirPrimos() {
        try {
            PrintWriter pw = new PrintWriter(
                    new BufferedWriter(new FileWriter("/home/marvicrui/DAW_mariateresa/ficheros/primos.dat")));
            for (int i = 1; i <= 500; i++) {
                if (esPrimo(i)) pw.println(i);
            }
            pw.close();
            System.out.println("Fichero primos.dat escrito.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    //EJERCICIO 4
    public static void sinEspacios(String nombreFichero) {
        FileReader fr = null;
        BufferedReader entrada;

        try {
            fr = new FileReader(nombreFichero);
            entrada = new BufferedReader(fr);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    //EJERCICIO 7
    public static void mostrarArchivoPantalla(String nombreArchivo) {
        FileReader fr = null;
        BufferedReader entrada;

        try {
            fr = new FileReader(nombreArchivo);
            entrada = new BufferedReader(fr);

            String cadena = entrada.readLine();
            while (cadena != null) {
                System.out.println(cadena);
                cadena = entrada.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

        public static void main(String[] args) {
        escribirNaturales();
        int resultadoSuma = obtenerSumaNumerosArchivo("/home/marvicrui/DAW_mariateresa/ficheros/numNaturales.txt");
        System.out.println("La suma de los números es: " + resultadoSuma);
        escribirPrimos();
        mostrarArchivoPantalla("/home/marvicrui/DAW_mariateresa/ficheros/primos.dat");
    }
}
