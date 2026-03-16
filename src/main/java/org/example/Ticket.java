package org.example;

import java.util.Objects;

public class Ticket {
    private String producto;
    private double precio;
    private int cantidad;

    public Ticket(String producto, double precio, int cantidad) {
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getProducto() { return producto; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }

    public void sumarCantidad(int cantidadExtra) {
        this.cantidad += cantidadExtra;
    }

    public double getSubtotal() {
        return precio * cantidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ticket that = (Ticket) obj;
        return producto.equalsIgnoreCase(that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto.toLowerCase());
    }
}
