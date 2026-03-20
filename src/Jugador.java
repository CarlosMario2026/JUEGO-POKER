import java.util.Random;

import javax.swing.JPanel;

public class Jugador {
    private final int TOTAL_CARTAS = 10;
    private final int MARGEN_SUPERIOR = 10;
    private final int MERGEN_IZQUIERDO = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir(){
        for (int i = 0; i < TOTAL_CARTAS; i++){
             cartas[i] = new Carta(r);
        }
    }
    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MERGEN_IZQUIERDO+DISTANCIA*(TOTAL_CARTAS-1);
        for (Carta carta:cartas){
            carta.mostrar(pnl, posicion, MARGEN_SUPERIOR);
            posicion-= DISTANCIA;
        }         
        pnl.repaint();
    }  
  
}
