package com.sebas.activitat1evaluable;

import java.io.Serializable;
import java.util.Objects;

public class Nota implements Serializable {
    private String titulo;
    private String fecha;
    private String contenido;

    public Nota() {
    }

    public Nota(String titulo, String fecha, String contenido) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(titulo, nota.titulo) && Objects.equals(fecha, nota.fecha) && Objects.equals(contenido, nota.contenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, fecha, contenido);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s;\t", titulo, fecha, contenido);
    }
}
