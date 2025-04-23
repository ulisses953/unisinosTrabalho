package arvore;

public class No {
    public int chave;
    public int altura;
    public No esquerdo, direito;

    public No(int chave) {
        this.chave = chave;
        this.altura = 1;
        esquerdo = direito = null;
    }


}
