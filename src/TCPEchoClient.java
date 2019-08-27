import java.io.*;
import java.net.*;
import java.util.*;

class TCPEchoClient
{
    private static InetAddress host;
    private static final int PORT = 1234;

    // NOTE: Example of a server IP address. Add a new server IP address.
    private static String serverIpAddress = "80.72.143.91";

    public static void main(String[] args)
    {
        try
        {
            // NOTE: For example - using localhost as server
            host = InetAddress.getLocalHost();

            // host = InetAddress.getByName(serverIpAddress);
        }
        catch (UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accessServer();
    }

    private static void accessServer()
    {
        Socket linksocket = null;

        try
        {
            linksocket = new Socket(host, PORT);
            Scanner input = new Scanner(linksocket.getInputStream());
            PrintWriter output = new PrintWriter(linksocket.getOutputStream(), true);

            Scanner userEntry = new Scanner(System.in);
            String message, response;
            do
            {
                System.out.print("Write message: ");
                message = userEntry.nextLine();
                output.println(message);
                response = input.nextLine();
                System.out.println("\nSERVER> " + response);
            } while (!message.equals("***CLOSE***"));
        }
        catch (IOException ioEx)
        {
            ioEx.getStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("\n* Closing connection... *");
                linksocket.close();
            }
            catch (IOException ioEx)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}

