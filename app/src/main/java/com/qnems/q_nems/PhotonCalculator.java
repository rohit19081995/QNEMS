package com.qnems.q_nems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.log10;

public class PhotonCalculator extends AppCompatActivity {

    private EditText kappa_value;
    private EditText eta_value;
    private EditText kappa_e_value;
    private EditText omega_c_value;
    private EditText omega_value;
    private EditText delta_value;
    private EditText power_value;
    private EditText photons_value;

    private Double kappa = 1e2;
    private Double eta = 0.5;
    private Double kappa_e = eta*kappa;
    private Double omega_c = 7.0;
    private Double omega = 6.0;
    private Double delta = omega-omega_c;
    private Double power_dbm = 0.0;
    private Double photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);

    private Boolean _ignore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photon_calculator);

        // kappa
        View kappa_inc = findViewById(R.id.kappa);
        ((TextView) kappa_inc.findViewById(R.id.name)).setText("\u03BA");
        kappa_value = (EditText) kappa_inc.findViewById(R.id.value);
        kappa_value.setText((kappa.toString()));
        ((TextView) kappa_inc.findViewById(R.id.unit)).setText("kHz");

        kappa_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                // change eta and photons
                String str = kappa_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double kappa = 1e3*Double.parseDouble(str);
                eta = kappa_e / kappa;
                eta_value.setText(eta.toString());
                photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);
                photons_value.setText(photons.toString());
                _ignore = false;
            }
        });

        // eta
        View eta_inc = findViewById(R.id.eta);
        ((TextView) eta_inc.findViewById(R.id.name)).setText("\u03B7");
        eta_value = (EditText) eta_inc.findViewById(R.id.value);
        eta_value.setText(eta.toString());
        ((TextView) eta_inc.findViewById(R.id.unit)).setText("");

        eta_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                // change kappa_e and photons
                String str = eta_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double eta = Double.parseDouble(str);
                kappa_e = eta*kappa;
                kappa_e_value.setText(kappa_e.toString());
                photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);
                photons_value.setText(photons.toString());
                _ignore = false;
            }
        });
        // kappa_e
        View kappa_e_inc = findViewById(R.id.kappa_e);
        ((TextView) kappa_e_inc.findViewById(R.id.name)).setText(Html.fromHtml("\u03BA<sub><small>e</small></sub>"));
        kappa_e_value = (EditText) kappa_e_inc.findViewById(R.id.value);
        kappa_e_value.setText(kappa_e.toString());
        ((TextView) kappa_e_inc.findViewById(R.id.unit)).setText("kHz");

        kappa_e_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                // change eta and photons
                String str = kappa_e_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double kappa_e = 1e3*Double.parseDouble(str);
                eta = kappa_e / kappa;
                eta_value.setText(eta.toString());
                photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);
                photons_value.setText(photons.toString());
                _ignore = false;
            }
        });
        // omega_c
        View omega_c_inc = findViewById(R.id.omega_c);
        ((TextView) omega_c_inc.findViewById(R.id.name)).setText(Html.fromHtml("\u03C9<sub><small>c</small></sub>"));
        omega_c_value = (EditText) omega_c_inc.findViewById(R.id.value);
        omega_c_value.setText(omega_c.toString());
        ((TextView) omega_c_inc.findViewById(R.id.unit)).setText("GHz");

        omega_c_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                // change delta and photons
                String str = omega_c_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double omega_c = 1e9*Double.parseDouble(str);
                delta = omega - omega_c;
                delta_value.setText(delta.toString());
                photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);
                photons_value.setText(photons.toString());
                _ignore = false;
            }
        });
        // omega
        View omega_inc = findViewById(R.id.omega);
        ((TextView) omega_inc.findViewById(R.id.name)).setText("\u03C9");
        omega_value = (EditText) omega_inc.findViewById(R.id.value);
        omega_value.setText(omega.toString());
        ((TextView) omega_inc.findViewById(R.id.unit)).setText("GHz");

        omega_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                // change delta and photons
                String str = omega_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double omega = 1e9*Double.parseDouble(str);
                delta = omega - omega_c;
                delta_value.setText(delta.toString());
                photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);
                photons_value.setText(photons.toString());
                _ignore = false;
            }
        });
        // delta
        View delta_inc = findViewById(R.id.delta);
        ((TextView) delta_inc.findViewById(R.id.name)).setText("\u0394");
        delta_value = (EditText) delta_inc.findViewById(R.id.value);
        delta_value.setText(delta.toString());
        ((TextView) delta_inc.findViewById(R.id.unit)).setText("GHz");

        delta_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                //change omega and photons
                String str = delta_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double delta = 1e9*Double.parseDouble(str);
                omega = delta + omega_c;
                omega_value.setText(omega.toString());
                photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);
                photons_value.setText(photons.toString());
                _ignore = false;
            }
        });
        // power
        View power_inc = findViewById(R.id.power);
        ((TextView) power_inc.findViewById(R.id.name)).setText(Html.fromHtml("P<sub><small>in</small></sub>"));
        power_value = (EditText) power_inc.findViewById(R.id.value);
        power_value.setText(power_dbm.toString());
        ((TextView) power_inc.findViewById(R.id.unit)).setText("GHz");

        power_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                // change photons
                String str = power_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double power_dbm = Double.parseDouble(str);
                photons = number_of_photons(power_dbm, omega, kappa_e, kappa, delta);
                photons_value.setText(photons.toString());
                _ignore = false;
            }
        });
        // photons
        View photons_inc = findViewById(R.id.photons);
        ((TextView) photons_inc.findViewById(R.id.name)).setText(Html.fromHtml("log<sub><small>10</small></sub>(N)"));
        photons_value = (EditText) photons_inc.findViewById(R.id.value);
        photons_value.setText(photons.toString());
        ((TextView) photons_inc.findViewById(R.id.unit)).setText("");

        photons_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (_ignore) return;
                _ignore = true;
                //change power
                String str = photons_value.getText().toString();
                if (str == null || str.length() <= 0 || str == "-") {
                    _ignore = false;
                    return;
                }
                Double photons = Double.parseDouble(str);
                power_dbm = power_in_dbm(photons, omega, kappa_e, kappa, delta);
                power_value.setText(power_dbm.toString());
                _ignore = false;
            }
        });

    }

    public static Double number_of_photons(Double power_dbm, Double omega, Double kappa_e, Double kappa, Double delta) {
        Double power_watt = 1e-3*Math.pow(10, power_dbm/10);
        kappa_e = kappa_e*10e3;
        omega = omega*10e9;
        kappa = kappa*1e3;
        delta = delta*1e9;
        Double h = 6.62607004e-34;
        return log10(power_watt*kappa_e*kappa_e/(h*omega*(Math.pow((kappa/2),2)+Math.pow(delta,2))));
    }

    public static Double power_in_dbm(Double photons, Double omega, Double kappa_e, Double kappa, Double delta) {
        kappa_e = kappa_e*10e3;
        omega = omega*10e9;
        kappa = kappa*1e3;
        delta = delta*1e9;
        Double h = 6.62607004e-34;
        Double power_watt = Math.pow(10,photons)*h*omega*(Math.pow((kappa/2),2)+Math.pow(delta,2))/(kappa_e*kappa_e);
        return 10*log10( power_watt) + 30;
    }
}
