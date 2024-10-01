import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client {

    public static void main(String[] args) {
        try {
            InetAddress groupAddress = InetAddress.getByName("230.0.0.1");
            int port = 4446;

            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(groupAddress);

            System.out.println("Cliente unido al grupo multicast.");

            //
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}