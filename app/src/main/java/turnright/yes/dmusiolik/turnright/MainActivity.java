package turnright.yes.dmusiolik.turnright;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Global Varz
    //Main Target
    public double x3 = 0;
    public double x2 = 0;
    public double x1 = 0;
    public double d = 0;
    //Sec Target
    public double Y = 0;
    //Terz Target

    //Start
    public void calc(View view) {
        clearLog();
        //Read in
        print2log("Prepare calculation ...");
        TextView txt_x3 = (TextView) findViewById(R.id.txt_x3);
        TextView txt_x2 = (TextView) findViewById(R.id.txt_x2);
        TextView txt_x1 = (TextView) findViewById(R.id.txt_x);
        TextView txt_d = (TextView) findViewById(R.id.txt_d);
        print2log("Checking conditions ...");

        print2log("Setup Varz ...");
        x3 = Double.parseDouble("" + txt_x3.getText());
        x2 = Double.parseDouble("" + txt_x2.getText());
        x1 = Double.parseDouble("" + txt_x1.getText());
        d = Double.parseDouble("" + txt_d.getText());

        axisIntersections();
    }

    public void axisIntersections() {
        //Y axis
        print2log("");
        print2log("---Y-AXIS---");
        printBasicForm("0");
        print2log("Y = " + d);
        Y = d;

        //X axis
        print2log("");
        print2log("---X-AXIS---");
        printBasicForm("X");
        double hornerHistory[] = hornerschema();
        print2log("Hornerschema History:");
        printHistory(hornerHistory);
    }

    public double[] hornerschema() {
        double iks1 = x1;
        double iks2 = x2;
        double iks3 = x3;
        double locald = d;

        double startrange = -20;
        double stoprange = 20;

        double ergebnis0 = 0;
        double ergebnis1 = 0;
        double gen = 0.1;

        double[] history = new double[4];

        for (int i = 0; startrange <= stoprange; startrange = startrange + Math.round(gen * 100) / 100.0) {

            startrange = Math.round(startrange * 100) / 100.0;
            x3 = Math.round(x3 * 100) /100.0;

            ergebnis0 = 0;
            ergebnis0 = (startrange * x3) + x2;
            ergebnis0 = Math.round(ergebnis0 * 100) / 100.0;
            history[1] = ergebnis0;

            ergebnis1 = 0;
            ergebnis1 = (startrange * ergebnis0) + x1;
            ergebnis1 = Math.round(ergebnis1 * 100) / 100.0;
            history[2] = ergebnis1;

            ergebnis0 = 0;
            ergebnis0 = (startrange * ergebnis1) + locald;
            ergebnis0 = Math.round(ergebnis0 * 100) / 100.0;
            history[3] = ergebnis0;


            if (ergebnis0 == 0.0) {
                history[0] = startrange;
                return history;
            }

        }
        print2log("CANT FIND ANY ZEROPOINTS!");
        return history;
    }

    public void pqFormula() {

    }

    public void printHistory(double[] history) {
        for(int i = 0; i < history.length; i++) {
            print2log("History " + i + ":  " + history[i]);
        }
    }

    public void printBasicForm(String X) {
        DecimalFormat fmt = new DecimalFormat("+#,##0.00;-#");
        print2log("f(" + X + ") =  " + fmt.format(x3) + " * " + X + "³ " + fmt.format(x2) + " * " +  X + "² " + fmt.format(x1) + " * " + X + " " + fmt.format(d));
    }

    public void print2log(String logme) {
        TextView output = (TextView) findViewById(R.id.txt_output);
        output.setText(output.getText() + " " + logme + "\n");
    }

    public void clearLog() {
        TextView output = (TextView) findViewById(R.id.txt_output);
        output.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
