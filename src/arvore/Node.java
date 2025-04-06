package arvore;

public class Node {
    int valor;
    int altura;
    Node esquerda, direita;

    public Node(int valor) {
        this.valor = valor;
        esquerda = direita = null;
    }

    public int getBalance(){
        return 0;
    }
}
