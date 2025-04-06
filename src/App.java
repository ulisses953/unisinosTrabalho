import arvore.BinaryTree;

public class App {
    public static void main(String[] args) throws Exception {
        BinaryTree binary = new BinaryTree();

        binary.inserir(10);
        binary.inserir(20);
        binary.inserir(30);
        binary.inserir(2);
        binary.inserir(7);
        binary.inserir(90);

        // binary.imprimirInOrdem();

        // binary.pesquisar(10);
        // binary.remover(20);

        // binary.imprimirInOrdem();

        binary.imprimirArvore();

    }
}
