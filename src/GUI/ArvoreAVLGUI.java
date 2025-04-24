package GUI;

import javax.swing.*;
import java.awt.*;

import arvore.ArvoreAVL;
import arvore.No;

public class ArvoreAVLGUI extends JFrame {
    private ArvoreAVL arvore = new ArvoreAVL();
    private No raiz = new No(10);
    private JTextField inputField;
    private DrawPanel drawPanel;

    public ArvoreAVLGUI() {
        setTitle("Árvore AVL Visualização");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inputField = new JTextField(5);
        JButton insertButton = new JButton("Inserir");
        JButton deleteButton = new JButton("Excluir");

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Valor:"));
        controlPanel.add(inputField);
        controlPanel.add(insertButton);
        controlPanel.add(deleteButton);

        drawPanel = new DrawPanel();

        insertButton.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(inputField.getText());
                raiz = arvore.inserir(raiz, valor);
                drawPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido.");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(inputField.getText());
                raiz = arvore.remover(raiz, valor);
                drawPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido.");
            }
        });

        add(controlPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
    }

    private class DrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (raiz != null) {
                drawNode(g, raiz, getWidth() / 2, 30, getWidth() / 4);
            }
        }

        private void drawNode(Graphics g, No no, int x, int y, int gap) {
            g.setColor(Color.BLUE);
            g.fillOval(x - 20, y - 20, 40, 40);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(no.chave), x - 6, y + 5);
            g.setColor(Color.black);
            g.drawString("FB: " + arvore.fatorBalanceamento(no), x - 18, y - 25);

            if (no.esquerdo != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x, y, x - gap, y + 50);
                drawNode(g, no.esquerdo, x - gap, y + 50, gap / 2);
            }
            if (no.direito != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x, y, x + gap, y + 50);
                drawNode(g, no.direito, x + gap, y + 50, gap / 2);
            }
        }
    }
}
