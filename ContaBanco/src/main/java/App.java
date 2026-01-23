
import java.awt.EventQueue;
import view.JCadastrarCliente;

public class App {
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
}
