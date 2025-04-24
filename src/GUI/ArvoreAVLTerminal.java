package GUI;

import java.util.Scanner;

import javax.swing.JFrame;

import arvore.ArvoreAVL;
import arvore.No;

public class ArvoreAVLTerminal {
    public void Terminal(){
        ArvoreAVL arvore = new ArvoreAVL();
        Scanner scanner = new Scanner(System.in);
        No raiz = null;
        boolean sair = true;
        while (sair) {
            System.out.println("\n==== ÁRVORE AVL ====");
            System.out.println("1. Inserir valor");
            System.out.println("2. Excluir valor");
            System.out.println("3. Buscar valor");
            System.out.println("4. Mostrar em Pré-Ordem (VLR)");
            System.out.println("5. Mostrar em In-Ordem (LVR)");
            System.out.println("6. Mostrar em Pós-Ordem (LRV)");
            System.out.println("7. Árvore Gráfica");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o valor a inserir: ");
                    int valor = scanner.nextInt();
                    if (raiz == null) {
                        raiz = arvore.inserir(null, valor);
                    }else{
                        raiz = arvore.inserir(raiz, valor);
                    }
                    
                    System.out.println("Valor inserido.");
                }
                case 2 -> {
                    System.out.print("Digite o valor a excluir: ");
                    int valor = scanner.nextInt();
                    raiz = arvore.remover(raiz, valor);
                    System.out.println("Valor excluído.");
                }
                case 3 -> {
                    System.out.print("Digite o valor a buscar: ");
                    int valor = scanner.nextInt();
                    No encontrado = arvore.buscar(raiz, valor);
                    System.out.println(encontrado != null ? "Valor encontrado." : "Valor não encontrado.");
                }
                case 4 -> System.out.println("Pré-Ordem (VLR): " + arvore.preOrdemVLR(raiz));
                case 5 -> System.out.println("In-Ordem (LVR): " + arvore.inOrdemLVR(raiz));
                case 6 -> System.out.println("Pós-Ordem (LRV): " + arvore.posOrdemLRV(raiz));
                case 7 -> {
                    JFrame frame = new JFrame("Visualização da Árvore AVL");
                    PainelArvoreAVL painel = new PainelArvoreAVL();
                    painel.setRaiz(raiz);
                    frame.add(painel);
                    frame.setSize(800, 600);
                    frame.setVisible(true);
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
