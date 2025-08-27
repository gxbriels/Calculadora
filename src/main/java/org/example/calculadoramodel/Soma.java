package org.example.calculadoramodel;

public class Soma implements IOperacao {
    @Override
    public double calcular(double v1, double v2){
        return v1 + v2;
    }
}
