import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by yrocha on 15/09/2016.
 */
public class Cliente implements Runnable {

    private Socket cliente;

    public Cliente(Socket cliente) {
        this.cliente = cliente;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9080);
        Cliente c = new Cliente(socket);
        Thread t = new Thread(c);
        t.start();
    }

    public void run() {
        try {
            if (this.cliente.isConnected()) {
                System.out.println("Conectado ");
            }

            //ENVIA MENSAGEM
            OutputStream os = cliente.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            Scanner teclado = new Scanner(System.in);
            PrintStream saida = new PrintStream(this.cliente.getOutputStream());

            String mensagem = "";
            while (teclado.hasNextLine()) {
                mensagem = teclado.nextLine();
            }
            bw.write(mensagem);
            bw.flush();
            System.out.println("A mensagem enviada ao servidor foi: " + mensagem);

            //RECEBE MENSAGEM
            InputStream is = cliente.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);


            saida.close();
            teclado.close();
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
