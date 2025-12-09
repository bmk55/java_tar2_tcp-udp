import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class QuoteUDPClient {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            InetAddress addr = InetAddress.getByName("localhost");

            while (true) {
                System.out.print("GET or exit: ");
                String msg = sc.nextLine();

                byte[] sendBuf = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, addr, 8080);

                socket.send(packet);

                if (msg.equals("exit")) {
                    System.out.println("Client finished.");
                    break;
                }

                byte[] recvBuf = new byte[512];
                DatagramPacket reply = new DatagramPacket(recvBuf, recvBuf.length);

                socket.receive(reply);

                String quote = new String(reply.getData(), 0, reply.getLength());
                System.out.println("Quote: " + quote);
            }

            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
