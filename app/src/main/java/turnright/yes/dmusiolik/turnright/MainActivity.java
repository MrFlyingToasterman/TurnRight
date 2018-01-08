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
        print2log("---Y AXIS---");
        printBasicForm("0");
        print2log("Y = " + d);
        Y = d;

        //X axis
        print2log("");
        print2log("---X AXIS---");
        printBasicForm("X");
    }

    public void pqFormula() {

    }

    public void printBasicForm(String X) {
        DecimalFormat fmt = new DecimalFormat("+#,##0.00;-#");
        print2log("f(" + X + ") =  " + fmt.format(x3) + " * " + X + "³ " + fmt.format(x2) + " * " +  X + "² " + fmt.format(x1) + " * " + X + " " + fmt.format(d));
    }

    public void print2log(String logme) {
        TextView output = (TextView) findViewById(R.id.txt_output);
        output.setText(output.getText() + " " + logme + "\n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
