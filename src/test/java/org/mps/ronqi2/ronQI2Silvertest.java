package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.mps.dispositivo.DispositivoSilver;

public class ronQI2SilverTest {

    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */
   
   @DisplayName("Tests para el método inicializar")
   @Nested
   public class inicializarTest {
      @Test
      @DisplayName("Inicializar un RonQI2 con un dispositivo que devuelve true en todos los métodos devuelve true")
      public void inicializar_TodoTrue_ReturnsTrue() {
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();

         ronQI2.anyadirDispositivo(disp);
         Boolean res = ronQI2.inicializar();

         assertTrue(res);
      }

      @Test
      @DisplayName("Inicializar un RonQI2 con un dispositivo que devuelve false en conectarSensorPresion devuelve false")
      public void inicializar_ConectarSensorPresionFalse_ReturnsFalse() {
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.conectarSensorPresion()).thenReturn(false);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();

         ronQI2.anyadirDispositivo(disp);
         Boolean res = ronQI2.inicializar();

         assertFalse(res);
      }

      @Test
      @DisplayName("Inicializar un RonQI2 con un dispositivo que devuelve false en configurarSensorPresion devuelve false")
      public void inicializar_ConfigurarSensorPresionFalse_ReturnsFalse() {
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(false);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();

         ronQI2.anyadirDispositivo(disp);
         Boolean res = ronQI2.inicializar();

         assertFalse(res);
      }

      @Test
      @DisplayName("Inicializar un RonQI2 con un dispositivo que devuelve false en conectarSensorSonido devuelve false")
      public void inicializar_ConectarSensorSonidoFalse_ReturnsFalse() {
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(false);
         when(disp.configurarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();

         ronQI2.anyadirDispositivo(disp);
         Boolean res = ronQI2.inicializar();

         assertFalse(res);
      }

      @Test
      @DisplayName("Inicializar un RonQI2 con un dispositivo que devuelve false en configurarSensorSonido devuelve false")
      public void inicializar_ConfigurarSensorSonidoFalse_ReturnsFalse() {
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(false);
         RonQI2Silver ronQI2 = new RonQI2Silver();

         ronQI2.anyadirDispositivo(disp);
         Boolean res = ronQI2.inicializar();

         assertFalse(res);
      }

      /*
         * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es 
         * true), se llama una sola vez al configurar de cada sensor.
      */

      @Test
      @DisplayName("Inicializar un RonQI2 correctamente configura un sola vez cada sensor")
      public void inicializar_TodoTrue_LlamaUnaVezConfigurarSensorPresion() {
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.configurarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         when(disp.configurarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();
   
         ronQI2.anyadirDispositivo(disp);
         ronQI2.inicializar();
   
         verify(disp, times(1)).configurarSensorPresion();
         verify(disp, times(1)).configurarSensorSonido();
      }
   }


   /*
    * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
    * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
    * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
    */

   @DisplayName("Tests para el método reconectar")
   @Nested
   public class reconectarTests {
      @Test
      @DisplayName("Reconectar un RonQI2 con un dispositivo desconectado llama a conectarSensorPresion y conectarSensorSonido una vez")
      public void reconectar_DispositivoDesconectado_LlamaConectarSensorPresionYConectarSensorSonido(){
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.estaConectado()).thenReturn(false);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();
         ronQI2.anyadirDispositivo(disp);
         ronQI2.reconectar();
   
         verify(disp, times(1)).conectarSensorPresion();
         verify(disp, times(1)).conectarSensorSonido();
      }
   
      @Test
      @DisplayName("Reconectar un RonQI2 con un dispositivo conectado no llama a conectarSensorPresion y conectarSensorSonido")
      public void reconectar_DispositivoConectado_NoLlamaConectarSensorPresionYConectarSensorSonido(){
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.estaConectado()).thenReturn(true);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();
         ronQI2.anyadirDispositivo(disp);
   
         ronQI2.reconectar();
   
         verify(disp, times(0)).conectarSensorPresion();
         verify(disp, times(0)).conectarSensorSonido();
      }
   
      @Test
      @DisplayName("Reconectar un RonQI2 con un dispositivo desconectado devuelve false si no se conecta el sensor de presion correctamente")
      public void reconectar_ConectarSensorPresionFalse_ReturnsFalse(){
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.estaConectado()).thenReturn(false);
         when(disp.conectarSensorPresion()).thenReturn(false);
         when(disp.conectarSensorSonido()).thenReturn(true);
         RonQI2Silver ronQI2 = new RonQI2Silver();
         ronQI2.anyadirDispositivo(disp);
   
         Boolean res = ronQI2.reconectar();
   
         assertFalse(res);
      }
   
      @Test
      @DisplayName("Reconectar un RonQI2 con un dispositivo desconectado devuelve false si no se conecta el sensor de sonido correctamente")
      public void reconectar_ConectarSensorSonidoFalse_ReturnsFalse(){
         DispositivoSilver disp = mock(DispositivoSilver.class);
         when(disp.estaConectado()).thenReturn(false);
         when(disp.conectarSensorPresion()).thenReturn(true);
         when(disp.conectarSensorSonido()).thenReturn(false);
         RonQI2Silver ronQI2 = new RonQI2Silver();
         ronQI2.anyadirDispositivo(disp);
   
         Boolean res = ronQI2.reconectar();
   
         assertFalse(res);
      }
   }

    
   /*
    * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectur(), 
    * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f ythresholdS = 30.0f;, 
    * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
    */
    /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
    * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea ono (por ejemplo 4, 5 y 10 lecturas).
    * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
    */
   @DisplayName("Tests para el método evaluarApneaSuenyo")
   @Nested
   public class evaluarApneaSuenyoTests {
      @ParameterizedTest
      @ValueSource(ints = {1, 5, 10}) //comprobamos que funciona independientemente del numero de lecturas
      @DisplayName("Evaluar apnea con un número de lecturas por debajo de los umbrales de presion y sonido devuelve false")
      public void evaluarApneaSuenyo_PresionySonidoPorDebajoUmbrales_DevuelveFalse(int numDatos){
         RonQI2Silver ronQI2 = new RonQI2Silver();
         DispositivoSilver dispMock = mock(DispositivoSilver.class);
         when(dispMock.leerSensorPresion()).thenReturn(10.0f); //debajo del umbral
         when(dispMock.leerSensorSonido()).thenReturn(15.0f);  //debajo del umbral
         ronQI2.anyadirDispositivo(dispMock);
   
         for(int i = 0; i < numDatos; i++){
             ronQI2.obtenerNuevaLectura();
         }
         boolean res = ronQI2.evaluarApneaSuenyo();
   
         assertFalse(res);
      }
   
      @ParameterizedTest
      @ValueSource(ints = {1, 5, 10}) //comprobamos que funciona independientemente del numero de lecturas
      @DisplayName("Evaluar apnea con un número de lecturas por encima de los umbrales de presion y sonido devuelve true")
      public void evaluarApneaSuenyo_PresionySonidoPorEncimaUmbrales_DevuelveTrue(int numLecturas){
         RonQI2Silver ronQI2 = new RonQI2Silver();
         DispositivoSilver dispMock = mock(DispositivoSilver.class);
         when(dispMock.leerSensorPresion()).thenReturn(25.0f); //encima del umbral
         when(dispMock.leerSensorSonido()).thenReturn(35.0f);  //encima del umbral
         ronQI2.anyadirDispositivo(dispMock);
   
         for(int i = 0; i < numLecturas; i++){
             ronQI2.obtenerNuevaLectura();
         }
         boolean res = ronQI2.evaluarApneaSuenyo();
   
         assertTrue(res);
      }
   
      @ParameterizedTest
      @ValueSource(ints = {1, 5, 10}) //comprobamos que funciona independientemente del numero de lecturas
      @DisplayName("Evaluar apnea con un número de lecturas por encima de los umbrales de presion y por debajo de sonido devuelve false")
      public void evaluarApneaSuenyo_PresionPorEncimaSonidoPorDebajoUmbrales_DevuelveFalse(int numDatos){
         RonQI2Silver ronQI2 = new RonQI2Silver();
         DispositivoSilver dispMock = mock(DispositivoSilver.class);
         when(dispMock.leerSensorPresion()).thenReturn(25.0f); //encima del umbral
         when(dispMock.leerSensorSonido()).thenReturn(25.0f);  //debajo del umbral
         ronQI2.anyadirDispositivo(dispMock);
   
         for(int i = 0; i < numDatos; i++){
             ronQI2.obtenerNuevaLectura();
         }
         boolean res = ronQI2.evaluarApneaSuenyo();
   
         assertFalse(res);
      }
   
      @ParameterizedTest
      @ValueSource(ints = {1, 5, 10}) //comprobamos que funciona independientemente del numero de lecturas
      @DisplayName("Evaluar apnea con un número de lecturas por debajo de los umbrales de presion y por encima de sonido devuelve false")
      public void evaluarApneaSuenyo_PresionPorDebajoSonidoPorEncimaUmbrales_DevuelveFalse(int numDatos){
         RonQI2Silver ronQI2 = new RonQI2Silver();
         DispositivoSilver dispMock = mock(DispositivoSilver.class);
         when(dispMock.leerSensorPresion()).thenReturn(15.0f); //debajo del umbral
         when(dispMock.leerSensorSonido()).thenReturn(35.0f);  //encima del umbral
         ronQI2.anyadirDispositivo(dispMock);
   
         for(int i = 0; i < numDatos; i++){
             ronQI2.obtenerNuevaLectura();
         }
         boolean res = ronQI2.evaluarApneaSuenyo();
   
         assertFalse(res);
      }
   }
}
