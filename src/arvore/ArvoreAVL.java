package arvore;

public class ArvoreAVL {

    public int altura(No no){
        if (no == null) {
            return 0;
        }

         return no.altura;
    }

    public int fatorBalanceamento(No no){
        int alturaEsquerdo = altura(no.esquerdo);
        int alturaDireito = altura(no.direito);


        return alturaEsquerdo - alturaDireito;
    }

    public void atualizarAltura(No no){
        if (no == null) return;
        int alturaEsquerda = (no.esquerdo != null) ? no.esquerdo.altura : 0;
        int alturaDireita = (no.direito != null) ? no.direito.altura : 0;
        no.altura = Math.max(alturaEsquerda, alturaDireita) + 1;
    }

    public void atualizarAlturaRecursiva(No no) {
        if (no != null){
            // Atualiza os filhos primeiro (pós-ordem)
            atualizarAlturaRecursiva(no.esquerdo);
            atualizarAlturaRecursiva(no.direito);
        
            // Depois atualiza o próprio nó
            atualizarAltura(no);

        }
    
        
    }
    
    public No rotacaoDireita(No y) {
        if (y == null || y.esquerdo == null) {
            return y; // nada para rotacionar
        }

        No x = y.esquerdo;
        No T2 = x.direito;
    
        // Realiza a rotação
        x.direito = y;
        y.esquerdo = T2;
    
        // Atualiza as alturas
        atualizarAlturaRecursiva(y);
    
        // Retorna nova raiz
        return x;
    }

    public No rotacaoEsquerda(No y) {
        if (y == null || y.direito == null) {
            return y; // nada para rotacionar
        }

        No x = y.direito;
        No T2 = x.esquerdo;
    
        // Realiza a rotação
        x.esquerdo = y;
        y.direito = T2;
    
        // Atualiza as alturas
        atualizarAltura(y);
        atualizarAltura(x);
    
        // Retorna nova raiz
        return x;
    }

    public No rotacaoEsquerdaDireita(No no) {
        // Primeiro: rotação à esquerda no filho esquerdo
        no.esquerdo = rotacaoEsquerda(no.esquerdo);

        // Segundo: rotação à direita no próprio nó
        return rotacaoDireita(no);
    }

    public No rotacaoDireitaEsquerda(No no) {
        no.direito = rotacaoDireita(no.direito);

        return rotacaoEsquerda(no);
    }

    public No inserir(No raiz,int chave){
        int FBraiz, FBesquerdo = 0, FBdireito = 0;
        
        if (raiz == null) {
            return new No(chave);
        }
    
        // Inserção recursiva
        if (chave < raiz.chave) {
            raiz.esquerdo = inserir(raiz.esquerdo, chave);
        } else if (chave > raiz.chave) {
            raiz.direito = inserir(raiz.direito, chave);
        } else {
            return raiz; //duplicata
        }
    
        // Atualiza a altura após a inserção
        atualizarAltura(raiz);
    
        // Calcula o fator de balanceamento da raiz
        FBraiz = fatorBalanceamento(raiz);
    
        // Calcula os fatores de balanceamento dos filhos
        if (raiz.esquerdo != null) {
            FBesquerdo = fatorBalanceamento(raiz.esquerdo);
        }
        if (raiz.direito != null) {
            FBdireito = fatorBalanceamento(raiz.direito);
        }
    
        // Caso Esquerda-Esquerda (LL)
        if (FBraiz > 1 && FBesquerdo >= 0) {
            return rotacaoDireita(raiz);
        }
    
        // Caso Esquerda-Direita (LR)
        if (FBraiz > 1 && FBesquerdo < 0) {
          
            return rotacaoEsquerdaDireita(raiz);
        }
    
        // Caso Direita-Direita (RR)
        if (FBraiz < -1 && FBdireito <= 0) {
            return rotacaoEsquerda(raiz); // Rotação à esquerda
        }
    
        // Caso Direita-Esquerda (RL)
        if (FBraiz < -1 && FBdireito > 0) {
           return rotacaoDireitaEsquerda(raiz);
        }
    
        return raiz; // Se a árvore não precisar de rotação
    }

    public No menorValor(No no) {
        No atual = no;
        while (atual.esquerdo != null) {
            atual = atual.esquerdo;
        }
        return atual;
    }

    public No remover(No raiz, int chave) {
        if (raiz == null) return null;
    
        if (chave < raiz.chave) {
            raiz.esquerdo = remover(raiz.esquerdo, chave);
        } else if (chave > raiz.chave) {
            raiz.direito = remover(raiz.direito, chave);
        } else {
            // Nó com 1 ou nenhum filho
            if (raiz.esquerdo == null || raiz.direito == null) {
                No temp = (raiz.esquerdo != null) ? raiz.esquerdo : raiz.direito;
                if (temp == null) {
                    return null; // sem filhos
                } else {
                    return temp; // um filho
                }
            }
    
            // Nó com 2 filhos: substituir pelo sucessor
            No sucessor = menorValor(raiz.direito);
            raiz.chave = sucessor.chave;
            raiz.direito = remover(raiz.direito, sucessor.chave);
        }
    
        // Atualiza altura
        atualizarAlturaRecursiva(raiz);
    
        // Recalcula fator de balanceamento
        int FBraiz = fatorBalanceamento(raiz);
        int FBesquerdo = raiz.esquerdo != null ? fatorBalanceamento(raiz.esquerdo) : 0;
        int FBdireito = raiz.direito != null ? fatorBalanceamento(raiz.direito) : 0;
    
        // Casos de rotação
        if (FBraiz > 1 && FBesquerdo >= 0) {
            return rotacaoDireita(raiz); // LL
        }
        if (FBraiz > 1 && FBesquerdo < 0) {
            raiz.esquerdo = rotacaoEsquerda(raiz.esquerdo);
            return rotacaoDireita(raiz); // LR
        }
        if (FBraiz < -1 && FBdireito <= 0) {
            return rotacaoEsquerda(raiz); // RR
        }
        if (FBraiz < -1 && FBdireito > 0) {
            raiz.direito = rotacaoDireita(raiz.direito);
            return rotacaoEsquerda(raiz); // RL
        }
    
        return raiz;
    }

    public No buscar(No raiz, int chave) {
        if (raiz == null || raiz.chave == chave) {
            return raiz;
        }
    
        if (chave < raiz.chave) {
            return buscar(raiz.esquerdo, chave);
        } else {
            return buscar(raiz.direito, chave);
        }
    }

    public String preOrdemVLR(No no) {
        if (no == null) return "";
        return no.chave + " " + preOrdemVLR(no.esquerdo) + preOrdemVLR(no.direito);
    }
    
    public String preOrdemVRL(No no) {
        if (no == null) return "";
        return no.chave + " " + preOrdemVRL(no.direito) + preOrdemVRL(no.esquerdo);
    }

    public String inOrdemLVR(No no) {
        if (no == null) return "";
        return inOrdemLVR(no.esquerdo) + no.chave + " " + inOrdemLVR(no.direito);
    }
    
    public String inOrdemRVL(No no) {
        if (no == null) return "";
        return inOrdemRVL(no.direito) + no.chave + " " + inOrdemRVL(no.esquerdo);
    }

    public String posOrdemLRV(No no) {
        if (no == null) return "";
        return posOrdemLRV(no.esquerdo) + posOrdemLRV(no.direito) + no.chave + " ";
    }
    
    public String posOrdemRLV(No no) {
        if (no == null) return "";
        return posOrdemRLV(no.direito) + posOrdemRLV(no.esquerdo) + no.chave + " ";
    }
    
    public String limparEspacos(String resultado) {
        return resultado.trim().replaceAll(" +", " ");
    }
    
}
