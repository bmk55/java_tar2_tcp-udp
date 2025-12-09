import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class CalculatorTCPClient {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 9090);

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            String text;

            while (true) {
                System.out.print("Enter expression: ");
                text = sc.nextLine();

                output.println(text);

                if (text.equals("exit")) {
                    System.out.println("Client closed.");
                    break;
                }

                String resp = input.readLine();
                System.out.println(text + " = " + resp);
            }

            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
