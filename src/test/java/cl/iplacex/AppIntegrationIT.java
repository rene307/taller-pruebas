package cl.iplacex;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AppIntegrationIT {

    @Test
    void pruebaIntegracionCalculadoraServicio() {

        CalculadoraService servicio = new CalculadoraService();

        int resultado = servicio.sumarYDuplicar(2, 3);

        assertEquals(10, resultado);
    }
}