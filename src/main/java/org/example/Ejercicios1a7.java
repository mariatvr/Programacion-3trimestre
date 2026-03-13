package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
    public static void eliminarEspaciosYMayusculas(String nombreArchivo) {
        FileReader fr = null;
        BufferedReader entrada = null;

        try {
            fr = new FileReader(nombreArchivo);
            entrada = new BufferedReader(fr);

            String cadena = entrada.readLine();
            System.out.println("Resultado (Sin espacios y Mayúsculas):");
            while (cadena != null) {
                // Quitamos espacios y pasamos a mayúsculas
                String transformada = cadena.replace(" ", "").toUpperCase();
                System.out.println(transformada);
                cadena = entrada.readLine(); // Leer la siguiente línea
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void ordenarAlfabe(String nombreArchivo){
        String archivoOrigen = "desordenadas.txt";
        String archivoDestino = "ordenadas.txt";

        Scanner entrada = null;
        PrintWriter salida = null;
        ArrayList<String> listaPalabras = new ArrayList<>();

        try {
            entrada = new Scanner(new File(nombreArchivo));

            while(entrada.hasNext()){
                listaPalabras.add(entrada.next());
            }
            System.out.println("Palabras leídas correctamente. Total: " + listaPalabras.size());
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer: No se encuentra el archivo '" + archivoOrigen + "'");
            return;
        } finally {
            if (entrada != null){
                entrada.close();
            }
        }

        Collections.sort(listaPalabras);
        try {
            salida = new PrintWriter(archivoDestino);

            for (String palabra : listaPalabras) {
                salida.println(palabra);
            }
            System.out.println("Fichero guardado y ordenado con éxito en: " + archivoDestino);

        } catch (FileNotFoundException e) {
            System.out.println("Error al escribir: " + e.getMessage());

        } finally {
            if (salida != null) {
                salida.close();
            }
        }
    }


    // --- EJERCICIO 6 ---
    public static void contarCaracteres(String nombreArchivo) {
        FileReader fr = null;
        int vocales = 0, consonantes = 0, numeros = 0;

        try {
            // Lectura carácter a carácter como en la página 5
            fr = new FileReader(nombreArchivo);
            int car = fr.read();

            while (car != -1) {
                char c = (char) car;
                c = Character.toLowerCase(c); // Lo pasamos a minúscula para comparar más fácil

                if (c >= '0' && c <= '9') {
                    numeros++;
                } else if (c >= 'a' && c <= 'z') {
                    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                        vocales++;
                    } else {
                        consonantes++;
                    }
                }
                car = fr.read(); // Leer siguiente carácter
            }
            System.out.println("Vocales: " + vocales);
            System.out.println("Consonantes: " + consonantes);
            System.out.println("Números: " + numeros);

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
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
        Ejercicios1a7.escribirNaturales();
        int resultadoSuma = Ejercicios1a7.obtenerSumaNumerosArchivo("/home/marvicrui/DAW_mariateresa/ficheros/numNaturales.txt");
        System.out.println("La suma de los números es: " + resultadoSuma);
        Ejercicios1a7.escribirPrimos();
        mostrarArchivoPantalla("/home/marvicrui/DAW_mariateresa/ficheros/primos.dat");
        ordenarAlfabe("/home/marvicrui/IdeaProjects/testIDEA/src/main/java/org/example/desordenadas.txt");
    }
}
