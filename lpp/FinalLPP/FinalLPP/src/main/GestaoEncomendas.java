package main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



// Interface para objetos entregáveis
interface Entregavel {
	void entregar();
}


abstract class Encomenda {
	protected int numero;
	protected double peso;


	public Encomenda(int numero, double peso) {
		this.numero = numero;
		this.peso = peso;
	}

	public Encomenda() {
		this(0, 0.0);
	}

	public Encomenda(Encomenda outra) {
		this(outra.numero, outra.peso);
	}

	// Getters e Setters
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	// Método abstrato para calcular o preço

	public abstract double calcularPreco();


	public void print() {
		System.out.println("Número: " + numero);
		System.out.println("Peso: " + peso);
	}

	@Override
	public String toString() {
		return "\n Encomenda Numero " + numero + "\n Peso: " + peso + "kg";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Encomenda other = (Encomenda) obj;
		return numero == other.numero && Double.compare(other.peso, peso) == 0;
	}
}


class EncomendaNormal extends Encomenda {
	private double preco;


	public EncomendaNormal(int numero, double peso, double preco) {
		super(numero, peso);
		this.preco = preco;
	}

	public EncomendaNormal() {
		this(0, 0.0, 0.0);
	}

	public EncomendaNormal(EncomendaNormal outra) {
		this(outra.numero, outra.peso, outra.preco);
	}

	// Getters e Setters
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public double calcularPreco() {
		return preco;
	}

	@Override
	public void print() {
		super.print();
		System.out.println("Preço: " + preco + "€");
	}

	@Override
	public String toString() {
		return super.toString() + "\n Tipo: Normal";
	}
}


class EncomendaExpressa extends Encomenda implements Entregavel {
	private double preco;


	public EncomendaExpressa(int numero, double peso, double preco) {
		super(numero, peso);
		this.preco = preco;
	}

	public EncomendaExpressa() {
		this(0, 0.0, 0.0);
	}

	public EncomendaExpressa(EncomendaExpressa outra) {
		this(outra.numero, outra.peso, outra.preco);
	}

	// Getters e Setters
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public double calcularPreco() {
		return preco;
	}

	@Override
	public void print() {
		super.print();
		System.out.println("\n Preço: " + preco + "€");
	}

	@Override
	public String toString() {
		return super.toString() + "\n Tipo: Expressa";
	}

	@Override
	public void entregar() {
		System.out.println("Entrega Expressa Realizada.");
	}
}

// Classe para encomenda prioritária
class EncomendaPrioritaria extends EncomendaExpressa {
	private boolean entregaRapida;

	// Construtores
	public EncomendaPrioritaria(int numero, double peso, double preco, boolean entregaRapida) {
		super(numero, peso, preco);
		this.entregaRapida = entregaRapida;
	}

	public EncomendaPrioritaria() {
		this(0, 0.0, 0.0, false);
	}

	public EncomendaPrioritaria(EncomendaPrioritaria outra) {
		this(outra.getNumero(), outra.getPeso(), outra.getPreco(), outra.entregaRapida);
	}

	// Getters e Setters
	public boolean isEntregaRapida() {
		return entregaRapida;
	}

	public void setEntregaRapida(boolean entregaRapida) {
		this.entregaRapida = entregaRapida;
	}

	@Override
	public void print() {
		super.print();
	}

	@Override
	public String toString() {
		return super.toString() + " Prioritaria";
	}
}

// Classe principal para a gestão das encomendas
public class GestaoEncomendas {
	private JFrame frame;
	private JTextArea textArea;
	private ArrayList<Encomenda> encomendas;

	public GestaoEncomendas() {
		// Configuração da interface gráfica
		frame = new JFrame("OnTime - Gestão de Encomendas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		JButton adicionarButton = new JButton("Adicionar Encomenda");
		JButton exportarButton = new JButton("Exportar Encomendas");

		adicionarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarEncomenda();
			}
		});

		exportarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarEncomendas();
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(adicionarButton);
		buttonPanel.add(exportarButton);
		panel.add(buttonPanel, BorderLayout.SOUTH);

		frame.add(panel);

		encomendas = new ArrayList<>();
	}


	public void exibirEncomendas() {
		StringBuilder sb = new StringBuilder();
		for (Encomenda encomenda : encomendas) {
			sb.append(encomenda.toString()).append("\n Preço: ").append(encomenda.calcularPreco()).append("€\n");
		}
		textArea.setText(sb.toString());
	}


	public void adicionarEncomenda() {
		String tipoEncomenda = JOptionPane.showInputDialog(null,
				"Insira o Tipo de Encomenda (Normal / Expressa / Prioritaria)", "Tipo de Encomenda",
				JOptionPane.INFORMATION_MESSAGE);

		if (tipoEncomenda != null) {
			tipoEncomenda = tipoEncomenda.toLowerCase();
			switch (tipoEncomenda) {
				case "normal":
					EncomendaNormal encomendaNormal = criarEncomendaNormal();
					if (encomendaNormal != null && !verificarNumeroEncomendaExistente(encomendaNormal.getNumero())) {
						encomendas.add(encomendaNormal);
						JOptionPane.showMessageDialog(frame, "Encomenda Normal Adicionada com Sucesso",
								"Encomenda Adicionada", JOptionPane.INFORMATION_MESSAGE);
						exibirEncomendas();
					} else {
						JOptionPane.showMessageDialog(frame, "ERRO - Já existe uma Encomenda com o mesmo Numero",
								"ERRO", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "expressa":
					EncomendaExpressa encomendaExpressa = criarEncomendaExpressa();
					if (encomendaExpressa != null && !verificarNumeroEncomendaExistente(encomendaExpressa.getNumero())) {
						encomendas.add(encomendaExpressa);
						JOptionPane.showMessageDialog(frame, "Encomenda Expressa Adicionada com Sucesso",
								"Encomenda Adicionada", JOptionPane.INFORMATION_MESSAGE);
						exibirEncomendas();
					} else {
						JOptionPane.showMessageDialog(frame, "ERRO - Já existe uma Encomenda com esse Numero",
								"ERRO", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "prioritaria":
					EncomendaPrioritaria encomendaPrioritaria = criarEncomendaPrioritaria();
					if (encomendaPrioritaria != null && !verificarNumeroEncomendaExistente(encomendaPrioritaria.getNumero())) {
						encomendas.add(encomendaPrioritaria);
						JOptionPane.showMessageDialog(frame, "Encomenda Prioritaria Adicionada com Sucesso",
								"Encomenda Adicionada", JOptionPane.INFORMATION_MESSAGE);
						exibirEncomendas();
					} else {
						JOptionPane.showMessageDialog(frame, "ERRO - Já existe uma Encomenda com esse Numero",
								"ERRO", JOptionPane.ERROR_MESSAGE);
					}
					break;
				default:
					JOptionPane.showMessageDialog(frame, "ERRO - Tipo de Encomenda Invalido", "ERRO",
							JOptionPane.ERROR_MESSAGE);
					break;
			}
		}
	}

	public void exportarEncomendas() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Salvar como");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo CSV", "csv"));

		int userSelection = fileChooser.showSaveDialog(frame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				String filePath = fileChooser.getSelectedFile().getAbsolutePath();
				if (!filePath.toLowerCase().endsWith(".csv")) {
					filePath += ".csv";
				}

				FileWriter writer = new FileWriter(filePath);
				for (Encomenda encomenda : encomendas) {
					writer.append(encomenda.toString()); // Inclui os detalhes da encomenda
					writer.append(","); // Separador
					writer.append(encomenda.getClass().getSimpleName()); // Tipo de encomenda (Normal, Expressa, Prioritária)
					writer.append(",");
					writer.append(String.valueOf(encomenda.calcularPreco())); // Preço
					writer.append("€\n");
				}
				writer.flush();
				writer.close();
				JOptionPane.showMessageDialog(frame, "Encomendas exportadas com sucesso!", "Exportação Concluída", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "Erro ao exportar encomendas!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}




	// Verifica se o número da encomenda já existe
	public boolean verificarNumeroEncomendaExistente(int numero) {
		for (Encomenda encomenda : encomendas) {
			if (encomenda.getNumero() == numero) {
				return true;
			}
		}
		return false;
	}

	// Criação de uma encomenda normal
	public EncomendaNormal criarEncomendaNormal() {
		try {
			int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da encomenda:",
					"Número da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da encomenda:",
					"Peso da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o preço da encomenda:",
					"Preço da Encomenda", JOptionPane.INFORMATION_MESSAGE));

			return new EncomendaNormal(numero, peso, preco);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "ERRO - Valores Invalidos", "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	// Criação de uma encomenda expressa
	public EncomendaExpressa criarEncomendaExpressa() {
		try {
			int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da encomenda:",
					"Número da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da encomenda:",
					"Peso da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o preço da encomenda:",
					"Preço da Encomenda", JOptionPane.INFORMATION_MESSAGE));

			return new EncomendaExpressa(numero, peso, preco);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "ERRO - Valores Invalidos", "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	// Criação de uma encomenda prioritária
	public EncomendaPrioritaria criarEncomendaPrioritaria() {
		try {
			int numero = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da encomenda:",
					"Número da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double peso = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da encomenda:",
					"Peso da Encomenda", JOptionPane.INFORMATION_MESSAGE));
			double preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o preço da encomenda:",
					"Preço da Encomenda", JOptionPane.INFORMATION_MESSAGE));

			return new EncomendaPrioritaria(numero, peso, preco, false);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame, "ERRO - Valores Invalidos", "ERRO", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	// Método para exibir a interface gráfica
	public void show() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		GestaoEncomendas gestaoEncomendas = new GestaoEncomendas();
		gestaoEncomendas.show();
	}

	public void setVisible(boolean b) {
	}

	public void setLocationRelativeTo(Object o) {
	}
}
