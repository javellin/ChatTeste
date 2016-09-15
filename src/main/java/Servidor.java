import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by yrocha on 15/09/2016.
 */
public class Servidor implements Runnable {

    public Socket cliente;

    public Servidor(Socket cliente) {
        this.cliente = cliente;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(9080);
        System.out.println("Esperando conexão...");

        while (true) {
            Socket cliente = servidor.accept();
            // Cria uma thread do servidor para tratar a conexão
            Servidor tratamento = new Servidor(cliente);
            Thread t = new Thread(tratamento);
            // Inicia a thread para o cliente conectado
            t.start();
        }

    }

    public void run() {
        try {
            InputStream is = cliente.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String mensagem = br.readLine();
            System.out.println("A mensagem recebida do cliente foi: " + mensagem);

            OutputStream os = cliente.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bfw = new BufferedWriter(osw);
            bfw.write(mensagem);
            bfw.flush();


//            System.out.println("Nova conexao com o cliente " + this.cliente.getInetAddress().getHostAddress());
//            Scanner scanner = new Scanner(cliente.getInputStream());
//
//            while (scanner.hasNextLine()) {
//                System.out.println(scanner.nextLine());
//            }
//
//            System.out.println("Conexão com cliente: " + this.cliente.getInetAddress().getHostAddress() + " encerrada!");

            //scanner.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
