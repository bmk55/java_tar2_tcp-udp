import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

public class QuoteUDPServer {

    public static void main(String[] args) {
        System.out.println("UDP Server is listening on port 8080...");

        String[] quotes = {
                "Success is not final, failure is not fatal.",
                "Happiness depends upon ourselves.",
                "Doubt is the greatest enemy.",
                "Do not let your pride blind you.",
                "Anger gives motivation without purpose."
        };

        Random rand = new Random();

        try {
            DatagramSocket socket = new DatagramSocket(8080);

            byte[] buffer = new byte[512];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + msg);

                if (msg.equals("exit")) {
                    System.out.println("Server closing...");
                    break;
                }

                String chosen = quotes[rand.nextInt(quotes.length)];
                byte[] sendData = chosen.getBytes();

                DatagramPacket reply = new DatagramPacket(
                        sendData,
                        sendData.length,
                        packet.getAddress(),
                        packet.getPort()
                );

                socket.send(reply);
            }

            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
