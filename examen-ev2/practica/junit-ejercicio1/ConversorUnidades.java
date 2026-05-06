package ede;

public class ConversorUnidades {

    public double kmAMillas(double km) {
        if (km < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }
        return km * 0.621371;
    }

    public double millasAKm(double millas) {
        if (millas < 0) {
            throw new IllegalArgumentException("La distancia no puede ser negativa");
        }
        return millas / 0.621371;
    }

    public double celsiusAFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }

    public boolean esNegativo(double valor) {
        return valor < 0;
    }
}
