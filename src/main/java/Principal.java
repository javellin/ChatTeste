import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by yrocha on 15/09/2016.
 */
public class Principal {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        InetAddress IP = InetAddress.getLocalHost();
        System.out.println("IP of my system is := " + IP.getHostAddress());
    }
}
