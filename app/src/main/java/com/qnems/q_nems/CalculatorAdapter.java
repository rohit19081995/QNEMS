package com.qnems.q_nems;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by wayne on 31/10/17.
 */

public class CalculatorAdapter extends ArrayAdapter {

    private final Activity context;
    private final String[] nameArray;
    private final String[] unitsArray;
    private final Double[] valueArray;

    public CalculatorAdapter(Activity context, String[] nameArrayParam, Double[] valueArrayParam, String[] unitsArrayParam){

        super(context,R.layout.calculator_element , nameArrayParam);

        this.context = context;
        this.nameArray = nameArrayParam;
        this.valueArray = valueArrayParam;
        this.unitsArray = unitsArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.calculator_element, null,true);

        TextView nameView = (TextView) rowView.findViewById(R.id.calc_value_name);
        EditText valueView = (EditText) rowView.findViewById(R.id.calc_value);
        TextView unitView = (TextView) rowView.findViewById(R.id.calc_unit);

        //this code sets the values of the objects to values from the arrays
        nameView.setText(Html.fromHtml(nameArray[position]));
        valueView.setText(String.format("%e",valueArray[position]));
        unitView.setText(Html.fromHtml(unitsArray[position]));

        return rowView;

    };
}
