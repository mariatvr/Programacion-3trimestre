package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//MODIFICADO EL EJEMPLO DE LOS APUNTES PARA HACERLO DE LA FORMA EFICIENTE RECOMENDADA:
// Printwriter pw = new PrintWriter(new BufferWriter(new FileWriter("nombrefichero")));
public class Escritura1b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            PrintWriter pw = new PrintWriter(
                    new BufferedWriter(new FileWriter("/home/marvicrui/DAW_mariateresa/ficheros/datos.txt")));
            System.out.println("Introduce texto. Para acabar introduce la cadena FIN:");
            String cadena;
            cadena = sc.nextLine();
            while (!cadena.equalsIgnoreCase("FIN")) {
                pw.write(cadena);
                cadena = sc.nextLine();
            }
            pw.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
