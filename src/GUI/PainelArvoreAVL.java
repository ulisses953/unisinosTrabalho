package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import arvore.ArvoreAVL;
import arvore.No;

public class PainelArvoreAVL extends JPanel{
    private No raiz;

    public void setRaiz(No raiz) {
        this.raiz = raiz;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            desenharArvore(g, raiz, getWidth() / 2, 50, getWidth() / 4);
        }
    }

    private void desenharArvore(Graphics g, No no, int x, int y, int distancia) {
        if (no.esquerdo != null) {
        int xEsq = x - distancia;
        int yFilho = y + 60;
        g.drawLine(x, y, xEsq, yFilho);
        desenharArvore(g, no.esquerdo, xEsq, yFilho, distancia / 2);
    }

    if (no.direito != null) {
        int xDir = x + distancia;
        int yFilho = y + 60;
        g.drawLine(x, y, xDir, yFilho);
        desenharArvore(g, no.direito, xDir, yFilho, distancia / 2);
    }

    g.setColor(Color.CYAN);
    g.fillOval(x - 20, y - 20, 40, 40);
    g.setColor(Color.BLACK);
    g.drawOval(x - 20, y - 20, 40, 40);

    int fator = new ArvoreAVL().fatorBalanceamento(no);
    g.drawString(no.chave + " (" + fator + ")", x - 15, y + 5);
    }
}
