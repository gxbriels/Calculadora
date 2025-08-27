package org.example;

import org.example.mvc.calculadoracontroller.CalculadoraController;
import org.example.calculadoraview.CalculadoraView;
import java.util.logging.Logger;


public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        logger.info("Iniciando a aplicação da Calculadora.");

        CalculadoraView view = new CalculadoraView();
        CalculadoraController controller = new CalculadoraController(view);
        controller.iniciar();

    }
}