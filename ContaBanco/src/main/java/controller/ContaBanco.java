package controller;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ContaBanco{   
    
	private String tipoConta;
	private String nomeCliente;
	private String cpf;
	private double valorAtual;
	private boolean status;
	JLabel lblSaldo;
	
	
	// Contrutor
	
	public ContaBanco(){
		this.setStatus(false);
		// Locale.setDefault(new Locale("pt", "BR"));
		Locale.setDefault(Locale.of("pt", "BR"));

		
	}
	
	// Getters and setters   
	
	public boolean getStatus(){	  	    
	    return this.status;
	}
	
	public void setStatus(boolean status){	

		this.status = status;	    
	}	
	
	public double getValorAtual(){
	    return this.valorAtual;	    
	}
	
	public void setValorAtual(double valorAtual){
		this.valorAtual = valorAtual;		    
	}
	
	public String getTipoConta(){
	    return this.tipoConta;	    
	}
	
	public void setTipoConta(String tipoConta){
		this.tipoConta = tipoConta;		    
	}
	
	public String getNomeCliente(){
	    return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	

/*
public void fecharConta(){
	if (this.getValorAtual() > 0){
		System.out.println("Você possui R$ " + valorAtual + " em conta. Saque todo o valor antes de encerrar a conta!");	        
	}
	else if(!this.getStatus()){
		System.out.println("Você não possui conta aberta.");
	}
	else{
		System.out.println("Conta " + this.getTipoConta() + " foi finalizada.");
		this.setStatus(false);
	}
}
 */
	


// ================== MÉTODOS PRINCIPAIS =============
	

	

	// Valida os dados na tela de cadastro do usuário
	public boolean validarDados(String nome, String conta, String cpf){

		if(nome == null || nome.trim().isEmpty() || conta.equals("Selecione uma opção")) {

			JOptionPane.showMessageDialog(null, "Dados de cadastro inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!this.ValidarCpf(cpf)){
			JOptionPane.showMessageDialog(null, "CPF Inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		} 
		else {
			JOptionPane.showMessageDialog(null, "Dados de cadastro validado.\n\nConta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
			this.setNomeCliente(nome);
			this.setTipoConta(conta);
			this.setStatus(true);
			
			return true;
		}
	}

	// método depositar

	public void depositar(JTextArea inputDepositar, JLabel lblSaldo){
		if( !this.getStatus() ){
			JOptionPane.showMessageDialog(null, "Não possui conta ativa", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else if(inputDepositar.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo de depósito vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else if(!isDouble(inputDepositar.getText())){
			JOptionPane.showMessageDialog(null, "Campo apenas recebe valor númerico.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else{
			Double elemento = conversorSeparador(inputDepositar.getText());

			this.setValorAtual( this.getValorAtual() + elemento );

			JOptionPane.showMessageDialog(null, "Valor de R$ " + String.format(Locale.getDefault(), "R$ %.2f", elemento) + " Depositado", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

			// atualizar valor na interface
			atualizarValor(lblSaldo);

		}
	}


	// método Sacar valor
	public void sacar(JTextArea inputSacar, JLabel lblSaldo) {
					

		if( !this.getStatus() ){
			JOptionPane.showMessageDialog(null, "Não possui conta ativa", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else if(inputSacar.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "Campo de saque vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else if(!isDouble(inputSacar.getText())){
			JOptionPane.showMessageDialog(null, "Campo apenas recebe valor númerico.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else if(this.getValorAtual() <= 0 || conversorSeparador(inputSacar.getText()) > this.getValorAtual() ){
			JOptionPane.showMessageDialog(null, "Valor de saque inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		else{

			Double elemento = conversorSeparador(inputSacar.getText());

			this.setValorAtual( this.getValorAtual() - elemento );

			JOptionPane.showMessageDialog(null, "Valor de R$ " + String.format(Locale.getDefault(), "R$ %.2f", elemento) + " sacado.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

			// atualizar valor na interface
			atualizarValor(lblSaldo);

		}
		
	}


	// Mostrar informações da conta
	public void mostrarInfo(){

		if(!this.getStatus()){

			JOptionPane.showMessageDialog(null, "Não possui conta ativa", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		else{

			String statusContaCompleto = this.getStatus() ? "Ativa" : "Inativa";
			String nomeContaCompleto = this.getTipoConta();

			String cpfCompleto = this.getCpf();

			String info = 
			"Usuário: " + this.getNomeCliente() + "\n" +
			"CPF: " + cpfCompleto + "\n" +
			"Tipo de Conta: " + nomeContaCompleto + "\n" +
			"Saldo Disponível: R$ " + String.format(Locale.getDefault(), "R$ %.2f", this.getValorAtual())  + "\n" +
			"Status da Conta: " + statusContaCompleto;

			JOptionPane.showMessageDialog(null, info, "Informações da Conta", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}


	// ===================== MÉTODOS AUXILIARES ==================


	// Atualiza o valor na interface
	public void atualizarValor( JLabel lblSaldo){
        // atualizar valor na interface com 2 casas decimais e separador de vírgula
        String saldoFormatado = String.format(Locale.getDefault(), "R$ %.2f", this.getValorAtual());
        
        lblSaldo.setText( saldoFormatado );
    }

	// Verifica se existe letras na sentença
	private boolean isDouble(String text) {
        // Regex para aceita double
        return text.matches("[0-9]*[.,]?[0-9]*"); 
    }


	// Converte o separador de ponto para vírgula
	private Double conversorSeparador(String elemento) {

        if ( elemento.contains(",") ) {
            String valorTratado = elemento.replace(",", ".");

            return Double.parseDouble(valorTratado);
        }else{

            return Double.parseDouble(elemento);
        }
     
    }

	// Msg de boas vindas ao sistema e informativo do bonus.
	public void boasVindas(){
        JOptionPane.showMessageDialog(null, "Bonus de " + this.getTipoConta() + ": R$ " + this.getValorAtual(), "Boas vindas", JOptionPane.PLAIN_MESSAGE);
    }

	public boolean ValidarCpf(String CPF) {
        

		if (CPF == null) return false;

		// Remove caracteres diferentes de números
        CPF = CPF.replaceAll("[^0-9]", "");
        
        if (CPF.length() != 11 || CPF.matches("(\\d)\\1{10}")){
			// mensagem de alerta
            return false;
		}
		// FÓRMULA QUE CONSEGUI NA INTERNET PARA VALIDAR CPF

		try {
        	// Cálculo do 1º Dígito Verificador
			int soma = 0;
			int peso = 10;
			for (int i = 0; i < 9; i++) {
				int num = (int) (CPF.charAt(i) - 48);
				soma += (num * peso--);
			}
			int r = 11 - (soma % 11);
			char dig10 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

			// Cálculo do 2º Dígito Verificador
			soma = 0;
			peso = 11;
			for (int i = 0; i < 10; i++) {
				int num = (int) (CPF.charAt(i) - 48);
				soma += (num * peso--);
			}
			r = 11 - (soma % 11);
			char dig11 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

			// Verifica se os dígitos calculados conferem com os digitados

			boolean isValid = (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));

			if (isValid) {
                // 2. SE FOR VÁLIDO, SETTA O CPF no formato correto 
                this.setCpf(CPF.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"));
            }

            return isValid;

		} catch (Exception e) {
			return false;
		}

       
    }

	    	
}
	
	

	
	

	
	
	

	