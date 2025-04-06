package arvore;

public class BinaryTree {
    Node raiz;

    public void inserir(int valor){
        raiz = inserirRecursivo(raiz, valor);
    }

    private Node inserirRecursivo(Node atual, int valor) {
        if (atual == null) {
            return new Node(valor);
        }

        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        } else {
            // valor já existe, não insere duplicatas
            System.out.println("Valor " + valor + " já existe na árvore.");
            
        }

        return atual;
    }

    public void pesquisar(int valor){
        Node resultado = pesquisarRecursivo(raiz,valor);
        
        if (resultado != null) {
            System.out.println("Valor encontrado: " + resultado.valor);
            System.out.println("Valor à esquerda: " + 
                (resultado.esquerda != null ? resultado.esquerda.valor : "null"));
            System.out.println("Valor à direita: " + 
                (resultado.direita != null ? resultado.direita.valor : "null"));
        } else {
            System.out.println("Valor " + valor + " não encontrado.");
        }
    }
    

    private Node pesquisarRecursivo(Node atual, int valor){
        if (atual == null || atual.valor == valor) {
            return atual;
        }
    
        if (valor < atual.valor) {
            return pesquisarRecursivo(atual.esquerda, valor);
        } else {
            return pesquisarRecursivo(atual.direita, valor);
        }
    }

    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }
    //3
    private Node removerRecursivo(Node atual, int valor) {
        if (atual == null) {
            return null;
        }
    
        if (valor < atual.valor) {
            atual.esquerda = removerRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = removerRecursivo(atual.direita, valor);
        } else {
            // Encontrou o nó a ser removido
    
            // Caso 1: sem filhos
            if (atual.esquerda == null && atual.direita == null) {
                return null;
            }
    
            // Caso 2: só tem um filho
            if (atual.esquerda == null) {
                return atual.direita;
            } else if (atual.direita == null) {
                return atual.esquerda;
            }
    
            // Caso 3: dois filhos
            // Pega o menor valor da subárvore da direita
            Node sucessor = encontrarMenor(atual.direita);
            atual.valor = sucessor.valor;
            atual.direita = removerRecursivo(atual.direita, sucessor.valor);
        }
    
        return atual;
    }

    private Node encontrarMenor(Node raiz) {
        while (raiz.esquerda != null) {
            raiz = raiz.esquerda;
        }
        return raiz;
    }

    public void balencear(){

    }

    public void imprimirInOrdem() {
        imprimirInOrdemRec(raiz);
    }

    private void imprimirInOrdemRec(Node atual) {
        if (atual != null) {
            imprimirInOrdemRec(atual.esquerda);
            System.out.print(atual.valor + " ");
            imprimirInOrdemRec(atual.direita);
        }
    }

    public BinaryTree() {
        this.raiz = null;
    }

    public void imprimirArvore() {
        imprimirArvore(raiz, 0);
    }
    
    private void imprimirArvore(Node node, int nivel) {
        if (node == null) {
            return;
        }
    
        imprimirArvore(node.direita, nivel + 1);
    
        // Espaçamento proporcional ao nível do nó
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        System.out.println(node.valor);
    
        imprimirArvore(node.esquerda, nivel + 1);
    }
    
}
