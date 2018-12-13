import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * client class.
 *
 * Just simple Client connecting to the Server and using terminal GUI.
 *
 * @author Group 3
 * @version 0.1
 */
public class Client {

    public static void main(String[] args){
        Socket client = null;
        BufferedReader inFromServer = null;
        BufferedWriter outToServer  = null;


        try {
            client = new Socket(InetAddress.getLocalHost(), 49975);
            inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outToServer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            Scanner input = new Scanner(System.in);
            System.out.println("Connected to Server on port 49975");

            String strClient,strServer;
            boolean notRequestedToStop = true;
            strServer = inFromServer.readLine();
            System.out.println(strServer);

            while(notRequestedToStop){

                System.out.print("Client: ");

                strClient = input.nextLine();
                outToServer.write(strClient);
                outToServer.newLine();
                outToServer.flush();

                strServer = inFromServer.readLine();
                System.out.println("Server: " + strServer);
                System.out.print("\n");
                if(strServer.equalsIgnoreCase("goodbye")) notRequestedToStop = false;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                assert inFromServer != null;
                inFromServer.close();
                assert outToServer != null;
                outToServer.close();
                client.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
}
