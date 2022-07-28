package pk;

import java.util.Scanner;
import java.io.*;
import java.net.*;
public class client extends Thread{

    static DataOutputStream dout = null;
    static String str="";
    static int portNum;
    Scanner sc = new Scanner(System.in);
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

        while(true) {

            if (available(portNum) == false) {


                while((str = sc.nextLine()) != null)
                try {
                    System.out.println("Entered client try");
                    Socket s = new Socket("localhost", portNum);
                    dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF(str);
                    dout.flush();
                    dout.close();
                    s.close();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }


    }


    public client(int portNum){
        this.portNum = portNum;
    }

}
