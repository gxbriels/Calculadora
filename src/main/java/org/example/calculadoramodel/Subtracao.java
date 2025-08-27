package org.example.calculadoramodel;

public class Subtracao implements IOperacao{
    @Override
    public double calcular(double valor1, double valor2) {
        return valor1 - valor2;
    }
}
