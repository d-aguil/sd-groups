package estacion;

public class SensorPresion extends Sensor {

    public SensorPresion(String grupoMulticast, int puertoMulticast) {
        super("Presión", 5000, grupoMulticast, puertoMulticast);
    }

    @Override
    protected double generarDato() {
        // Genera presión entre 950 hPa y 1000 hPa
        return 950 + (50 * Math.random());
    }
}
