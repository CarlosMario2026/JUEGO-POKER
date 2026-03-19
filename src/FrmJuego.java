import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {

    public FrmJuego(){
        setSize(500, 400);
        setTitle("JUEGO DE POKER");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(null);

        JButton btnRepartir = new JButton("REPARTIR");
        btnRepartir.setBounds(10, 10, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("VERIFICAR");
        btnVerificar.setBounds(120, 10, 100, 25);
        add(btnVerificar);

        JTabbedPane tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 50, 460, 300);
        add(tpJugadores);

        JPanel pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(0, 255, 0));

        JPanel pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(0, 255, 255));

        tpJugadores.addTab("Martin Estrada Contreras", pnlJugador1);
        tpJugadores.addTab("Carlos Mario Manco", pnlJugador2);

    }    

}
