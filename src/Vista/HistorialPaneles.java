package Vista;

import java.util.Stack;

import javax.swing.JPanel;

public class HistorialPaneles {
	protected Stack<JPanel> historialPaneles;

    public HistorialPaneles() {
        historialPaneles = new Stack<>();
    }

    public void push(JPanel panel) {
        historialPaneles.push(panel);
    }

    public JPanel pop() {
        return historialPaneles.isEmpty() ? null : historialPaneles.pop();
    }

    public boolean isEmpty() {
        return historialPaneles.isEmpty();
    }
}
