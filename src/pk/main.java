package pk;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class main{



    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        int portServer = Integer.parseInt(sc.nextLine());
        int portClient = Integer.parseInt(sc.nextLine());
        server s = new server(portServer);
        client c = new client(portClient);
        s.start();
        //Thread.sleep(1000);
        c.start();
       }
    }

