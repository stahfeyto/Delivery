package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setLocationRelativeTo(null); // Centraliza a janela na tela
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		// Configurações da janela principal
		setResizable(false); // Desabilita o redimensionamento da janela
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/main/imagens/icon.svg"))); // Define o ícone da janela
		setTitle("OnTime - Gestão de Encomendas"); // Define o título da janela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
		setBounds(100, 100, 600, 500); // Define o tamanho da janela

		// Configurações do painel de conteúdo
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do painel (preto)
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define uma borda vazia ao redor do painel
		setContentPane(contentPane);
		contentPane.setLayout(null); // Define o layout como nulo para posicionamento absoluto

		// Botão "Iniciar"
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Fecha a janela atual
				GestaoEncomendas frame = new GestaoEncomendas(); // Cria uma nova instância da classe GestaoEncomendas
				frame.setVisible(true); // Torna a nova janela visível
				frame.setLocationRelativeTo(null); // Centraliza a nova janela na tela
			}
		});
		btnIniciar.setBackground(new Color(44, 179, 240)); // Define a cor de fundo do botão (azul claro)
		btnIniciar.setForeground(new Color(0, 0, 0)); // Define a cor do texto do botão (preto)
		btnIniciar.setBounds(230, 400, 133, 34); // Define a posição e o tamanho do botão
		btnIniciar.setFocusPainted(false); // Remove a borda de foco ao redor do botão
		btnIniciar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(236, 140, 211), new Color(242, 173, 61))); // Define uma borda rebaixada para o botão
		btnIniciar.setMinimumSize(new Dimension(141, 23)); // Define o tamanho mínimo do botão
		btnIniciar.setMaximumSize(new Dimension(141, 23)); // Define o tamanho máximo do botão
		btnIniciar.setFont(new Font("Arial", Font.BOLD, 14)); // Define a fonte do texto no botão
		contentPane.add(btnIniciar); // Adiciona o botão ao painel de conteúdo

		// Label de fundo com a imagem do logo
		JLabel Background = new JLabel("");
		Background.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza a imagem no label
		Background.setIcon(new ImageIcon(Home.class.getResource("/main/imagens/OnTime.png"))); // Define a imagem do logo
		Background.setBounds(0, 0, 584, 461); // Define a posição e o tamanho do label
		contentPane.add(Background); // Adiciona o label ao painel de conteúdo
	}
}
