package org.example.calculadoramodel;

public class Multiplicacao implements IOperacao{
    @Override
    public double calcular(double valor1, double valor2) {
        return valor1 * valor2;
    }
}
