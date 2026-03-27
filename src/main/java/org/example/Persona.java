package org.example;// Clase Modelo que representa a una Persona

import java.time.LocalDate;
import java.util.Objects;

public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private LocalDate fechaDenacimiento;

    public Persona(){
        this.id=0;
        this.nombre="";
        this.apellido="";
        this.fechaDenacimiento=null;
    }
    public Persona(int id,String nombre,String apellido,LocalDate fechaDenacimiento)
    {
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechaDenacimiento=fechaDenacimiento;
    }
    public Persona(String nombre,String apellido,LocalDate fechaDenacimiento)
    {
        this.nombre=nombre;
        this.apellido=apellido;
        this.fechaDenacimiento=fechaDenacimiento;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaDenacimiento() {
        return fechaDenacimiento;
    }

    public void setFechaDenacimiento(LocalDate fechaDenacimiento) {
        this.fechaDenacimiento = fechaDenacimiento;
    }

    @Override
    // una persona es distinta a otra si varía cualquiera de los datos : nombre,apellidos o fecha de nacimiento

    public int hashCode() {
        final int primo=31;
        int result=1;
        result = result*primo+((nombre==null)?0:nombre.hashCode());
        result = result*primo+((apellido==null)?0:apellido.hashCode());
        result = result*primo+((fechaDenacimiento==null)?0:fechaDenacimiento.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        boolean finished = false;
        if (this == obj)
            result = true;
        if (this != obj) {
            if (obj == null) {
                result = false;
            } else if (getClass() != obj.getClass()) {
                result = false;
            } else {
                Persona other = (Persona) obj;
                if (apellido == null) {
                    if (other.apellido != null) {
                        result = false;
                        finished = true;
                    }
                } else if (!apellido.equals(other.apellido)) {
                    result = false;
                    finished = true;
                }
                if (!finished) {
                    if (fechaDenacimiento == null) {
                        if (other.fechaDenacimiento != null) {
                            result = false;
                            finished = true;
                        }
                    } else if (!fechaDenacimiento.equals(other.fechaDenacimiento)) {
                        result = false;
                        finished = true;
                    }
                    if (!finished) {
                        if (nombre == null) {
                            if (other.nombre != null) {
                                result = false;
                            }
                        } else if (!nombre.equals(other.nombre)) {
                            result = false;
                        }
                    }
                }
            }
        }
        return result;
    }
}
