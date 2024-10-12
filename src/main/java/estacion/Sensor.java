package estacion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Sensor {
    private String tipoSensor;
    private String grupoMulticast;
    private int puertoMulticast;
    private int interval;
    private Timer timer;

    public Sensor(String tipoSensor, int intervalInMs, String grupoMulticast, int puertoMulticast) {
        this.tipoSensor = tipoSensor;
        this.grupoMulticast = grupoMulticast;
        this.puertoMulticast = puertoMulticast;
        this.interval = intervalInMs;
        this.timer = new Timer();
    }

    // Método abstracto para que cada sensor implemente la generación de datos
    protected abstract double generarDato();

    // Inicia el envío periódico de datos mediante multicast
    public void iniciarEnvioDatos() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    double dato = generarDato();
                    enviarDatoMulticast(dato);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, this.interval);
    }

    // Enviar datos a un grupo multicast
    private void enviarDatoMulticast(double dato) throws Exception {
        String mensaje = tipoSensor + ": " + dato;
        byte[] buffer = mensaje.getBytes();

        InetAddress grupo = InetAddress.getByName(grupoMulticast);
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, grupo, puertoMulticast);

        socket.send(packet);
        socket.close();

        System.out.println("Sensor " + tipoSensor + " envió: " + mensaje);
    }

    public void detenerEnvioDatos() {
        timer.cancel();
    }
}
