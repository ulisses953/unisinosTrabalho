import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import GUI.ArvoreAVLGUI;
import GUI.ArvoreAVLTerminal;
import arvore.ArvoreAVL;
import arvore.No;

public class App {
    public static void main(String[] args) throws Exception {
   
        Scanner scanner = new Scanner(System.in);
        boolean sair = true;
        while (sair) {
            System.out.println("Grafico : 1");
            System.out.println("Terminal : 2");
            System.out.println("Sair : 3");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    SwingUtilities.invokeLater(() -> new ArvoreAVLGUI().setVisible(true));
                }
                case 2 -> {
                    ArvoreAVLTerminal terminal = new ArvoreAVLTerminal();
                    terminal.Terminal();
                }
                case 3 -> {
                    System.out.println("Saindo...");
                    scanner.close();
                    sair = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}

