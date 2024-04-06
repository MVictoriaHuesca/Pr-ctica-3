package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Nested;
import org.mps.dispositivo.Dispositivo;
import org.mps.dispositivo.DispositivoSilver;

import static org.mockito.Mockito.*;


// mvn jacoco:prepare-agent test install jacoco:report

public class ronQI2SilverTest {

    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
    
    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */
    @DisplayName("Clase para hacer tests del metodo inicializar")
    @Nested
    public class inicializarTest {
        @Test
        public void inicializar_ConectarSensorPresionEsFalse_ReturnFalse(){
            RonQI2Silver r = new RonQI2Silver();

            boolean res = r.inicializar();

            assertFalse(res);
        }

        @Test
        public void inicializar_ConectarSensorPresionEsTruePeroConectarSensorSonidoEsFalse_ReturnTrue(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.conectarSensorPresion()).thenReturn(true);
            when(dispMock.configurarSensorPresion()).thenReturn(true);
            when(dispMock.conectarSensorSonido()).thenReturn(false);
            
            
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.inicializar();

            assertFalse(res);
        }

        @Test
        public void inicializar_LasDosCondicionesSonTruePeroConfigurarSensorSonidoEsFalse_ReturnTrue(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.conectarSensorPresion()).thenReturn(true);
            when(dispMock.configurarSensorPresion()).thenReturn(true);
            when(dispMock.conectarSensorSonido()).thenReturn(true);
            when(dispMock.configurarSensorSonido()).thenReturn(false);
            
            
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.inicializar();

            assertFalse(res);
        }

        @Test
        public void inicializar_LasDosCondicionesSonTruePeroConfigurarSensorPresionEsFalse_ReturnTrue(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.conectarSensorPresion()).thenReturn(true);
            when(dispMock.configurarSensorPresion()).thenReturn(false);
            when(dispMock.conectarSensorSonido()).thenReturn(true);
            when(dispMock.configurarSensorSonido()).thenReturn(true);
            
            
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.inicializar();

            assertFalse(res);
        }


        @Test
        public void inicializar_TodasLasLlamadasDanTrue_ReturnTrue(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.conectarSensorPresion()).thenReturn(true);
            when(dispMock.configurarSensorPresion()).thenReturn(true);
            when(dispMock.conectarSensorSonido()).thenReturn(true);
            when(dispMock.configurarSensorSonido()).thenReturn(true);
            
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.inicializar();

            assertTrue(res);
        }
    }
    /*@DisplayName("Tests para la clase RonQI2Silver")
    @Nested
    class obtenerNuevaLecturaTest{
        @Test
        public void obtenerNuevaLectura_LasLecturasSonMenoresQueNumLecturas_ReturnTrue(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.leerSensorPresion()).thenReturn(1.0f);
            when(dispMock.leerSensorSonido()).thenReturn(1.0f);
            ronQI2.anyadirDispositivo(dispMock);

            ronQI2.obtenerNuevaLectura();

            assertEquals(1, ronQI2.lecturasP.size());
            assertEquals(1, ronQI2.lecturasS.size());
        }

        @Test
        public void obtenerNuevaLectura_LasLecturasSonMayoresQueNumLecturas_ReturnTrue(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.leerSensorPresion()).thenReturn(1.0f);
            when(dispMock.leerSensorSonido()).thenReturn(1.0f);
            ronQI2.anyadirDispositivo(dispMock);

            for(int i = 0; i < 6; i++){
                ronQI2.obtenerNuevaLectura();
            }

            assertEquals(5, ronQI2.lecturasP.size());
            assertEquals(5, ronQI2.lecturasS.size());
        }
    }*/

    

    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
    */

    @DisplayName("Tests para el método reconectar")
    @Nested
    public class reconectarTest {
        @Test
        public void reconectar_DispositivoDesconectado_ReturnTrue(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.estaConectado()).thenReturn(false);
            when(dispMock.conectarSensorPresion()).thenReturn(true);
            when(dispMock.conectarSensorSonido()).thenReturn(true);
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.reconectar();

            assertTrue(res);
        }

        @Test
        public void reconectar_DispositivoDesconectadoPeroConectarSensorPresionEsFalse_ReturnFalse(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.estaConectado()).thenReturn(false);
            when(dispMock.conectarSensorPresion()).thenReturn(false);
            when(dispMock.conectarSensorSonido()).thenReturn(true);
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.reconectar();

            assertFalse(res);
        }

        @Test
        public void reconectar_DispositivoDesconectadoPeroConectarSensorSonidoEsFalse_ReturnFalse(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.estaConectado()).thenReturn(false);
            when(dispMock.conectarSensorPresion()).thenReturn(true);
            when(dispMock.conectarSensorSonido()).thenReturn(false);
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.reconectar();

            assertFalse(res);
        }

        @Test
        public void reconectar_DispositivoConectado_ReturnFalse(){
            RonQI2Silver ronQI2 = new RonQI2Silver();
            DispositivoSilver dispMock = mock(DispositivoSilver.class);
            when(dispMock.estaConectado()).thenReturn(true);
            ronQI2.anyadirDispositivo(dispMock);

            boolean res = ronQI2.reconectar();

            assertFalse(res);
        }
        
    }

    @Test
    public void estaConectado_ReturnFalse(){
        RonQI2Silver ronQI2 = new RonQI2Silver();
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        when(dispMock.estaConectado()).thenReturn(false);
        ronQI2.anyadirDispositivo(dispMock);

        boolean res = ronQI2.estaConectado();

        assertFalse(res);
    }

    @Test
    public void estaConectado_ReturnTrue(){
        RonQI2Silver ronQI2 = new RonQI2Silver();
        DispositivoSilver dispMock = mock(DispositivoSilver.class);
        when(dispMock.estaConectado()).thenReturn(true);
        ronQI2.anyadirDispositivo(dispMock);

        boolean res = ronQI2.estaConectado();

        assertTrue(res);
    }
    
    
    /*
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
     * /
     
     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */
}
