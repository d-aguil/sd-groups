package estacion;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class EstacionMeteorologica {

    // Método para escuchar un canal multicast específico
    public void escucharCanal(String grupoMulticast, int puertoMulticast) {
        new Thread(() -> {
            try {
                InetAddress grupo = InetAddress.getByName(grupoMulticast);
                MulticastSocket socket = new MulticastSocket(puertoMulticast);
                socket.joinGroup(grupo);

                System.out.println("Estación Meteorológica escuchando en el grupo " + grupoMulticast + " en el puerto " + puertoMulticast);

                byte[] buffer = new byte[256];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);

                    String mensaje = new String(packet.getData(), 0, packet.getLength());
                    System.out.println("Estación Meteorológica recibió: " + mensaje);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        EstacionMeteorologica estacion = new EstacionMeteorologica();


        // Escuchar los tres canales multicast: uno para cada sensor

        // Canal de Temperatura
        estacion.escucharCanal("230.0.0.1", 4446);
        // Canal de Humedad
        estacion.escucharCanal("230.0.0.2", 4447);
        // Canal de Presión
        estacion.escucharCanal("230.0.0.3", 4448);
    }
}
