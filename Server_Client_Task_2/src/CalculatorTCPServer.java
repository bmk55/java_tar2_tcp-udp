import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CalculatorTCPServer {

    public static void main(String[] args) {
        System.out.println("Server is listening on port 9090...");

        try {
            ServerSocket server = new ServerSocket(9090);
            Socket client = server.accept();
            System.out.println("Client connected.");

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);

            String line;

            while ((line = input.readLine()) != null) {

                if (line.equals("exit")) {
                    System.out.println("Client requested to close connection.");
                    break;
                }

                System.out.println("Received expression: " + line);

                String[] parts = line.split(" ");
                String result;

                if (parts.length != 3) {
                    result = "Error: Invalid expression";
                } else {
                    try {
                        double a = Double.parseDouble(parts[0]);
                        double b = Double.parseDouble(parts[2]);
                        String op = parts[1];

                        switch (op) {
                            case "+":
                                result = "" + (a + b);
                                break;
                            case "-":
                                result = "" + (a - b);
                                break;
                            case "*":
                                result = "" + (a * b);
                                break;
                            case "/":
                                if (b == 0) result = "Error: Division by zero";
                                else result = "" + (a / b);
                                break;
                            default:
                                result = "Error: Invalid expression";
                        }
                    } catch (Exception e) {
                        result = "Error: Invalid expression";
                    }
                }

                output.println(result);
            }

            client.close();
            server.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
