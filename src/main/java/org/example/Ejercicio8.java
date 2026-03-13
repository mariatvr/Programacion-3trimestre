package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Error: Debes indicar el archivo y la palabra a buscar.");
            return;
        }

        String nombreArchivo = args[0];
        String palabraBuscada = args[1];
        int contador = 0;

        Scanner entrada = null;

        try {
            entrada = new Scanner(new File(nombreArchivo));
            while (entrada.hasNext()) {
                String palabraLeida = entrada.next();
                if (palabraLeida.equalsIgnoreCase(palabraBuscada)) contador++;
            }
            System.out.println("La palabra '" + palabraBuscada + "' aparece " + contador + " veces.");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {

            if (entrada != null) {
                entrada.close();
            }
        }
    }
}