package estacion;

public class SensorHumedad extends Sensor {

    public SensorHumedad(String grupoMulticast, int puertoMulticast) {
        super("Humedad", 10000, grupoMulticast, puertoMulticast);
    }

    @Override
    protected double generarDato() {
        // Genera humedad entre 0% y 100%
        return 0 + (100 * Math.random());
    }
}
