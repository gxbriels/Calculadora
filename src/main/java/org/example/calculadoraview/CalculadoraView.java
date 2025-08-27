package org.example.calculadoraview;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

public class CalculadoraView {
    private static final Logger logger = Logger.getLogger(CalculadoraView.class.getName());
    private Scanner scan;

    public CalculadoraView(){
        this.scan = new Scanner(System.in);
    }

    public void exibirMenu(String menu){
        System.out.println(menu);
    }



    public int lerOpcao(){
        while(true){
            try{
                System.out.println("Digite sua opção: ");
                return scan.nextInt();
            } catch (java.util.InputMismatchException e){
                logger.warning("Entrada inválida. Por favor, digite um número.");
                scan.next();
            }
        }
    }

    public double lerValor(String mensagem){
        while (true) {
            try {
                System.out.print(mensagem);
                return scan.nextDouble();
            } catch (InputMismatchException e) {
                logger.warning("Entrada inválida. Por favor, digite um número válido.");
                scan.next(); // Limpa o buffer do scanner
            }
        }
    }

    public void exibirResultado(double resultado){
        System.out.println("O resultado é: " + resultado);
        logger.info("O resultado é: " + resultado);
    }

    public void exibirMensagemErro(String mensagem){
        logger.severe("Erro: " + mensagem);
    }

    public void fecharScanner(){
        scan.close();
        logger.info("Scanner Fechado. ");
    }


}
