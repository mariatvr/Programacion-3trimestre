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
        Map<String, Double> catalogo = new HashMap<>();
        Set<LineaProducto> carrito = new HashSet<>();

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader("C:/Users/mterv/IdeaProjects/Programacion-3trimestre/src/main/java/org/example/productos.txt");
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while (linea != null) {
                String[] partes = linea.split(";");

                if (partes.length == 2) {
                    String nombre = partes[0].trim().toLowerCase();
                    double precio = Double.parseDouble(partes[1].replace(",", ".").trim());

                    catalogo.put(nombre, precio);
                }
                linea = br.readLine();
            }
            System.out.println("Catálogo cargado correctamente (" + catalogo.size() + " productos).");

        } catch (IOException e) {
            System.out.println("Error al cargar el catálogo de productos: " + e.getMessage());
            return;
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar: " + e.getMessage());
            }
        }

        System.out.println("=== SUPERMERCADO MARIA TERESA ===");
        System.out.println("-----------------------------------------------------------");
        System.out.println("PRODUCTOS DISPONIBLES HOY:");
        for (Map.Entry<String, Double> producto : catalogo.entrySet()) {
            String nombre = producto.getKey();
            nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1);

            System.out.println("- " + nombre + ": " + producto.getValue() + " €");
        }
        System.out.println("-----------------------------------------------------------");

        System.out.println("\nEscriba 'fin' en el producto para terminar la compra.\n");

        boolean comprando = true;

        while (comprando) {
            System.out.print("Producto: ");
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
