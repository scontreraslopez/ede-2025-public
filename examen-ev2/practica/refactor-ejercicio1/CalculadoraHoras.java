package ede;

public class CalculadoraHoras {

    public double calcularSueldo(double horasNormales, double horasExtra, String categoria) {
        if (horasNormales < 0) {
            throw new IllegalArgumentException("Las horas no pueden ser negativas");
        }
        if (horasExtra < 0) {
            throw new IllegalArgumentException("Las horas no pueden ser negativas");
        }

        double tarifaNormal;
        if (categoria.equals("JUNIOR")) {
            tarifaNormal = 12.0;
        } else if (categoria.equals("SENIOR")) {
            tarifaNormal = 20.0;
        } else if (categoria.equals("LEAD")) {
            tarifaNormal = 35.0;
        } else {
            tarifaNormal = 12.0;
        }

        double sueldoBase = horasNormales * tarifaNormal;
        double sueldoExtra = horasExtra * tarifaNormal * 1.5;
        double sueldoBruto = sueldoBase + sueldoExtra;
        double retencion = sueldoBruto * 0.15;
        double sueldoNeto = sueldoBruto - retencion;
        return sueldoNeto;
    }
}
