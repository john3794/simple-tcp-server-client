import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoServer
{
    private static ServerSocket servSock;
    private static final int PORT = 1234;

    // NOTE: Example of a client IP address. Add a new client IP address.
    private static String clientIpAddress = "80.72.132.51";

    public static void main(String[] args)
    {
        System.out.println("Opening port..\n");
        try
        {
            servSock = new ServerSocket(PORT);
        }
        catch (IOException ioEx)
        {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
        do
        {
            handleClient();
        } while (true);
    }

    private static void handleClient()
    {
        Socket link = null;

        try
        {
            link = servSock.accept();

            System.out.println(link.getInetAddress());
            InetAddress outcomingIPaddress = InetAddress.getByName(clientIpAddress);

            if (link.getInetAddress().equals(outcomingIPaddress))
            {
                link.close();
            }

            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);

            int numMessages = 0;
            String message = input.nextLine();

            while (!message.equals("***CLOSE***"))
            {
                System.out.println("Message received.");
                numMessages++;
                output.println("Message " + numMessages + ": " + message);
                message = input.nextLine();
            }
            output.println(numMessages + " messages received.");
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }
}