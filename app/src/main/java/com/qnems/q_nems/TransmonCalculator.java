package com.qnems.q_nems;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class TransmonCalculator extends AppCompatActivity {

    String[] nameArray = {"E<sub><small>C</small></sub>/2\u03C0",
                        "C<sub><small>\u03A3</small></sub>",
                        "E<sub><small>J</small></sub>/2\u03C0",
                        "L<sub><small>J</small></sub>",
                        "I<sub><small>C</small></sub>",
                        "R",
                        "\u03C9<sub><small>10</small></sub>/2\u03C0",
                        "\u03C9<sub><small>21</small></sub>/2\u03C0",
                        "\u03C9<sub><small>20</small></sub>/2\u03C0",
                        "\u03B1/2\u03C0"};
    Double[] valueArray = {8.3, 4.2, 3.2,5.6,7.8,2.7,1.2,2.2,3.2,4.2 };
    String[] unitsArray = {"GHz", "fF", "GHz", "nH", "nA", "\u03A9", "GHz", "GHz", "GHz", "GHz"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transmon_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CalculatorAdapter calculatorAdapter = new CalculatorAdapter(this, nameArray, valueArray, unitsArray);
        listView = (ListView) findViewById(R.id.calc_list_view);
        listView.setAdapter(calculatorAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
