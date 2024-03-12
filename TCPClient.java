import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main(String args[]) {
        Socket s = null;
        try {
            int serverPort = 4142;
            s = new Socket(args[0], serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                
                String message = userInput.readLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                out.writeUTF(message);
                String data = in.readUTF();
                System.out.println("Received: " + data);
            }
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("Fechar:" + e.getMessage());
            }
        }
    }
}
