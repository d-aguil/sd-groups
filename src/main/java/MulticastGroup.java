import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastGroup {

    public static void main(String[] args) {
        try {
            InetAddress groupAddress = InetAddress.getByName("230.0.0.1"); // Dirección de grupo multicast
            int port = 4446;

            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(groupAddress);

            // Hilo para recibir mensajes
            Thread receiverThread = new Thread(() -> {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                while (true) {
                    try {
                        socket.receive(packet);
                        String message = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("Mensaje recibido: " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiverThread.start();

            // Enviar un mensaje al grupo
            String message = "Hola desde el proceso";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, groupAddress, port);
            socket.send(packet);

            // Espera un poco para recibir mensajes antes de terminar
            Thread.sleep(5000);

            socket.leaveGroup(groupAddress);
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}