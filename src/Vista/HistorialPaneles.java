package Vista;

import javax.swing.*;
import java.util.Stack;

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
