package cl.iplacex;

public class CalculadoraService {

    private final Calculadora calculadora = new Calculadora();

    public int sumarYDuplicar(int a, int b) {

        int suma = calculadora.sumar(a, b);

        return suma * 2;
    }
}