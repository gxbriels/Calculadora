package org.example.mvc.calculadoracontroller;

import org.example.calculadoramodel.IOperacao;
import org.example.calculadoraview.CalculadoraView;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class CalculadoraController {
    private Logger logger = Logger.getLogger(CalculadoraController.class.getName());
    private CalculadoraView view;
    private Map<Integer, Class<? extends IOperacao>> operacoesMap;

    public CalculadoraController(CalculadoraView view){
        this.view = view;
        this.operacoesMap = new HashMap<>();
        mapearOperacoes();
    }

    private void mapearOperacoes(){
        logger.info("Mapenado operações de cálculo...");
        operacoesMap.put(1, org.example.calculadoramodel.Soma.class);
        operacoesMap.put(2, org.example.calculadoramodel.Subtracao.class);
        operacoesMap.put(3, org.example.calculadoramodel.Multiplicacao.class);
        operacoesMap.put(4, org.example.calculadoramodel.Divisao.class);
    }


    public void iniciar(){
        int opcao;
        do {
            exibirMenu();
            opcao = view.lerOpcao();

            if (opcao >= 1 && opcao <= 4){
                double valor1 = view.lerValor("Informe o primeiro valor: ");
                double valor2 = view.lerValor("Informe o segundo valor: ");
                realizarCalculo(opcao, valor1, valor2);
            } else if(opcao != 5){
                view.exibirMensagemErro("Opção Inválida !!");
            }
        } while (opcao != 5);{
            logger.info("Aplicação encerrada.");
            view.fecharScanner();
        }
    }

    public void exibirMenu(){
        // Constrói dinamicamente usando o nome das respectivas classes
        StringBuilder menu = new StringBuilder();
        menu.append("\nOlá! Escolha uma das opções:\n");

        operacoesMap.forEach((key, value) -> {
            String nomeClasse = value.getSimpleName();
            // Remove o nome da operação e substitui por um nome amigável.
            String nomeAmigavel = nomeClasse.replace("Soma", "Somar")
                    .replace("Subtracao", "Subtrair")
                    .replace("Multiplicacao", "Multiplicar")
                    .replace("Divisao", "Dividir");
            menu.append(String.format("%d - %s%n", key, nomeAmigavel));
        });
        menu.append("5 - Sair da aplicação\n");

        view.exibirMenu(menu.toString());
    }

    private void realizarCalculo(int opcao, double valor1, double valor2){
        Class<? extends IOperacao> classeOperacao = operacoesMap.get(opcao);
        if(classeOperacao == null){
            view.exibirMensagemErro("Opção de cálculo inválida!!");
            return;
        }

        try {
            // usa reflection para criar uma nova instância da classe e invocar o método executar() em tempo de execução.
            IOperacao operacao = classeOperacao.getDeclaredConstructor().newInstance();
            double resultado = operacao.calcular(valor1, valor2);
            view.exibirResultado(resultado);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            view.exibirMensagemErro("Ocorreu um erro ao realizar o cálculo: " + e.getMessage());
        } catch (IllegalArgumentException e){
            logger.severe("Ocorreu um erro ao realizar o cálculo: " + e.getMessage());
            view.exibirMensagemErro("Ocorreu um erro ao realizar o cálculo: " + e.getMessage());
        }
    }

}
