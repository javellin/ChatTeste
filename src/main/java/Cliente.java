import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by yrocha on 15/09/2016.
 */
public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("127.0.0.1", 9080);
        if (cliente.isConnected()) {
            System.out.println("Conectado");
        }
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        Scanner teclado = new Scanner(System.in);
        System.out.println("Para sair do chat digite 0!");
        while (teclado.hasNextLine()) {
            if (!teclado.nextLine().equals("0")) {
                saida.println(teclado.nextLine());
            } else {
                System.out.println("Você saiu do chat!");
                break;
            }
        }
        saida.close();
        teclado.close();
        cliente.close();
    }
}
