package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import arvore.ArvoreAVL;
import arvore.No;

public class TestArvoreAvl {

     /**
     * Cenário LL (rotação simples à direita).
     * 
     * Estrutura:
     *     30
     *    /
     *  20
     * /
     *10
     * 
     * Esperado:
     * - FB(30) = 2 (desequilíbrio à esquerda)
     * - FB(20) = 1
     * - FB(10) = 0 (folha)
     */

    @Test
    public void deveCalcularFatorBalanceamento_RotacaoSimplesDireita_LL() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();
    
        No a1 = new No(30);
        No a2 = new No(20);
        No a3 = new No(10);
    
        a1.esquerdo = a2;
        a2.esquerdo = a3;
    
        arvoreAVL.atualizarAlturaRecursiva(a1);
    
        assertEquals(2, arvoreAVL.fatorBalanceamento(a1));
        assertEquals(1, arvoreAVL.fatorBalanceamento(a2));
        assertEquals(0, arvoreAVL.fatorBalanceamento(a3));
    }

    /**
     * Cenário RR (rotação simples à esquerda).
     * 
     * Estrutura:
     * 10
     *   \
     *   20
     *     \
     *     30
     * 
     * Esperado:
     * - FB(10) = -2 (desequilíbrio à direita)
     * - FB(20) = -1
     * - FB(30) = 0 (folha)
     */
    @Test
    public void deveCalcularFatorBalanceamento_RotacaoSimplesEsquerda_RR() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No a1 = new No(10);
        No a2 = new No(20);
        No a3 = new No(30);
    
        a1.direito = a2;
        a2.direito = a3;
    
        arvoreAVL.atualizarAlturaRecursiva(a1);
    
        assertEquals(-2, arvoreAVL.fatorBalanceamento(a1));
        assertEquals(-1, arvoreAVL.fatorBalanceamento(a2));
        assertEquals(0, arvoreAVL.fatorBalanceamento(a3));
    }

    /**
     * Cenário LR (rotação dupla esquerda-direita).
     * 
     * Estrutura:
     *     30 
     *    /
     *  10
     *    \
     *    20
     * 
     * Esperado:
     * - FB(30) = 2
     * - FB(10) = -1
     * - FB(20) = 0
     */
    @Test
    public void deveCalcularFatorBalanceamento_RotacaoDuplaEsquerdaDireita_LR() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No a1 = new No(30);
        No a2 = new No(10);
        No a3 = new No(20);

        a1.esquerdo = a2;
        a2.direito = a3;

        arvoreAVL.atualizarAlturaRecursiva(a1);
        
        assertEquals(2, arvoreAVL.fatorBalanceamento(a1));
        assertEquals(-1, arvoreAVL.fatorBalanceamento(a2));
        assertEquals(0, arvoreAVL.fatorBalanceamento(a3));
    }

    /**
     * Cenário RL (rotação dupla direita-esquerda).
     * 
     * Estrutura:
     * 10
     *   \
     *   30
     *   /
     * 20
     * 
     * Esperado:
     * - FB(10) = -2 (mais profundo à direita)
     * - FB(30) = 1 (mais profundo à esquerda)
     * - FB(20) = 0 (folha)
     */
    @Test
    public void deveCalcularFatorBalanceamento_RotacaoDuplaDireitaEsquerda_RL() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No a1 = new No(10);
        No a2 = new No(30);
        No a3 = new No(20);

        a1.direito = a2;
        a2.esquerdo = a3;

        arvoreAVL.atualizarAlturaRecursiva(a1);

        assertEquals(-2, arvoreAVL.fatorBalanceamento(a1)); // raiz (10)
        assertEquals(1, arvoreAVL.fatorBalanceamento(a2));  // nó direito (30)
        assertEquals(0, arvoreAVL.fatorBalanceamento(a3));  // nó folha (20)
    }
    /**
     * Teste de fator de balanceamento para uma estrutura de árvore maior.
     *            40
     *          /    \
     *        20      60
     *       /  \     / \
     *     10   30  50  70
     *                    \
     *                    80
     * Verifica o fator de balanceamento de vários nós em diferentes níveis.
     */

    @Test
    public void deveCalcularFatorBalanceamento_EstruturaMaior() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No n40 = new No(40);
        No n20 = new No(20);
        No n60 = new No(60);
        No n10 = new No(10);
        No n30 = new No(30);
        No n50 = new No(50);
        No n70 = new No(70);
        No n80 = new No(80);

        // Montagem da estrutura da árvore
        n40.esquerdo = n20;
        n40.direito = n60;

        n20.esquerdo = n10;
        n20.direito = n30;

        n60.esquerdo = n50;
        n60.direito = n70;

        n70.direito = n80;

        // Atualizar alturas recursivamente a partir da raiz
        arvoreAVL.atualizarAlturaRecursiva(n40);

        // Verificações dos fatores de balanceamento
        assertEquals(-1, arvoreAVL.fatorBalanceamento(n40));  // balanceada
        assertEquals(0, arvoreAVL.fatorBalanceamento(n20));  // balanceada
        assertEquals(-1, arvoreAVL.fatorBalanceamento(n60)); // levemente à direita
        assertEquals(0, arvoreAVL.fatorBalanceamento(n10));  // folha
        assertEquals(0, arvoreAVL.fatorBalanceamento(n30));  // folha
        assertEquals(0, arvoreAVL.fatorBalanceamento(n50));  // folha
        assertEquals(-1, arvoreAVL.fatorBalanceamento(n70)); // um filho à direita
        assertEquals(0, arvoreAVL.fatorBalanceamento(n80));  // folha
    }

    @Test
    public void TestRotacaoDireita(){
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No errado1 = new No(30);
        No errado2 = new No(20);
        No errado3 = new No(10);

        errado1.esquerdo = errado2;
        errado2.esquerdo = errado3;

        arvoreAVL.atualizarAlturaRecursiva(errado1);

        No retorno = arvoreAVL.rotacaoDireita(errado1);

        //verificar os valores

        assertEquals(20, retorno.chave);                 // nova raiz
        assertEquals(10, retorno.esquerdo.chave);        // filho esquerdo
        assertEquals(30, retorno.direito.chave);         // filho direito

        // verificar os filhos 

        assertNull(retorno.esquerdo.esquerdo);         
        assertNull(retorno.esquerdo.direito);

        assertNull(retorno.direito.esquerdo);            // 30 não tem filhos
        assertNull(retorno.direito.direito);

        // verificar a altura 

        assertEquals(2, retorno.altura);                 // nova raiz
        assertEquals(1, retorno.esquerdo.altura);        // filho esquerdo
        assertEquals(1, retorno.direito.altura); 
        
        //Verificar o fator balanciamento

        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.esquerdo));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.direito));
    }
    @Test
    public void testRotacaoEsquerda(){
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No errado1 = new No(10);
        No errado2 = new No(20);
        No errado3 = new No(30);

        errado1.direito = errado2;
        errado2.direito = errado3;

        arvoreAVL.atualizarAlturaRecursiva(errado1);

        No retorno = arvoreAVL.rotacaoEsquerda(errado1);

        //verificar os valores

        assertEquals(20, retorno.chave);                 // nova raiz
        assertEquals(10, retorno.esquerdo.chave);        // filho esquerdo
        assertEquals(30, retorno.direito.chave);         // filho direito

        // verificar os filhos 

        assertNull(retorno.esquerdo.esquerdo);         
        assertNull(retorno.esquerdo.direito);

        assertNull(retorno.direito.esquerdo);            // 30 não tem filhos
        assertNull(retorno.direito.direito);

        // verificar a altura 

        assertEquals(2, retorno.altura);                 // nova raiz
        assertEquals(1, retorno.esquerdo.altura);        // filho esquerdo
        assertEquals(1, retorno.direito.altura); 
        
        //Verificar o fator balanciamento

        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.esquerdo));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.direito));
    }

    @Test
    public void testRotacaoDireitaEsquerda(){
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No errado1 = new No(10);
        No errado2 = new No(30);
        No errado3 = new No(20);

        errado1.direito = errado2;    // errado1 = 10, errado2 = 30
        errado2.esquerdo = errado3;   // errado3 = 20

        arvoreAVL.atualizarAlturaRecursiva(errado1);

        No retorno = arvoreAVL.rotacaoDireitaEsquerda(errado1);

        //verificar os valores

        assertEquals(20, retorno.chave);                 // nova raiz
        assertEquals(10, retorno.esquerdo.chave);        // filho esquerdo
        assertEquals(30, retorno.direito.chave);         // filho direito

        // verificar os filhos 

        assertNull(retorno.esquerdo.esquerdo);         
        assertNull(retorno.esquerdo.direito);

        assertNull(retorno.direito.esquerdo);            // 30 não tem filhos
        assertNull(retorno.direito.direito);

        // verificar a altura 

        assertEquals(2, retorno.altura);                 // nova raiz
        assertEquals(1, retorno.esquerdo.altura);        // filho esquerdo
        assertEquals(1, retorno.direito.altura); 
        
        //Verificar o fator balanciamento

        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.esquerdo));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.direito));
    }

    @Test
    public void testRotacaoEsquerdaDireita(){
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No errado1 = new No(30);
        No errado2 = new No(10);
        No errado3 = new No(20);

        errado1.esquerdo = errado2;    // errado1 = 10, errado2 = 30
        errado2.direito = errado3;   // errado3 = 20

        arvoreAVL.atualizarAlturaRecursiva(errado1);

        No retorno = arvoreAVL.rotacaoEsquerdaDireita(errado1);

        //verificar os valores

        assertEquals(20, retorno.chave);                 // nova raiz
        assertEquals(10, retorno.esquerdo.chave);        // filho esquerdo
        assertEquals(30, retorno.direito.chave);         // filho direito

        // verificar os filhos 

        assertNull(retorno.esquerdo.esquerdo);         
        assertNull(retorno.esquerdo.direito);

        assertNull(retorno.direito.esquerdo);            // 30 não tem filhos
        assertNull(retorno.direito.direito);

        // verificar a altura 

        assertEquals(2, retorno.altura);                 // nova raiz
        assertEquals(1, retorno.esquerdo.altura);        // filho esquerdo
        assertEquals(1, retorno.direito.altura); 
        
        //Verificar o fator balanciamento

        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.esquerdo));
        assertEquals(0, arvoreAVL.fatorBalanceamento(retorno.direito));
    }
    @Test
    public void testInsercaoAVL() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();
        No raiz = null;

        raiz = arvoreAVL.inserir(raiz, 10);
        raiz = arvoreAVL.inserir(raiz, 20);
        raiz = arvoreAVL.inserir(raiz, 30); // aqui vai acontecer uma rotação esquerda

        assertEquals(20, raiz.chave);
        assertEquals(10, raiz.esquerdo.chave);
        assertEquals(30, raiz.direito.chave);

        assertEquals(2, raiz.altura);
        assertEquals(1, raiz.esquerdo.altura);
        assertEquals(1, raiz.direito.altura);
    }

    @Test
    public void testInserir_RotacaoEsquerdaEsquerda() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No raiz = null;
        raiz = arvoreAVL.inserir(raiz, 10);
        raiz = arvoreAVL.inserir(raiz, 5);
        raiz = arvoreAVL.inserir(raiz, 1);  // Deve ocorrer uma rotação à direita (LL)

        // Verificar a nova raiz
        assertEquals(5, raiz.chave);      // 5 se torna a nova raiz

        // Verificar filhos da nova raiz
        assertEquals(1, raiz.esquerdo.chave); // 1 é o filho esquerdo de 5
        assertEquals(10, raiz.direito.chave); // 10 é o filho direito de 5

        // Verificar a altura
        assertEquals(2, raiz.altura);
        assertEquals(1, raiz.esquerdo.altura);
        assertEquals(1, raiz.direito.altura);
    }

    @Test
    public void testInserir_RotacaoEsquerdaDireita() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No raiz = null;
        raiz = arvoreAVL.inserir(raiz, 10);
        raiz = arvoreAVL.inserir(raiz, 30);
        raiz = arvoreAVL.inserir(raiz, 20);  // Deve ocorrer uma rotação à esquerda no filho esquerdo e à direita na raiz (LR)

        // Verificar a nova raiz
        assertEquals(20, raiz.chave);      // 20 se torna a nova raiz

        // Verificar filhos da nova raiz
        assertEquals(10, raiz.esquerdo.chave); // 10 é o filho esquerdo de 20
        assertEquals(30, raiz.direito.chave); // 30 é o filho direito de 20

        // Verificar a altura
        assertEquals(2, raiz.altura);
        assertEquals(1, raiz.esquerdo.altura);
        assertEquals(1, raiz.direito.altura);
    }

    @Test
    public void testInserir_RotacaoDireitaDireita() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No raiz = null;
        raiz = arvoreAVL.inserir(raiz, 10);
        raiz = arvoreAVL.inserir(raiz, 15);
        raiz = arvoreAVL.inserir(raiz, 20);  // Deve ocorrer uma rotação à esquerda (RR)

        // Verificar a nova raiz
        assertEquals(15, raiz.chave);      // 15 se torna a nova raiz

        // Verificar filhos da nova raiz
        assertEquals(10, raiz.esquerdo.chave); // 10 é o filho esquerdo de 15
        assertEquals(20, raiz.direito.chave); // 20 é o filho direito de 15

        // Verificar a altura
        assertEquals(2, raiz.altura);
        assertEquals(1, raiz.esquerdo.altura);
        assertEquals(1, raiz.direito.altura);
    }
    
    @Test
    public void testInserir_RotacaoDireitaEsquerda() {
        ArvoreAVL arvoreAVL = new ArvoreAVL();

        No raiz = null;
        raiz = arvoreAVL.inserir(raiz, 10);
        raiz = arvoreAVL.inserir(raiz, 5);
        raiz = arvoreAVL.inserir(raiz, 7);  // Deve ocorrer uma rotação à direita no filho direito e à esquerda na raiz (RL)

        // Verificar a nova raiz
        assertEquals(7, raiz.chave);      // 7 se torna a nova raiz

        // Verificar filhos da nova raiz
        assertEquals(5, raiz.esquerdo.chave); // 5 é o filho esquerdo de 7
        assertEquals(10, raiz.direito.chave); // 10 é o filho direito de 7

        // Verificar a altura
        assertEquals(2, raiz.altura);
        assertEquals(1, raiz.esquerdo.altura);
        assertEquals(1, raiz.direito.altura);
    }

    @Test
    public void testInserir_LR() {
        ArvoreAVL arvore = new ArvoreAVL();
        No raiz = null;
    
        raiz = arvore.inserir(raiz, 30);
        raiz = arvore.inserir(raiz, 10);
        raiz = arvore.inserir(raiz, 20); 
    
        assertEquals(20, raiz.chave);
        assertEquals(10, raiz.esquerdo.chave);
        assertEquals(30, raiz.direito.chave);
    }

    @Test
    public void testInsercaoLR() {
        ArvoreAVL arvore = new ArvoreAVL();
        No raiz = null;

        raiz = arvore.inserir(raiz, 10);
        raiz = arvore.inserir(raiz, 5);
        raiz = arvore.inserir(raiz, 6); // Deve disparar rotação LR no 10

        // Esperado: 6 vira raiz, 5 vira filho esquerdo, 10 filho direito
        assertEquals(6, raiz.chave);           // nova raiz
        assertEquals(5, raiz.esquerdo.chave);  // filho esquerdo
        assertEquals(10, raiz.direito.chave);  // filho direito

        assertEquals(2, raiz.altura);
        assertEquals(1, raiz.esquerdo.altura);
        assertEquals(1, raiz.direito.altura);

        assertEquals(0, arvore.fatorBalanceamento(raiz));
    }
    @Test
    public void testRemoverFolha() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 10);
        raiz = avl.inserir(raiz, 5);
        raiz = avl.inserir(raiz, 15);

        raiz = avl.remover(raiz, 5); // Folha

        assertEquals(10, raiz.chave);
        assertNull(raiz.esquerdo);
        assertEquals(15, raiz.direito.chave);
    }

    @Test
    public void testRemoverComUmFilho() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 10);
        raiz = avl.inserir(raiz, 5);
        raiz = avl.inserir(raiz, 3); // 5 tem um filho

        raiz = avl.remover(raiz, 5);

        assertEquals(10, raiz.chave);
        assertEquals(3, raiz.esquerdo.chave);
    }


    @Test
    public void testRemoverCausaRotacaoLL() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 30);
        raiz = avl.inserir(raiz, 20);
        raiz = avl.inserir(raiz, 10);

        raiz = avl.remover(raiz, 30); // rebalanceamento LL

        assertEquals(20, raiz.chave);
        assertEquals(10, raiz.esquerdo.chave);
        assertNull(raiz.direito);
    }


    @Test
    public void testRemoverCausaRotacaoRR() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 10);
        raiz = avl.inserir(raiz, 20);
        raiz = avl.inserir(raiz, 30);

        raiz = avl.remover(raiz, 10); // rebalanceamento RR

        assertEquals(20, raiz.chave);
        assertNull(raiz.esquerdo);
        assertEquals(30, raiz.direito.chave);
    }

    @Test
    public void testRemoverCausaRotacaoLR() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 30);
        raiz = avl.inserir(raiz, 10);
        raiz = avl.inserir(raiz, 20); // causa LR

        raiz = avl.remover(raiz, 30); // remove raiz, novo balanceamento

        assertEquals(20, raiz.chave);
        assertEquals(10, raiz.esquerdo.chave);
        assertNull(raiz.direito);
    }


    @Test
    public void testRemoverCausaRotacaoRL() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 10);
        raiz = avl.inserir(raiz, 30);
        raiz = avl.inserir(raiz, 20); // causa RL

        raiz = avl.remover(raiz, 10); // remove raiz, novo balanceamento

        assertEquals(20, raiz.chave);
        assertNull(raiz.esquerdo);
        assertEquals(30, raiz.direito.chave);
    }

    @Test
    public void testBuscar_Existente() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 10);
        raiz = avl.inserir(raiz, 20);
        raiz = avl.inserir(raiz, 5);

        No resultado = avl.buscar(raiz, 20);
        assertNotNull(resultado);
        assertEquals(20, resultado.chave);
    }

    @Test
    public void testBuscar_NaoExistente() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = null;
        raiz = avl.inserir(raiz, 10);
        raiz = avl.inserir(raiz, 5);

        No resultado = avl.buscar(raiz, 99);
        assertNull(resultado);
    }

    @Test
    public void testBuscar_Raiz() {
        ArvoreAVL avl = new ArvoreAVL();
        No raiz = avl.inserir(null, 42);

        No resultado = avl.buscar(raiz, 42);
        assertNotNull(resultado);
        assertEquals(42, resultado.chave);
    }

    @Test
    public void testPercursosSimples() {
        ArvoreAVL arvore = new ArvoreAVL();
        No raiz = null;

        raiz = arvore.inserir(raiz, 10);
        raiz = arvore.inserir(raiz, 5);
        raiz = arvore.inserir(raiz, 15);
        raiz = arvore.inserir(raiz, 3);
        raiz = arvore.inserir(raiz, 7);
        raiz = arvore.inserir(raiz, 13);
        raiz = arvore.inserir(raiz, 17);

        // Pré-ordem VLR: raiz -> esq -> dir
        assertEquals("10 5 3 7 15 13 17", arvore.limparEspacos(arvore.preOrdemVLR(raiz)));

        // Pré-ordem VRL: raiz -> dir -> esq
        assertEquals("10 15 17 13 5 7 3", arvore.limparEspacos(arvore.preOrdemVRL(raiz)));

        // In-ordem LVR: esq -> raiz -> dir
        assertEquals("3 5 7 10 13 15 17", arvore.limparEspacos(arvore.inOrdemLVR(raiz)));

        // In-ordem RVL: dir -> raiz -> esq
        assertEquals("17 15 13 10 7 5 3", arvore.limparEspacos(arvore.inOrdemRVL(raiz)));

        // Pós-ordem LRV: esq -> dir -> raiz
        assertEquals("3 7 5 13 17 15 10", arvore.limparEspacos(arvore.posOrdemLRV(raiz)));

        // Pós-ordem RLV: dir -> esq -> raiz
        assertEquals("17 13 15 7 3 5 10", arvore.limparEspacos(arvore.posOrdemRLV(raiz)));
    }
}
