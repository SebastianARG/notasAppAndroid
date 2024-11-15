package com.sebas.activitat1evaluable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Notas implements Serializable {
    private List<Nota> notas = new ArrayList<>();

    public void add(Nota nota) {
        notas.add(nota);
    }

    public List<Nota> obtenerNotas() {
        return notas;
    }
    public Nota get(int num){
        if(num < notas.size()){
            return notas.get(num);
        }else {
            return null;
        }
    }
    public int size(){
        return notas.size();
    }
    public Nota getLast(){
        if(notas.size() > 0){
            return notas.get(notas.size()-1);
        }else {
            return null;
        }
    }

    public void remove(int pos){
        notas.remove(pos);
    }
    public void remove(Nota nota){
        if(notas.contains(nota)){
            notas.remove(nota);
        }
    }

    public boolean isEmpty(){
        return notas.isEmpty();
    }
    public int getIndexOf(Nota nota){
        for(int i = 0; i < notas.size(); i++){
            if(notas.get(i).equals(nota)){
                return i;
            }
        }
        return -1;
    }
    @Override
    public String toString() {
        return "Notas{" +
                "notas=" + notas +
                '}';
    }
}
