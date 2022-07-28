package pk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class server extends Thread{

    private Socket socket = null;
    private ServerSocket server = null;
    private BufferedReader input =  null;
    String line ="";
    String msg = "";
    int portNumber;

    private static boolean available(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }
    @Override
    public void run() {
        super.run();
        try {
            server = new ServerSocket(portNumber);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("AVAILABLE?? "+available(portNumber));
            while ((line=input.readLine())!=null) {
                try {
                    if(line.length()>=6) {
                        msg = line.substring(2, 6);
                        System.out.println("Entered server try to print val");
                        switch (msg) {
                            case "name":
                                System.out.println("Hello " + line.substring(8, line.length()));
                                break;
                            case "date":
                                System.out.println(new Date());
                                break;
                            case "age:":
                                int age = Integer.parseInt(line.substring(7, line.length()));
                                System.out.println("Age " + age);
                                String ageMsg = age < 20 ? "Hello kido" : (age > 40 ? "Hello Senior" : "Hello Mr");
                                System.out.println(ageMsg);
                                break;
                            default:
                                System.out.println("ERROR " + line.substring(2, line.length()));
                                break;
                        }
                    }
                    else
                        System.out.println("ERROR " + line);

//                    socket.close();
//                    input.close();
                }
                catch (Exception e)
                {
                    System.out.println(e.toString());
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    public server(int portNumber) throws IOException {
        this.portNumber = portNumber;
    }
}
