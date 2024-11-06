import javax.swing.*; // Importa as Classes e Bibliotecas para que a Interface funcione
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// Criação da Classe FrutaManagerGUI
public class FrutaManagerGUI {

	private ArrayList<String> frutas; // Lista que armazenará as frutas
	private DefaultListModel<String> listModel; // Modelo para atualizar a JList com as frutas
	private JList<String> list; // Lista que exibitá as frutas na tela
	
	// Construtor da Classe FrutaManagerGUI
	public FrutaManagerGUI() {
		
		frutas = new ArrayList<>(); // Inicializa a lista como  um Array
		listModel = new DefaultListModel<>(); // Inicializa o modelo JList
	
	
	JFrame frame = new JFrame("Gerenciador de Frutas"); // Cria uma janela com o nome de "Gerenciador de frutas"
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // A operação para ao fechar a janela
	frame.setSize(600,300); // Tamanho e largura da janela
	frame.setLayout(new BorderLayout()); // Insere o layout da janela
	
	// Painel da janela
	JPanel panel = new JPanel(); // Cria o painel para os textos e botões
	panel.setLayout(new FlowLayout()); // Insere o layout do painel
	
	// Campo para inserção de texto
	JTextField frutaField = new JTextField(15); // Criação do campo que permite até 15 caracteres
	panel.add(frutaField); // Insere o campo de texto no painel
	
	// Botão de adicionar
	JButton addButton = new JButton("Adicionar"); // Criação do botão "Adicionar"
	panel.add(addButton); // Insere o botão "Adicionar" no painel
	
	// Botão de modificar
	JButton modifyButton = new JButton("Modificar"); // Criação do botão "Modificar"
	modifyButton.setEnabled(false); // Caso a fruta não esteja selecionada, não há opção de modificar
	panel.add(modifyButton); // Insere o botão "Modificar" no painel
	
	// Botão de remover
	JButton removeButton = new JButton("Remover"); // Criação do botão "Remover"
	removeButton.setEnabled(false); // Caso a fruta não esteja selecionada, não há opção de remover
	panel.add(removeButton); // Insere o botão "Remover" no painel
	
	
	// Adiciona o painel com os botões na parte superior da janela
	frame.add(panel, BorderLayout.NORTH); 
	
	// JList que exibirá as frutas
	list = new JList<>(listModel); // Cria JList a partir do listModel
	JScrollPane scrollPane = new JScrollPane(list); // Adiciona a JList dentro de um painel de rolagem
	frame.add(scrollPane, BorderLayout.CENTER); // Adiciona o painel de rolagem no centro da janela
	
	// Botão para listar todas as frutas registradas
	JButton listButton = new JButton("Listar Frutas"); // Cria o botão "Listar Frutas"
	frame.add(listButton, BorderLayout.SOUTH); // Adiciona o  botão no na parte inferior da janela
	
	
	// Ação do botão "Adicionar"
	addButton.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			String novaFruta = frutaField.getText(); // Armazena o texto digitado
			if (!novaFruta.isEmpty()) { // Se não esitver vazio
				frutas.add(novaFruta); // Adiciona na lista
				listModel.addElement(novaFruta); // Adiciona a fruta no modelo da lista
				frutaField.setText(""); // Limpa o campo de texto após ação
				JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada."); // Informa que a fruta foi adicionada
			}
		}
	});
	
	// Ação do botão "Modificar"
	modifyButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIndex = list.getSelectedIndex();
			if(selectedIndex != -1) { // Se houver uma fruta selecionada
				String frutaSelecionada = listModel.getElementAt(selectedIndex); // Puxa o nome da fruta selecionada
				String novaFruta = JOptionPane.showInputDialog(frame, "Modificar" + frutaSelecionada + " para:", frutaSelecionada); // Solicita a modificação
				if (novaFruta != null && !novaFruta.isEmpty()) { // Se a modificação não estiver vazia
					frutas.set(selectedIndex, novaFruta); // Atualiza no Array
					listModel.set(selectedIndex, novaFruta); // Atualiza na JList
					JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para "+ novaFruta); // Informa a modificação
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Selecione uma Fruta para modificar."); // Caso nenhuma fruta tenho sido selecionada, esta mensagem será exibida
			}
		}		
	});
	
	// Ação do botão "Remover"
	removeButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedIndex = list.getSelectedIndex();
			if(selectedIndex != -1) { // Se houver uma fruta selecionada
				String frutaRemovida = frutas.remove(selectedIndex); // Remove a fruta selecionada da lista
				listModel.remove(selectedIndex); // Remove a fruta da JList
				JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida"); // Informa que a fruta foi removida
			} else {
				JOptionPane.showMessageDialog(frame, "Selecione uma Fruta para remover."); // Se nenhuma fruta for selecionada, a mensagem solicitará uma seleção 
			}
		}
	});
	
	// Ação do  botão "Listar Frutas"
	listButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (frutas.isEmpty()) { // Se a lista estiver vazia
				JOptionPane.showMessageDialog(frame, "Nenhuma Fruta na lista."); // Informa que a lista está vazia
			} else {
				JOptionPane.showMessageDialog(frame, "Frutas: "+frutas); // Se houverem frutas na lista, serão exibidas
			}
		}
	});
	
	// Listener para monitorar a seleção na JList
	list.addListSelectionListener(e -> {
		boolean selectionExists = !list.isSelectionEmpty(); // Verifica se há uma seleção
		removeButton.setEnabled(selectionExists); // Habilita ou Desabilita o botão "Remover" dependendo da seleção
		modifyButton.setEnabled(selectionExists); // Habilita ou Desabilita o botão "Modificar" dependendo da seleção
	});
	
	frame.setVisible(true);  // Deixa a janela visível
	
	}
}

