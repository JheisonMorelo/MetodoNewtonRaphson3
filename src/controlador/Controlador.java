package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.MetodoNewtonRaphson;
import vista.CalculadoraMetodoNewtonRaphson;
import vista.TablaDeDatos;

public class Controlador implements ActionListener {

    private MetodoNewtonRaphson calculadora;
    private CalculadoraMetodoNewtonRaphson vistaPrincipal;
    private TablaDeDatos tablaDeDatos;
    private DefaultTableModel modeloTabla;

    public Controlador() {
        this.vistaPrincipal = new CalculadoraMetodoNewtonRaphson();
        this.tablaDeDatos = new TablaDeDatos();
        this.modeloTabla = new DefaultTableModel();
        this.tablaDeDatos.getjTable1().setModel(modeloTabla);

        modeloTabla.addColumn("i");
        modeloTabla.addColumn("xi");
        modeloTabla.addColumn("f(xi)");
        modeloTabla.addColumn("f'(xi)");
        modeloTabla.addColumn("|xi - xi-1|");

        this.vistaPrincipal.getJbEjecutarMetodo().addActionListener(this);
        this.vistaPrincipal.getJbLimpiarCampos().addActionListener(this);
        this.tablaDeDatos.getJbCerrarVentana().addActionListener(this);
    }

    public CalculadoraMetodoNewtonRaphson getVistaPrincipal() {
        return vistaPrincipal;
    }

    public void setVistaPrincipal(CalculadoraMetodoNewtonRaphson vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
    }

    public TablaDeDatos getTablaDeDatos() {
        return tablaDeDatos;
    }

    public void setTablaDeDatos(TablaDeDatos tablaDeDatos) {
        this.tablaDeDatos = tablaDeDatos;
    }

    public MetodoNewtonRaphson getCalculadora() {
        return calculadora;
    }

    public void setCalculadora(MetodoNewtonRaphson calculadora) {
        this.calculadora = calculadora;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public void limpiarCampos() {
        this.vistaPrincipal.getJtfFuncion().setText("");
        this.vistaPrincipal.getJtfNumeroIteraciones().setText("");
        this.vistaPrincipal.getJtfValorInicial().setText("");
    }

    public void limpiarTabla() {
        int filas = modeloTabla.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            modeloTabla.removeRow(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == this.vistaPrincipal.getJbEjecutarMetodo()) {
                if (this.vistaPrincipal.getJtfFuncion().getText().equals("")
                        || this.vistaPrincipal.getJtfNumeroIteraciones().getText().equals("")
                        || this.vistaPrincipal.getJtfValorInicial().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese todos los datos");
                } else {
                    limpiarTabla();
                    this.tablaDeDatos.setVisible(true);
                    calculadora = new MetodoNewtonRaphson(this.vistaPrincipal.getJtfFuncion().getText(),
                            Integer.parseInt(this.vistaPrincipal.getJtfNumeroIteraciones().getText()),
                            Double.parseDouble(this.vistaPrincipal.getJtfValorInicial().getText()));
                    calculadora.setModeloTabla(modeloTabla);
                    calculadora.evaluarMetodoNewtonRaphson();
                }
            } else if (e.getSource() == this.vistaPrincipal.getJbLimpiarCampos()) {
                limpiarCampos();
            } else if (e.getSource() == this.tablaDeDatos.getJbCerrarVentana()) {
                this.tablaDeDatos.setVisible(false);
                this.vistaPrincipal.setVisible(true);
            }
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, er);
        }

    }

    public void run() {
        this.vistaPrincipal.setVisible(true);
    }

}
