import java.util.Random;

import javax.swing.JPanel;

public class Jugador {
    private final int TOTAL_CARTAS = 10;
    private final int MARGEN_SUPERIOR = 10;
    private final int MERGEN_IZQUIERDO = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int posicion = MERGEN_IZQUIERDO + DISTANCIA * (TOTAL_CARTAS - 1);
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN_SUPERIOR);
            posicion -= DISTANCIA;
        }
        pnl.repaint();
    }

    public String getGrupos() {
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;

        }
        String resultado = "";
        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                resultado += Grupo.values()[contadores[i]] + " De " + NombreCarta.values()[i] + "\n";
            }
        }
        // punto 1 del laboratorio

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
                    // Encontramos el inicio de una posible escalera
                    int inicio = i;
                    int n = 1;
                    while (i + 1 < v.length && v[i + 1]) {
                        n++;
                        i++;
                    }
                    if (n >= 3) {
                        res += Grupo.values()[n] + " de " + p + " de "
                                + NombreCarta.values()[inicio] + " a "
                                + NombreCarta.values()[i] + "\n";
                    }
                }
                i++;
            }

            // escalera circular (K-A-2)
            boolean tieneK = v[12];
            boolean tieneA = v[0];
            boolean tiene2 = v[1];
            if (tieneK && tieneA && tiene2) {
                res += "Escalera Circular de " + p + " (K-A-2) \n";
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
            // Solo se muestran las cartas que no forman parte de ningún grupo (es decir, aquellas que aparecen exactamente una vez)
            if (contadores[idx] >= 2) continue;
            boolean enEscalera = false;
            
            for (Pinta p : Pinta.values()) {
                boolean[] v = new boolean[NombreCarta.values().length];
                for (Carta x : cartas) {
                    if (x.getPinta() == p) {
                        v[x.getNombre().ordinal()] = true;
                    }
                }
                int i = 0;
                while (i < v.length) {
                    if (v[i]) {
                        int inicio = i;
                        int n = 1;
                        while (i + 1 < v.length && v[i + 1]) {
                            n++;
                            i++;
                        }
                        if (n >= 3) {
                            for (int j = inicio; j <= i; j++){
                                if (j == idx && c.getPinta() == p) {
                                    enEscalera = true;
                                }
                            }    
                        }
                    }
                    i++;
                }              
            }
            if (!enEscalera) {
                res += c.getNombre() + " de " + c.getPinta() + "\n";
            }
        }
        return res;
    }

    public int getPuntaje() {

        int[] contadores = new int[NombreCarta.values().length];
        //contamos cuántas veces aparece cada carta
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        } 

        int suma = 0;

        for (Carta c : cartas) {

           int idx = c.getNombre().ordinal();

           if (contadores[idx] >= 2) continue; // Si la carta forma parte de un grupo, no se le asigna ningún valor
           boolean enEscalera = false;
           for (Pinta p : Pinta.values()) {
               boolean[] v = new boolean[NombreCarta.values().length];
                for (Carta x : cartas) {
                    if (x.getPinta() == p) {
                       v[x.getNombre().ordinal()] = true;
                    }
                }
                int i = 0;
                while (i < v.length) {
                    if (v[i]) {
                        int inicio = i;
                        int n = 1;
                        while (i + 1 < v.length && v[i + 1]) {
                            n++;
                            i++;
                        }
                        if (n >= 3) {
                            for (int j = inicio; j <= i; j++){
                                if (j == idx && c.getPinta() == p) {
                                    enEscalera = true;
                                }
                            }    
                        }
                    }
                    i++;
                }              
            }
            // Si la carta forma parte de un grupo o escalera, se le asigna un valor de 0
            if (!enEscalera) {
                int valor = idx + 1;
                // Las figuras (A, J, Q, K) valen 10 puntos
                if (valor == 1 || valor > 10) {
                    valor = 10;
                    }
                suma += valor; 
            }
        }
        return suma;
    }
}