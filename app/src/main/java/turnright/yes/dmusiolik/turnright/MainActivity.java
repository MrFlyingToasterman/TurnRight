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
    //derivations
    double[] deriv1 = new double[3];
    double[] deriv2 = new double[2];
    double[] deriv3 = new double[1];
    //Terz Target
    double xone = 0;
    double xtwo = 0;

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
        derivation();
        extremeValues();
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
        //Check for Zero positions
        if (hornerHistory[4] == 0.0) {
            print2log("Zero position found! @" + hornerHistory[0]);
        }else {
            print2log("Math Error!");
            return;
        }
        print2log("Looking for more Zero positions ...");
        double fixed_hoernerhistory[] = solveTheEquation(hornerHistory);
        print2log("Fixed Horner History:");
        printHistory(fixed_hoernerhistory);
        pqFormula(fixed_hoernerhistory[2],fixed_hoernerhistory[3]);
        print2log("Found Zero position: " + xone);
        print2log("Found Zero position: " + xtwo);
    }

    public void derivation(){
        double localx3 = x3;
        double localx2 = x2;
        double localx1 = x1;

        localx3 = localx3 * 3;
        localx2 = localx2 * 2;

        deriv1[0] = localx3;
        deriv1[1] = localx2;
        deriv1[2] = localx1;

        localx3 = localx3 * 2;

        deriv2[0] = localx3;
        deriv2[1] = localx2;

        deriv3[0] = localx3;

        print2log("First derviation");
        printDerv(deriv1);
        print2log("Secound derviation");
        printDerv(deriv2);
        print2log("Third derviation");
        printDerv(deriv3);
    }

    public void extremeValues(){
        double[] History = new double[4];
        History[1] = deriv1[0];
        History[2] = deriv1[1];
        History[3] = deriv1[2];

        double[] fixedH = solveTheEquation(History);
        double p = fixedH[2];
        double q = fixedH[3];
        double lxone = -p/2 + Math.sqrt((((p/2)*(p/2)) -q));
        double lxtwo = -p/2 - Math.sqrt((((p/2)*(p/2)) -q));

        lxone = Math.round(lxone * 100) / 100.0;
        lxtwo = Math.round(lxtwo * 100) / 100.0;

        double lyone = x3 * ((lxone)*(lxone)*(lxone)) + x2 * ((lxone)*(lxone)) + x1 * lxone + d ;
        double lytwo = x3 * ((lxtwo)*(lxtwo)*(lxtwo)) + x2 * ((lxtwo)*(lxtwo)) + x1 * lxtwo + d ;

        lyone = Math.round(lyone * 100) / 100.0;
        lytwo = Math.round(lytwo * 100) / 100.0;

        String p1 = "";
        String p2 = "";

        double lxyone = (deriv2[0] * lxone) + deriv2[1];

        if(lxyone < 0.0){
            p1 = "HP " + "("+ lxone + "|" + lyone + ")";
        }else if (lxyone > 0.0){
            p1 = "LP " + "("+ lxone + "|" + lyone + ")";
        }else{
            p1 = "N/A" + "("+ lxone + "|" + lyone + ")";
        }

        print2log(p1);

        double lxytwo = (deriv2[0] * lxtwo) + deriv2[1];

        if(lxytwo < 0.0){
            p2 = "HP " + "("+ lxtwo + "|" + lytwo + ")";
        } else if (lxytwo > 0.0){
            p2 = "LP " + "("+ lxtwo + "|" + lytwo + ")";
        }else{
            p2 = "N/A" + "("+ lxtwo + "|" + lytwo + ")";
        }

        print2log(p2);

    }

    public double[] solveTheEquation(double History[]) {




        double subst = History[1];
        subst = Math.round(subst * 100) / 100.0;
        History[1] = History[1] / subst;
        History[2] = History[2] / subst;
        History[3] = History[3] / subst;



        return History;
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

        double[] history = new double[5];

        for (int i = 0; startrange <= stoprange; startrange = startrange + Math.round(gen * 100) / 100.0) {

            startrange = Math.round(startrange * 100) / 100.0;
            x3 = Math.round(x3 * 100) /100.0;
            history[1] = x3;

            ergebnis0 = 0;
            ergebnis0 = (startrange * x3) + x2;
            ergebnis0 = Math.round(ergebnis0 * 100) / 100.0;
            history[2] = ergebnis0;

            ergebnis1 = 0;
            ergebnis1 = (startrange * ergebnis0) + x1;
            ergebnis1 = Math.round(ergebnis1 * 100) / 100.0;
            history[3] = ergebnis1;

            ergebnis0 = 0;
            ergebnis0 = (startrange * ergebnis1) + locald;
            ergebnis0 = Math.round(ergebnis0 * 100) / 100.0;
            history[4] = ergebnis0;


            if (ergebnis0 == 0.0) {
                history[0] = startrange;
                return history;
            }

        }
        print2log("CANT FIND ANY ZEROPOINTS!");
        return history;
    }

    public void pqFormula(double p, double q) {
        xone = -p/2 + Math.sqrt((((p/2)*(p/2)) -q));
        xtwo = -p/2 - Math.sqrt((((p/2)*(p/2)) -q));
    }

    public void printHistory(double[] history) {
        for(int i = 0; i < history.length; i++) {
            print2log("History " + i + ":  " + history[i]);
        }
    }

    public void printDerv(double[] input) {
        DecimalFormat fmt = new DecimalFormat("+#,##0.00;-#");
        String preF = "";
        switch (input.length){
            case 1:
                preF = "f ' ' ' (X) = " + fmt.format(deriv3[0]);
                break;
            case 2:
                preF = "f ' ' (X) = " + fmt.format(deriv2[0]) + "X " + fmt.format(deriv2[1]);
                break;
            case 3:
                preF = "f ' (X) = " + fmt.format(deriv1[0]) + "X² " + fmt.format(deriv1[1]) + "X " + fmt.format(deriv1[2]);
                break;
        }

        print2log(preF);
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
