package org.example;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Ejercicio9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Diccionario para el catálogo de productos y precios
        Map<String, Double> catalogo = new HashMap<>();
        catalogo.put("avena", 2.21);
        catalogo.put("garbanzos", 2.39);
        catalogo.put("tomate", 1.59);
        catalogo.put("jengibre", 3.13);
        catalogo.put("quinoa", 4.50);
        catalogo.put("guisantes", 1.60);

        // 2. Set para almacenar la compra que realiza el usuario
        Set<Ticket> carrito = new HashSet<>();

        System.out.println("=== SUPERMERCADO MARIA TERESA ===");
        System.out.println("Escriba 'fin' en el producto para terminar la compra.\n");

        if (args.length != 2) {
            System.out.println("Error: Debes indicar el archivo y la palabra a buscar.");
            return;
        }

        String producto = args[0];
        String cantidad = args[1];
    }
}
