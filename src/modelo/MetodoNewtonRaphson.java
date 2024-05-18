package modelo;

import javax.swing.table.DefaultTableModel;
import org.nfunk.jep.ParseException;

public class MetodoNewtonRaphson {

    private String funcion;
    private int iteraciones;
    private double x;
    private EvaluarFuncion evaluador;
    private DefaultTableModel modeloTabla;

    public MetodoNewtonRaphson(String funcion, int iteraciones, double x) {
        this.funcion = funcion;
        this.iteraciones = iteraciones;
        this.x = x;
        this.evaluador = new EvaluarFuncion();
        this.evaluador.setFuncion(funcion);
        this.modeloTabla = new DefaultTableModel();
    }

    public int getIteraciones() {
        return iteraciones;
    }

    public void setIteraciones(int iteraciones) {
        this.iteraciones = iteraciones;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public EvaluarFuncion getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(EvaluarFuncion evaluador) {
        this.evaluador = evaluador;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }

    public double iteracionSiguiente(double x) {
        return x - evaluador.evaluarFuncion(x) / evaluador.evaluarFuncionDerivada(x);
    }

    public void evaluarMetodoNewtonRaphson() throws ParseException {

        evaluador.derivarFuncion();

        modeloTabla.addRow(new Object[]{0,
            String.format("%.15f", x),
            String.format("%.15f", this.evaluador.evaluarFuncion(x)),
            String.format("%.15f", this.evaluador.evaluarFuncionDerivada(x)),
            "Vacío"});

        for (int i = 1; i <= iteraciones; i++) {

            x = iteracionSiguiente(x);

            modeloTabla.addRow(new Object[]{i,
                String.format("%.15f", x),
                String.format("%.15f", this.evaluador.evaluarFuncion(x)),
                String.format("%.15f", this.evaluador.evaluarFuncionDerivada(x)),
                String.format("%.15f", Math.abs(iteracionSiguiente(x) - x))});

        }

    }

}
