import cambiomoneda.CambioMoneda;
import historialconversion.HistorialDeConversiones;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        CambioMoneda cambiarMoneda = new CambioMoneda();
        Scanner teclado= new Scanner(System.in);
        //ejecucion del menu
        boolean activo =true;


        while (activo){

            try {
                int opcion = menu(teclado);
                activo = opciones(opcion, teclado, cambiarMoneda);

                //guarda el historial un vez se cierre el programa
              if (activo == false) {
                  HistorialDeConversiones historial = new HistorialDeConversiones();
                  historial.guardarJson(cambiarMoneda);
              }

            } catch (InputMismatchException e) {
                System.out.println("solo se permiten numeros");
                // en caso de ingresar un caracter no numerico se usa next para evitar un bucle infinito
                teclado.next();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    // menu principal
    private static int menu(Scanner consola) {
        System.out.println( """
                         ******************************Bienvenido/a al conversor de Monedas*****************************
                             1) Dolar >>>> Peso Chileno
                             2) Dolar >>>> Peso Argentino
                             3) Dolar >>>> Peso Uruguayo
                             4) Peso Chileno >>>> Real Brasileño
                             5) Peso Chileno >>>> Sol Peruano
                             6) Peso Uruguayo >>> Peso Chileno
                             7) Salir de conversor
                         ***********************************************************************************************
                         Eliga  una opcion\s""");

        return consola.nextInt();
    }

    public static boolean opciones(int opcion,Scanner teclado,CambioMoneda cambiarMoneda) throws IOException {

        // este booleano es para retornar true o false al activo del while principal
        boolean activo = true;


            switch (opcion){
                case 1:
                    System.out.println("Ingrese el monto a cambiar");
                    cambiarMoneda.monedasACambiar("USD","CLP");
                  break;
                case 2 :
                    System.out.println("Ingrese el monto a cambiar");
                    cambiarMoneda.monedasACambiar("USD","ARS");

                    break;
                case 3:
                    System.out.println("Ingrese el monto a cambiar");
                    cambiarMoneda.monedasACambiar("USD","UYU");

                    break;
                case 4:
                    System.out.println("Ingrese el monto a cambiar");
                    cambiarMoneda.monedasACambiar("CLP","BRL");
                    break;
                case 5:
                    System.out.println("Ingrese el monto a cambiar");
                    cambiarMoneda.monedasACambiar("CLP","PEN");
                    break;
                case 6:
                    System.out.println("Ingrese el monto a cambiar");
                    cambiarMoneda.monedasACambiar("UYU","CLP");
                    break;
                case 7:
                    System.out.println("""
                            gracias por usar el conversor de monedas
                            finalizando programa....
                            """);
                    activo = false;
                    break;
                default:
                    System.out.println("*¡Opcion incorrecta porfavor elija una opcion!*");
                    return activo;
            }
            // este condicional es  para terminar de procesar y mostrar resultado del monto ingresado
            if(activo){
                double monto = teclado.nextInt();
                cambiarMoneda.setMonto(monto);
                // se muestra el resultado de la operacion
                cambiarMoneda.resultado();

                // opciones extra por si el usuario desea realizar otra operacion
                while (activo) {
                    System.out.println("""
                            Desea realizar otro cambio?
                            1) para realizar otro cambio
                            2) salir de conversor
                            """);
                    opcion = teclado.nextInt();
                    if (opcion ==1){
                        break;
                    }
                    if (opcion == 2) {
                        System.out.println("""
                                Gracias por usar el conversor de monedas
                                finalizando programa...
                                """);
                        activo = false;
                    } else {
                        System.out.println("¡Opcion incorrecta!");
                        System.out.println("porfavor intente nuevamente");
                    }

                }
            }


            return activo;
    }
}
