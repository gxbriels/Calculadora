package org.example.calculadoramodel;

public class Divisao implements IOperacao{
    @Override
    public double calcular(double valor1, double valor2) {
        if(valor2 == 0){
            throw new IllegalArgumentException("Divisão por 0 ( zero ) não permitida !!");
        }
        return valor1 / valor2;
    }
}
