package com.example.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class QuadraticEquationSolver extends ActionBarActivity {

    protected float Discriminant = 0;
    //a = 0, b = 0, c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic_equation_solver);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quadratic_equation_solver, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quadratic_equation_solver, container, false);
            return rootView;
        }
    }

    public String solveEquation(View view)
    {
        EditText inputTxt = (EditText) findViewById(R.id.editText);
        TextView textView = (TextView) findViewById(R.id.textViewSolution);

        // Store EditText in Variable
        String str = inputTxt.getText().toString();
        String[] splitStrings = str.split(" ");
        float a = Float.parseFloat(splitStrings[0]);
        float b = Float.parseFloat(splitStrings[1]);
        float c = Float.parseFloat(splitStrings[2]);

        if (a != 0)
        {
            Discriminant = b * b - 4 * a * c;

            if (Discriminant == 0)
            {
                textView.setText(DiscriminantEqualZero(a, b, Discriminant));
                return DiscriminantEqualZero(a, b, Discriminant);
            }

            if (Discriminant > 0)
            {
                textView.setText(DiscriminantGreaterThanZero(a, b, Discriminant));
                return DiscriminantGreaterThanZero(a, b, c);

            }

            if (Discriminant < 0)
            {
                textView.setText(DiscriminantLessThanZero(a, b, c, Discriminant));
                return DiscriminantLessThanZero(a, b, c, Discriminant);
            }

            return "Quadratic equation return path";
        }
        else
        {
            textView.setText(LinearEquation(b, c));
            return LinearEquation(b, c);
        }

    }



    private String DiscriminantEqualZero(float a, float b, float Discriminant)
    {

        return "The equation has only one root.\n\r" + "Discriminant: "
                + Discriminant + "\n\rRoot: " + (-b - Math.sqrt(Discriminant)) / (2 * a);

    }

    private String DiscriminantGreaterThanZero(float a, float b, float Discriminant)
    {
        double x1 = (-b - Math.sqrt(Discriminant)) / (2 * a);
        double x2 = (-b + Math.sqrt(Discriminant)) / (2 * a);

        return "The equation has two roots.\n\r" + "Discriminant: " + Discriminant
                + "\n\rRoot1: " + x1 + "\n\r" + "Root2: " + x2;
    }

    private String DiscriminantLessThanZero(float a, float b, float c, float Discriminant)
    {
        double xReal = -b / (2 * a);
        double xImaginary = (Math.sqrt(4 * a * c - b * b)) / (2 * a);

        return "The equation has two complex roots.\n\r" + "Discriminant: "
                + Discriminant
                + "\n\rRoot1: " + xReal + " + i" + xImaginary
                + "\n\rRoot2: " + xReal + " - i" + xImaginary;
    }

    private String LinearEquation(float b, float c)
    {
        return "Linear equation has only one root: " + (-c / b);
    }

}
