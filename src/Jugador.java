import java.util.Random;

import javax.swing.JPanel;

public class Jugador{
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
    public String getGrupos(){
        int[] contadores = new int[NombreCarta.values().length];
         for (Carta carta:cartas){
              contadores[carta.getNombre().ordinal()] ++;

        }    
        String resultado = "";
        for (int i = 0; i < contadores.length; i++){
            if (contadores[i] >= 2){
                resultado += Grupo.values()[contadores[i]]+" De "+NombreCarta.values()[i]+"\n";             
            }
        }
        //punto 1 del laboratorio

        resultado += getEscaleras();
        return resultado;
    }
    public String getEscaleras() {
        String res = "";
        for (Pinta p : Pinta.values()) {
            boolean[] v = new boolean[NombreCarta.values().length];
            for (Carta c : cartas) {
                if (c.getPinta() == p) {
                    v[c.getNombre().ordinal()] = true;
                }
            }

            int i = 0;
            while (i < v.length) {
                if (v[i]) {
                    int n = 1;
                    while (i + 1 < v.length && v[i + 1]) {
                        n++;
                        i++;
                    }
                    if (n >= 3) {
                        res += Grupo.values()[n] + " de " + p + "\n";
                    }
                }
                i++;
            }
        }
        return res;
    }    
    public String getSobran() {

        int[] contadores = new int[NombreCarta.values().length];

        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        String res = "";

        for (Carta c : cartas) {

            int idx = c.getNombre().ordinal();

            if (contadores[idx] == 1) {
                res += c.getNombre() + " de " + c.getPinta() + "\n";
            }
        }

        return res;
    }    
    public int getPuntaje() {

    int[] contadores = new int[NombreCarta.values().length];

    for (Carta c : cartas) {
        contadores[c.getNombre().ordinal()]++;
    }

    int suma = 0;

    for (Carta c : cartas) {

        int idx = c.getNombre().ordinal();

        if (contadores[idx] == 1) {

            int valor = idx + 1;

            if (valor > 10) {
                valor = 10;
            }
            suma += valor;
        }
    }  

    return suma; 
    }
}
 

