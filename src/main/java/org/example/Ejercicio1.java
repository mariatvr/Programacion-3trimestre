package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        try {
            PrintWriter pw = new PrintWriter(
                    new BufferedWriter(new FileWriter("/home/marvicrui/DAW_mariateresa/ficheros/numNaturales.txt")));
            for(int i=0; i<=100; i++){
                pw.println(i);
            }
            pw.close();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
