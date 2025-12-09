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
                System.out.print("Enter expression (num op num) or close/exit: ");
                text = sc.nextLine();

                output.println(text);

                if (text.equals("close")) {
                    socket.close();   
                    return;
                }

                if (text.equals("exit")) {
                    socket.close();   
                    return;
                }

                String resp = input.readLine();
                System.out.println(text + " = " + resp);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
