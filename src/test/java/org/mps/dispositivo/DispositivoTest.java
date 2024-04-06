package org.mps.dispositivo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DispositivoTest {
    @Test
    public void conectarSensorPresion_returnFalse(){
        DispositivoSilver disp = new DispositivoSilver();

        boolean res = disp.conectarSensorPresion();

        assertFalse(res);
    }

    @Test
    public void conectarSensorSonido_returnFalse(){
        DispositivoSilver disp = new DispositivoSilver();

        boolean res = disp.conectarSensorSonido();

        assertFalse(res);
    }

    @Test
    public void configurarSensorPresion_returnFalse(){
        DispositivoSilver disp = new DispositivoSilver();

        boolean res = disp.configurarSensorPresion();

        assertFalse(res);
    }

    @Test
    public void configurarSensorSonido_returnFalse(){
        DispositivoSilver disp = new DispositivoSilver();

        boolean res = disp.configurarSensorSonido();

        assertFalse(res);
    }

    @Test
    public void leerSensorPresion_returnFalse(){
        DispositivoSilver disp = new DispositivoSilver();

        float res = disp.leerSensorPresion();

        assertEquals(0,res);
    }

    @Test
    public void leerSensorSonido_returnFalse(){
        DispositivoSilver disp = new DispositivoSilver();

        float res = disp.leerSensorSonido();

        assertEquals(0,res);
    }

    @Test
    public void estaConectado_returnFalse(){
        DispositivoSilver disp = new DispositivoSilver();

        boolean res = disp.estaConectado();

        assertFalse(res);
    }
    
}
