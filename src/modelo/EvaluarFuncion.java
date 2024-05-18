package modelo;

import static java.lang.Math.PI;
import org.math.array.DoubleArray.*;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.lsmp.djep.djep.DJep;
import org.math.plot.Plot2DPanel;
import org.nfunk.jep.ParseException;

public class EvaluarFuncion {

    private String funcion;
    private String funcionDerivada;
    private double valorX;
    private JEP evaluarFyDF;
    private DJep DerivarF;

    public EvaluarFuncion() {
        this.funcion = "";
        this.funcionDerivada = "";
        this.valorX = 0.0;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getFuncionDerivada() {
        return funcionDerivada;
    }

    public void setFuncionDerivada(String funcionDerivada) {
        this.funcionDerivada = funcionDerivada;
    }

    public double getValorX() {
        return valorX;
    }

    public void setValorX(double valorX) {
        this.valorX = valorX;
    }

    public JEP getEvaluarFyDF() {
        return evaluarFyDF;
    }

    public void setEvaluarFyDF(JEP evaluarFuncion) {
        this.evaluarFyDF = evaluarFuncion;
    }

    public DJep getDerivarF() {
        return DerivarF;
    }

    public void setDerivarF(DJep DerivarF) {
        this.DerivarF = DerivarF;
    }

    public void derivarFuncion() throws ParseException {
        this.DerivarF = new DJep();
        this.DerivarF.addStandardConstants();
        this.DerivarF.addStandardFunctions();
        this.DerivarF.addComplex();
        this.DerivarF.setAllowUndeclared(true);
        this.DerivarF.setAllowAssignment(true);
        this.DerivarF.setImplicitMul(true);
        this.DerivarF.addStandardDiffRules();

        //Manejar las funciones como nodos, para poder derivarla
        Node f = this.DerivarF.parse(this.funcion);
        Node fd = this.DerivarF.differentiate(f, "x");
        fd = this.DerivarF.simplify(fd);
        this.funcionDerivada = this.DerivarF.toString(fd);
    }

    public double evaluarFuncion(double x) {
        this.valorX = x;
        this.evaluarFyDF = new JEP();
        this.evaluarFyDF.addStandardConstants();
        this.evaluarFyDF.addStandardFunctions();
        this.evaluarFyDF.setImplicitMul(true);

        this.evaluarFyDF.addVariable("x", this.valorX);
        this.evaluarFyDF.parseExpression(funcion);
        return this.evaluarFyDF.getValue();
    }

    public double evaluarFuncionDerivada(double x) {
        this.valorX = x;
        this.evaluarFyDF = new JEP();
        this.evaluarFyDF.addStandardConstants();
        this.evaluarFyDF.addStandardFunctions();

        this.evaluarFyDF.addVariable("x", this.valorX);
        this.evaluarFyDF.parseExpression(this.funcionDerivada);
        return this.evaluarFyDF.getValue();
    }
    
}
