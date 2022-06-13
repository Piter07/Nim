import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Nim {
    //Pilas
    private int  A = 3;
    private int  B = 4;
    private int  C = 5;
    //Jugadores
    private String jugador1;
    private String jugador2;
    //Variable para saber cuando el juego termina
    private boolean fin;

    public boolean isFin() {
        return fin;
    }
    private void elegirPila(int jugadorNum) throws IOException {

        String jugador;
        //Si el juego ya termino no permitimos que elija mas
        if(this.fin)
            return;

        if( jugadorNum == 1)
            jugador = this.jugador1;
        else
            jugador = this.jugador2;
        String pila = "";
        int cantidad = 0;
        //Bandera 0 es que los dos estan bien, bandera 2 es que solo esta mal la pila y bandera 1 es que esta mal la cantidad
        boolean bandera = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            //Elegir pila
            System.out.println("A: " + this.A + " B: " + this.B + " C: " + this.C + " ");
            System.out.print(jugador + ", elija una pila: ");
            System.out.println(" ");
            //Elegir cantidad a quitar
            pila = reader.readLine();
            System.out.print("Cuantos quitara de la pila " + pila + ": ");
            System.out.println(" ");
            cantidad = Integer.parseInt(reader.readLine());
            bandera = this.escogerPila(pila, cantidad, jugadorNum);
        }while( bandera );


    }

   public void finJuego(int ganador){
       if( ganador == 2)
           System.out.print( this.jugador1 + ", ya no quedan contadores, asi que ... Ganaste!!! ");
       else
           System.out.print( this.jugador2 + ", ya no quedan contadores, asi que ... Ganaste!!! ");

   }

    private boolean escogerPila(String pila, int cantidad, int nombre){
        int A,B,C;
        boolean resta = false;
        switch (pila){
            case "A":
                A= this.A - cantidad;
                if(A == 0 && (this.B==0 && this.C==0)) {
                    this.fin = true;
                }else if(A < 0){
                    System.out.println( "La cantidad excede a la que hay en la pila. Vuelva a elegir.");
                    System.out.println();
                    resta = true;
                    break;
                }
                this.A = A;
                break;
            case "B":
                B = this.B - cantidad;
                if(B == 0 && (this.A==0 && this.C==0)) {
                    this.fin = true;
                }else if(B < 0){
                    System.out.println( "La cantidad excede a la que hay en la pila. Vuelva a elegir.");
                    System.out.println();
                    resta = true;
                    break;
                }
                this.B = B;
                break;
            case "C":
                C = this.C - cantidad;
                if(C == 0 && (this.B==0 && this.A==0)) {
                    this.fin = true;
                }else if(C < 0){
                    System.out.println( "La cantidad excede a la que hay en la pila. Vuelva a elegir.");
                    System.out.println();
                    resta = true;
                    break;
                }
                this.C = C;
                break;
            default:
                System.out.println( "No es una pila valida");
                System.out.println();
                resta = true;
                break;
        }
        return resta;
    }

    private void iniciarJuego() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println( "Jugador 1, ingrese su nombre: ");
        this.jugador1 = reader.readLine();
        System.out.println( "Jugador 2, ingrese su nombre: ");
        this.jugador2 = reader.readLine();
        this.fin = false;
    }

    public static void main(String[] args) throws IOException {

        Nim juego = new Nim();
        juego.iniciarJuego();
        int jugadorNum = 0;
        while(!juego.isFin()){
            jugadorNum++;
            if(jugadorNum > 2)
                jugadorNum = 1;
            juego.elegirPila(jugadorNum);
        }
        juego.finJuego(jugadorNum);

    }

}


