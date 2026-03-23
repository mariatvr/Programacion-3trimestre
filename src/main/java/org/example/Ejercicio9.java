package org.example;

import java.io.*;
import java.text.DecimalFormat;
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
        Set<LineaProducto> carrito = new HashSet<>();

        System.out.println("=== SUPERMERCADO MARIA TERESA ===");
        System.out.println("___________________________________________________________");
        System.out.println(" avena | garbanzos | tomate | jengibre | quinoa | guisantes");
        System.out.println("___________________________________________________________");
        System.out.println("  2,21 |    2,39   |  1,59  |   3,13   |  4,50  |    1,60  ");
        System.out.println("\nEscriba 'fin' en el producto para terminar la compra.\n");

        boolean comprando = true;

        while (comprando) {
            System.out.println("Producto: ");
            String nombreProd = sc.nextLine().toLowerCase();

            if (nombreProd.equals("fin")) {
                System.out.println("Compra finalizada. Imprimiendo ticket...");
                comprando = false;

            } else {

                if (!catalogo.containsKey(nombreProd)) {
                    System.out.println("Ese producto no está disponible en el supermercado.");
                    continue;
                }

                System.out.print("Cantidad: ");
                int cantidadProd = sc.nextInt();
                sc.nextLine();

                double precioUnidad = catalogo.get(nombreProd);
                LineaProducto nuevoProducto = new LineaProducto(nombreProd, precioUnidad, cantidadProd);

                boolean existe = false;
                for (LineaProducto producto : carrito) {
                    if (producto.equals(nuevoProducto)) {
                        producto.sumarCantidad(cantidadProd);
                        existe = true;
                        break;
                    }
                }

                if (!existe) {
                    carrito.add(nuevoProducto);
                }
            }
        }

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(
                    new BufferedWriter(new FileWriter("ticket.txt")));

            String cabecera = "Producto\t\tPrecio\t\tCantidad\t\tSubtotal";
            System.out.println("\n" + cabecera);
            pw.println(cabecera);

            double total = 0.0;
            DecimalFormat df = new DecimalFormat("#.00");

            String barra = "-----------------------------------------------------";
            System.out.println(barra);
            pw.println(barra);
            for (LineaProducto producto : carrito) {
                if (producto.getProducto().equalsIgnoreCase("garbanzos") || producto.getProducto().equalsIgnoreCase("guisantes") || producto.getProducto().equalsIgnoreCase("jengibre")) {
                    String datoProdu = producto.getProducto() + "\t\t " + producto.getPrecio() + "\t\t    " + producto.getCantidad() + "\t\t\t  " + df.format(producto.getSubtotal());
                    System.out.println(datoProdu);
                    pw.println(datoProdu);
                    total += producto.getSubtotal();
                } else {
                    String datoProdu = producto.getProducto() + "\t\t\t " + producto.getPrecio() + "\t\t    " + producto.getCantidad() + "\t\t\t  " + df.format(producto.getSubtotal());
                    System.out.println(datoProdu);
                    pw.println(datoProdu);
                    total += producto.getSubtotal();
                }
            }
            String parteFinal = "TOTAL:\t\t\t\t\t\t\t\t\t\t  "+df.format(total);
            System.out.println(barra);
            System.out.println(parteFinal);
            pw.println(barra);
            pw.println(parteFinal);


        } catch (IOException e) {
            System.out.println("Error al generar el ticket: " + e.getMessage());
        } finally {
            if (pw != null) {
                pw.close();
            }
            sc.close();
        }
    }
    }
