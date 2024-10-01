import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Server {

    public static void main(String[] args) {
        try {
            InetAddress groupAddress = InetAddress.getByName("230.0.0.1");
            int port = 4446;

            MulticastSocket socket = new MulticastSocket();

            // Bucle para enviar mensajes
            while (true) {
                String message = "Mensaje desde el servidor: " + System.currentTimeMillis();
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, port);
                socket.send(packet);

                System.out.println("Mensaje enviado: " + message);
                Thread.sleep(5000);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}