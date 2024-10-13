package Vista.Paneles;

import Logica.JugadorRanking;
import Logica.Ranking;
import Vista.Controladores.ControladorVista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPantallaRanking2 extends JPanel {
    private JTable tablaJugadores = new JTable();
    private JButton btn_volver = new JButton("Volver"); // Initialize the button
    private Ranking ranking;
    private ControladorVista controlador_vistas;

    public PanelPantallaRanking2(ControladorVista controlador_vistas, Ranking rankingParametro){
        this.controlador_vistas = controlador_vistas;
        this.ranking = rankingParametro;

        setLayout(new BorderLayout());
        add(tablaJugadores, BorderLayout.CENTER);

        // Create a panel for the button (to position it nicely)
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btn_volver);
        add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the south

        llenarTabla();
        registrar_oyente_boton_volver(); // Register the action listener
    }

    // ... (llenarTabla() method remains the same) ...

    protected void registrar_oyente_boton_volver() {
        btn_volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador_vistas.mostrar_pantalla_inicial();
            }
        });
    }

    public void llenarTabla() {
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nombre", "Puntaje"}
        );

        tablaJugadores.setModel(tableModel);

        Iterable<JugadorRanking> listaJugadores;
        listaJugadores = ranking.mostrarRanking();
        for (JugadorRanking jugador : listaJugadores) {
            Object[] fila = new Object[2];
            fila[0] = jugador.getNombre();
            fila[1] = jugador.getPuntaje();

            tableModel.addRow(fila);
        }
    }

}