import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carta {

    private int indice;

    public Carta(Random r){
         indice = r.nextInt(52) + 1;
    }

    public void mostrar(JPanel pnl ,int x ,int y){

        String archivoCarta = "Imagenes/carta"+indice+".jpg";
        ImageIcon ImgCarta = new ImageIcon(getClass() .getResource(archivoCarta));

        JLabel lblCarta = new JLabel(ImgCarta);
        lblCarta.setBounds(x, y,ImgCarta.getIconWidth(),ImgCarta.getIconHeight());
        pnl.add(lblCarta);
    
     }
}


 
