import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by yrocha on 15/09/2016.
 */
public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(9080);
        System.out.println("Esperando conexão...");
        Socket cliente = servidor.accept();
        if (cliente.isConnected()) {
            System.out.println("Cliente conectado! " + cliente.getInetAddress().getHostAddress());
        }
        Scanner scanner = new Scanner(cliente.getInputStream());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
        cliente.close();
        servidor.close();
    }
}
