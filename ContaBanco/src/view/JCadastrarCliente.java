package view;
import controller.ContaBanco;

import java.awt.Color;
import java.awt.Cursor;
//import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

// import controller.ContaBanco;

public class JCadastrarCliente extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Paleta de cores moderna
    private static final Color COR_PRIMARIA = new Color(26, 35, 126);      // Azul escuro
    private static final Color COR_SECUNDARIA = new Color(63, 81, 181);    // Azul médio
    private static final Color COR_SUCESSO = new Color(46, 125, 50);       // Verde
    private static final Color COR_ALERTA = new Color(211, 47, 47);        // Vermelho
    private static final Color COR_FUNDO = new Color(240, 242, 245);       // Cinza claro
    private static final Color COR_CARD = Color.WHITE;
    private static final Color COR_TEXTO = new Color(33, 33, 33);
    // private static final Color COR_TEXTO_CLARO = new Color(117, 117, 117);
    
    private JPanel contentPane;
    private JTextField txtNomeCliente;
    private JComboBox<String> cmbTipoConta;

    /*    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JCadastrarCliente frame = new JCadastrarCliente();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */


    public JCadastrarCliente() {
        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBackground(COR_FUNDO);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Painel de cabeçalho
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COR_PRIMARIA);
        panelHeader.setBounds(0, 0, 520, 80);
        contentPane.add(panelHeader);
        panelHeader.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Cadastro de Cliente");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBounds(0, 20, 520, 40);
        panelHeader.add(lblTitulo);
        
        // Painel principal (card)
        JPanel panelCard = criarPainelArredondado();
        panelCard.setBackground(COR_CARD);
        panelCard.setBounds(40, 110, 420, 240);
        contentPane.add(panelCard);
        panelCard.setLayout(null);
        
        // Label Nome Cliente
        JLabel lblNomeCliente = new JLabel("Nome do Cliente");
        lblNomeCliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNomeCliente.setForeground(COR_TEXTO);
        lblNomeCliente.setBounds(30, 30, 150, 25);
        panelCard.add(lblNomeCliente);
        
        // Campo Nome Cliente
        txtNomeCliente = criarCampoTexto();
        txtNomeCliente.setBounds(30, 60, 360, 35);
        panelCard.add(txtNomeCliente);
        
        // Label Tipo Conta
        JLabel lblTipoConta = new JLabel("Tipo de Conta");
        lblTipoConta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTipoConta.setForeground(COR_TEXTO);
        lblTipoConta.setBounds(30, 105, 150, 25);
        panelCard.add(lblTipoConta);
        
        // Campo Tipo Conta
        cmbTipoConta = criarComboBox();
        cmbTipoConta.setBounds(30, 135, 360, 35);
        panelCard.add(cmbTipoConta);
        
        // Botão Cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

                // Novo cadastro de cliente
                ContaBanco conta = new ContaBanco();
                
                String nome = getTxtNomeCliente().getText();
                String tipo = (String) getCmbTipoConta().getSelectedItem();
                
                // Valida a conta e credita com base no tipo da conta
                if(conta.validarDados(nome, tipo)){
                    if(tipo.equals("Conta Corrente")) {
                    conta.setValorAtual(50.00);

                    } 
                    else if(tipo.equals("Conta Poupança")) {
                    conta.setValorAtual(150.00);

                    }

                    dispose(); // Fechando tela de cadastro
                    telaPrincipal(conta); // Abrindo tela principal

                }

        	}
        });
        btnCadastrar.setBounds(30, 190, 170, 40);
        estilizarBotao(btnCadastrar, COR_SUCESSO);
        panelCard.add(btnCadastrar);
        
        // Botão Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(220, 190, 170, 40);
        estilizarBotao(btnCancelar, COR_ALERTA);
        panelCard.add(btnCancelar);
    }

    public void telaPrincipal(ContaBanco conta){
        JContaBancoUi telaPrincipal = new JContaBancoUi(conta);
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setVisible(true); 
    }
    
    
    // Método modificadores (getters and setters)

    public JTextField getTxtNomeCliente() {
        return txtNomeCliente;
    }

    public void setTxtNomeCliente(JTextField txtNomeCliente) {
        this.txtNomeCliente = txtNomeCliente;
    }

    public JComboBox<String> getCmbTipoConta() {
        return cmbTipoConta;
    }

    public void setCmbTipoConta(JComboBox<String> cmbTipoConta) {
        this.cmbTipoConta = cmbTipoConta;
    }



    // Método para criar painel com bordas arredondadas
    private JPanel criarPainelArredondado() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
    }
    
    // Método para criar campo de texto estilizado
    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setForeground(COR_TEXTO);
        campo.setBackground(Color.WHITE);
        campo.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        campo.setColumns(10);
        
        // Efeito de foco
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                campo.setBorder(new LineBorder(COR_SECUNDARIA, 2, true));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                campo.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
            }
        });
        
        return campo;
    }
    
    // Método para criar combobox estilizado
    private JComboBox<String> criarComboBox() {
        String[] opcoes = {"Selecione uma opção", "Conta Corrente", "Conta Poupança"};
        JComboBox<String> combo = new JComboBox<>(opcoes);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setForeground(COR_TEXTO);
        combo.setBackground(Color.WHITE);
        combo.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
        combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efeito de foco
        combo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                combo.setBorder(new LineBorder(COR_SECUNDARIA, 2, true));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                combo.setBorder(new LineBorder(new Color(200, 200, 200), 2, true));
            }
        });
        
        return combo;
    }
    
    // Método para estilizar botão (compatível com designer)
    private void estilizarBotao(JButton botao, Color cor) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setForeground(Color.WHITE);
        botao.setBackground(cor);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setOpaque(true);
        
        // Efeito hover
        Color corOriginal = cor;
        Color corHover = cor.brighter();
        Color corPressed = cor.darker();
        
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(corHover);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(corOriginal);
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                botao.setBackground(corPressed);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                botao.setBackground(corHover);
            }
        });
    }
}