package estacion;

public class Main {

    public static void main(String[] args) {
        // Crear los tres sensores, cada uno con su propio canal multicast
        Sensor sensorTemperatura = new SensorTemperatura("230.0.0.1", 4446);
        Sensor sensorHumedad = new SensorHumedad("230.0.0.2", 4447);
        Sensor sensorPresion = new SensorPresion("230.0.0.3", 4448);

        // Iniciar el envío de datos desde los sensores
        sensorTemperatura.iniciarEnvioDatos();
        sensorHumedad.iniciarEnvioDatos();
        sensorPresion.iniciarEnvioDatos();
    }
}
