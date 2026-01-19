package controller;
// import view.JCadastrarCliente;
import javax.swing.JOptionPane;

public class ContaBanco{   
    
	private String tipoConta;
	private String nomeCliente;
	private double valorAtual;
	private boolean status;
	// JCadastrarCliente cadastrarCliente = new JCadastrarCliente();	
	
	
	
	// Contrutor
	// String nomeCliente, String tipoConta
	public ContaBanco(){
		this.setStatus(false);
		// System.out.println("Conta criada para " + nomeCliente + ". Bonus de R$ " + this.getValorAtual() + " Adicionado a conta");
		
	}
	
	
	// Getters and setters   
	
	public boolean getStatus(){	  	    
	    return this.status;
	}
	
	
	private void setStatus(boolean status){	// use método fecharConta() para mudar status
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

// ============================================== METODOS =====================	
	


/*
// método depositar

public void depositar(double valor){
	if (!this.getStatus()) {
		System.out.println("Crie uma conta antes de utilizar o sistema.");
	}
   else if(valor <= 0 ){
		System.out.println("Valor de depósito inválido!");
	}
	else{
		this.valorAtual += valor;
	}
}


// método Sacar valor
public void sacar(double valor) {
	if (!this.getStatus()) { 
		System.out.println("Crie uma conta antes de utilizar o sistema.");
	} 
	else if (valor <= 0) { 
		System.out.println("Erro: O valor do saque deve ser maior que zero.");
	} 
	else if (valor > this.getValorAtual()) { 
		System.out.println("Saldo insuficiente! Você tentou sacar R$" + valor + 
						   ", mas seu saldo é R$ " + this.getValorAtual());
	} 
	else {
		this.valorAtual -= valor;
		System.out.println("Saque de R$ " + valor + " realizado com sucesso!");
		System.out.println("Saldo atual: R$ " + this.getValorAtual());
	}
	
}

// Mostrar informações da conta
public void mostrarInfo(){
	if (!this.getStatus()) {
		System.out.println("Crie uma conta antes de utilizar o sistema.");
	}
	else{
		String nomeContaCompleto; 
		String statusContaCompleto;         	
		
		if(this.getTipoConta() == "CC") {
			nomeContaCompleto = "Conta corrente";    	    	 	    	
		}
		else {
			nomeContaCompleto = "Conta poupança";    	    	 
		}
		
		if(this.getStatus()) {
			statusContaCompleto = "Aberta";
		}
		else {
			statusContaCompleto = "Fechada";
		}
		
		System.out.println("O usuário do sistema: " + this.getNomeCliente());
		System.out.println("O tipo de conta: " + nomeContaCompleto);
		System.out.println("O saldo disponível na conta: R$ " + this.getValorAtual());
		System.out.println("O status da conta: " + statusContaCompleto);
		
				
	}
	
}

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
	
	    



	


	public boolean validarDados(String nome, String conta){

		if(nome == null || nome.trim().isEmpty() || conta.equals("Selecione uma opção")) {

			JOptionPane.showMessageDialog(null, "Dados de cadastro inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
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
	    	
}
	
	

	
	

	
	
	

	