

import controller.ContaBanco;



public class Main {

	public static void main(String[] args) {
		ContaBanco conta1 = new ContaBanco("Jubileu", "CP");
		
		// conta1.depositar(1);
		// conta1.sacar(151);
		//System.out.println(conta1.getValorAtual());
		// conta1.setStatus(false);
		
		conta1.mostrarInfo();

	}

}
