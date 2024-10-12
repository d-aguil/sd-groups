package estacion;

public class SensorTemperatura extends Sensor {

    public SensorTemperatura(String grupoMulticast, int puertoMulticast) {
        super("Temperatura", 3000, grupoMulticast, puertoMulticast);
    }

    @Override
    protected double generarDato() {
        // Genera temperatura entre -10°C y 40°C
        return -10 + (40 * Math.random());
    }
}
