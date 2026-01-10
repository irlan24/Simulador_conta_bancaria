package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContaBancoUi extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    // Paleta de cores moderna
    private static final Color COR_PRIMARIA = new Color(26, 35, 126);      // Azul escuro
    private static final Color COR_SECUNDARIA = new Color(63, 81, 181);    // Azul médio
    private static final Color COR_SUCESSO = new Color(46, 125, 50);       // Verde
    private static final Color COR_ALERTA = new Color(211, 47, 47);        // Vermelho
    private static final Color COR_FUNDO = new Color(240, 242, 245);       // Cinza claro
    private static final Color COR_CARD = Color.WHITE;
    private static final Color COR_TEXTO = new Color(33, 33, 33);
    private static final Color COR_TEXTO_CLARO = new Color(117, 117, 117);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ContaBancoUi frame = new ContaBancoUi();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ContaBancoUi() {
        setTitle("Banco Digital - Gerenciamento de Conta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 600);
        
        contentPane = new JPanel();
        contentPane.setBackground(COR_FUNDO);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Cabeçalho
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COR_PRIMARIA);
        panelHeader.setBounds(0, 0, 500, 80);
        contentPane.add(panelHeader);
        panelHeader.setLayout(null);
        
        JLabel lblTitulo = new JLabel("BANCO DIGITAL");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(0, 15, 500, 35);
        panelHeader.add(lblTitulo);
        
        JLabel lblSubtitulo = new JLabel("Gerenciamento de Conta");
        lblSubtitulo.setForeground(new Color(200, 200, 200));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setBounds(0, 50, 500, 20);
        panelHeader.add(lblSubtitulo);
        
        // Card do Saldo
        JPanel panelSaldo = new JPanel();
        panelSaldo.setBackground(COR_CARD);
        panelSaldo.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        panelSaldo.setBounds(30, 110, 440, 100);
        contentPane.add(panelSaldo);
        panelSaldo.setLayout(null);
        
        JLabel lblSaldoTexto = new JLabel("Saldo Disponível");
        lblSaldoTexto.setForeground(COR_TEXTO_CLARO);
        lblSaldoTexto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSaldoTexto.setBounds(20, 15, 200, 25);
        panelSaldo.add(lblSaldoTexto);
        
        JLabel lblSaldo = new JLabel("R$ 0,00");
        lblSaldo.setForeground(COR_PRIMARIA);
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblSaldo.setBounds(20, 45, 400, 40);
        panelSaldo.add(lblSaldo);
        
        // Card de Operações
        JPanel panelOperacoes = new JPanel();
        panelOperacoes.setBackground(COR_CARD);
        panelOperacoes.setBorder(new LineBorder(new Color(220, 220, 220), 1, true));
        panelOperacoes.setBounds(30, 230, 440, 200);
        contentPane.add(panelOperacoes);
        panelOperacoes.setLayout(null);
        
        JLabel lblOperacoes = new JLabel("Operações");
        lblOperacoes.setForeground(COR_TEXTO);
        lblOperacoes.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblOperacoes.setBounds(20, 15, 200, 25);
        panelOperacoes.add(lblOperacoes);
        
        // Campo Depositar
        JLabel lblDepositarTexto = new JLabel("Depositar");
        lblDepositarTexto.setForeground(COR_TEXTO_CLARO);
        lblDepositarTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDepositarTexto.setBounds(20, 55, 80, 25);
        panelOperacoes.add(lblDepositarTexto);
        
        JLabel lblRsDepositar = new JLabel("R$");
        lblRsDepositar.setForeground(COR_TEXTO_CLARO);
        lblRsDepositar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRsDepositar.setBounds(20, 85, 25, 30);
        panelOperacoes.add(lblRsDepositar);
        
        JTextArea inputDepositar = new JTextArea();
        inputDepositar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputDepositar.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        inputDepositar.setBounds(50, 85, 120, 30);
        panelOperacoes.add(inputDepositar);
        
        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDepositar.setForeground(Color.WHITE);
        btnDepositar.setBackground(COR_SUCESSO);
        btnDepositar.setBorder(new LineBorder(COR_SUCESSO, 0, true));
        btnDepositar.setBounds(190, 85, 220, 30);
        btnDepositar.setFocusPainted(false);
        btnDepositar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adicionarHoverEffect(btnDepositar, COR_SUCESSO);
        panelOperacoes.add(btnDepositar);
        
        // Campo Sacar
        JLabel lblSacarTexto = new JLabel("Sacar");
        lblSacarTexto.setForeground(COR_TEXTO_CLARO);
        lblSacarTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSacarTexto.setBounds(20, 125, 80, 25);
        panelOperacoes.add(lblSacarTexto);
        
        JLabel lblRsSacar = new JLabel("R$");
        lblRsSacar.setForeground(COR_TEXTO_CLARO);
        lblRsSacar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRsSacar.setBounds(20, 155, 25, 30);
        panelOperacoes.add(lblRsSacar);
        
        JTextArea inputSacar = new JTextArea();
        inputSacar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputSacar.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        inputSacar.setBounds(50, 155, 120, 30);
        panelOperacoes.add(inputSacar);
        
        JButton btnSacar = new JButton("Sacar");
        btnSacar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSacar.setForeground(Color.WHITE);
        btnSacar.setBackground(COR_ALERTA);
        btnSacar.setBorder(new LineBorder(COR_ALERTA, 0, true));
        btnSacar.setBounds(190, 155, 220, 30);
        btnSacar.setFocusPainted(false);
        btnSacar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adicionarHoverEffect(btnSacar, COR_ALERTA);
        panelOperacoes.add(btnSacar);
        
        // Botão de Informações
        JButton btnMostrarInfo = new JButton("Informações da Conta");
        btnMostrarInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnMostrarInfo.setForeground(Color.WHITE);
        btnMostrarInfo.setBackground(COR_SECUNDARIA);
        btnMostrarInfo.setBorder(new LineBorder(COR_SECUNDARIA, 0, true));
        btnMostrarInfo.setBounds(30, 450, 440, 45);
        btnMostrarInfo.setFocusPainted(false);
        btnMostrarInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        adicionarHoverEffect(btnMostrarInfo, COR_SECUNDARIA);
        contentPane.add(btnMostrarInfo);
        
        // Área de Alertas
        JPanel panelAlertas = new JPanel();
        panelAlertas.setBackground(new Color(255, 248, 225));
        panelAlertas.setBorder(new LineBorder(new Color(255, 193, 7), 1, true));
        panelAlertas.setBounds(30, 510, 440, 40);
        contentPane.add(panelAlertas);
        panelAlertas.setLayout(null);
        
        JLabel lblAlert = new JLabel("Nenhum alerta no momento");
        lblAlert.setForeground(new Color(102, 60, 0));
        lblAlert.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblAlert.setBounds(15, 10, 410, 20);
        panelAlertas.add(lblAlert);
    }
    
    // Método auxiliar para adicionar efeito hover aos botões
    private void adicionarHoverEffect(JButton btn, Color corOriginal) {
        Color corHover = corOriginal.darker();
        
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(corHover);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(corOriginal);
            }
        });
    }
}