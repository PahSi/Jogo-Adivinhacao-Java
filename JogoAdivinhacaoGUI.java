import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class JogoAdivinhacaoGUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private int numeroSecreto;
    private int tentativas;

    public JogoAdivinhacaoGUI() {
        frame = new JFrame("Jogo de Adivinhação"); // Cria uma nova janela com título "Jogo de Adivinhação"
        panel = new JPanel(); // Cria um painel para os componentes da interface
        label = new JLabel("Tente adivinhar o número de 0 a 100"); // Cria uma etiqueta de texto
        textField = new JTextField(10); // Cria uma caixa de texto para o jogador inserir o palpite
        button = new JButton("Enviar"); // Cria um botão de envio
        button.addActionListener(this); // Adiciona um ouvinte de eventos ao botão

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); // Define as margens do painel
        panel.setLayout(new GridLayout(3, 1)); // Define um layout de grade para organizar os componentes
        panel.add(label); // Adiciona a etiqueta ao painel
        panel.add(textField); // Adiciona a caixa de texto ao painel
        panel.add(button); // Adiciona o botão ao painel

        frame.add(panel, BorderLayout.CENTER); // Adiciona o painel ao centro da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento de fechamento da janela
        frame.pack(); // Ajusta o tamanho da janela de acordo com os componentes
        frame.setVisible(true); // Torna a janela visível

        numeroSecreto = gerarNumeroSecreto(); // Gera um número secreto aleatório
        tentativas = 0; // Inicializa o contador de tentativas
    }

    private int gerarNumeroSecreto() {
        return (int) (Math.random() * 101); // Gera um número aleatório entre 0 e 100
    }

    private void salvarJogo() {
        try {
            FileWriter writer = new FileWriter("jogo.txt"); // Abre o arquivo "jogo.txt" para escrita
            writer.write(Integer.toString(numeroSecreto) + "\n"); // Escreve o número secreto no arquivo
            writer.write(Integer.toString(tentativas) + "\n"); // Escreve o número de tentativas no arquivo
            writer.close(); // Fecha o arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void carregarJogo() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("jogo.txt")); // Abre o arquivo "jogo.txt" para leitura
            numeroSecreto = Integer.parseInt(reader.readLine()); // Lê o número secreto do arquivo
            tentativas = Integer.parseInt(reader.readLine()); // Lê o número de tentativas do arquivo
            reader.close(); // Fecha o arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int palpite = Integer.parseInt(textField.getText()); // Obtém o palpite do jogador
        tentativas++; // Incrementa o contador de tentativas

        if (palpite < numeroSecreto) {
            label.setText("O número secreto é maior"); // Exibe uma mensagem indicando que o número secreto é maior
        } else if (palpite > numeroSecreto) {
            label.setText("O número secreto é menor"); // Exibe uma mensagem indicando que o número secreto é menor
        } else {
            label.setText("Parabéns! Você acertou em " + tentativas + " tentativas"); // Exibe uma mensagem de parabéns com o número de tentativas
            salvarJogo(); // Salva o estado do jogo
            button.setEnabled(false); // Desabilita o botão de envio
        }
    }

    public static void main(String[] args) {
        JogoAdivinhacaoGUI jogo = new JogoAdivinhacaoGUI(); // Cria uma instância do jogo
    }
}