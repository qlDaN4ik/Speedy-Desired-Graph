package sdg.speedy_desired_graph;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class SetValueActivity extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    String tX, tY, tl, tr, tst, tsn, ts;
    static private TextView ValueLeftView, ValueRightView, ValueStepView, ValueSelectNumberView, ValueScaleView;
    static public double Left = 0;
    static public double Right = 0;
    static public double H = 0;
    static public int Count = 0;
    static public int N = 0;
    static public double[] X;
    static public double[] Y;
    static public double C = 0;
    static public double y = 0;
    static public int ii = 0;
    static public int iii = 0;
    static public double[] a;
    static public double[] b;
    static public double E = 0;
    static public boolean enable=false;
    CheckBox SelectCheckbox;
    TextView textView2, textView5;
    EditText samplex, sampley;
    Button setsample, Cancel, Ready;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_setvalue, null);
        Cancel = (Button)v.findViewById(R.id.Cancel);
        Cancel.setOnClickListener(this);
        Ready = (Button) v.findViewById(R.id.Ready);
        Ready.setOnClickListener(this);
        setsample = (Button) v.findViewById(R.id.setsample);
        setsample.setOnClickListener(this);
        ValueLeftView = (TextView) v.findViewById(R.id.ValueLeftView);
        ValueRightView = (TextView) v.findViewById(R.id.ValueRightView);
        ValueStepView = (TextView) v.findViewById(R.id.ValueStepView);
        ValueSelectNumberView = (TextView) v.findViewById(R.id.ValueSelectNumberView);
        ValueScaleView = (TextView) v.findViewById(R.id.ValueScaleView);
        textView2 = (TextView) v.findViewById(R.id.textView2);
        textView5 = (TextView) v.findViewById(R.id.textView5);
        samplex = (EditText) v.findViewById(R.id.samplex);
        sampley = (EditText) v.findViewById(R.id.sampley);
        SelectCheckbox = (CheckBox) v.findViewById(R.id.selectcheckbox);
        SelectCheckbox.setOnCheckedChangeListener(this);
        return v;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        if (isChecked)
        {
            textView2.setVisibility(View.VISIBLE);
            textView5.setVisibility(View.VISIBLE);
            samplex.setVisibility(View.VISIBLE);
            sampley.setVisibility(View.VISIBLE);
            setsample.setVisibility(View.VISIBLE);
        }
        else
        {
            textView2.setVisibility(View.GONE);
            textView5.setVisibility(View.GONE);
            samplex.setVisibility(View.GONE);
            sampley.setVisibility(View.GONE);
            setsample.setVisibility(View.GONE);
        }
    }

    public void onClick(View v) {
        tl = ValueLeftView.getText().toString();
        tr = ValueRightView.getText().toString();
        tst = ValueStepView.getText().toString();
        tsn = ValueSelectNumberView.getText().toString();
        ts = ValueScaleView.getText().toString();
        if (tsn.isEmpty() == false) N = Integer.parseInt(tsn);
    switch (v.getId()) {
        case R.id.Ready:
            if (tl.isEmpty() == true || tr.isEmpty() == true || tst.isEmpty() == true || tsn.isEmpty() == true || ts.isEmpty() == true) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Значения не заданы", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                Left = Double.parseDouble(tl);
                Right = Double.parseDouble(tr);
                if(Right<Left)
                {
                    double c=Left;
                    Left=Right;
                    Right=c;
                }
                H = Double.parseDouble(tst);
                N = Integer.parseInt(tsn);
                C = Double.parseDouble(ts);
                X = new double[N];
                Y = new double[N];
                Count = (int) ((Right - Left) / H + 2);
                a = new double[Count];
                b = new double[Count];
                if (SelectCheckbox.isChecked() == false) {
                    for (int i = 0; i < N; i++) {
                        X[i] = Math.random() * (Right - Left) + Left;
                        Y[i] = Math.sin(X[i]) + Math.random() * 0.4 - 0.2;
                    }
                }
                if (SelectCheckbox.isChecked() == true && X[N - 1] == 0 || Y[N - 1] == 0) {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Заданы не все значения выборки", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                    ii = 0;
                    for (double x = Left; x < Right; x = x + H) {
                        if(Count>ii) {
                            y = G(x, X, Y, N, C);
                            a[ii] = x;
                            b[ii] = y;
                            ii++;
                        }
                    }
                    for (int i = 0; i < N; i++) {
                        E = E + Math.abs(Y[i] - M(X[i], X, Y, N, C));
                    }
                    E /= (double) N;

                enable=true;

                MainActivity.fTrans = getFragmentManager().beginTransaction();
                MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag7);
                MainActivity.fTrans.commit();
                }


            break;
        case R.id.Cancel:
            ValueLeftView.setText("");
            ValueRightView.setText("");
            ValueStepView.setText("");
            ValueSelectNumberView.setText("");
            ValueScaleView.setText("");
            break;
        case R.id.setsample:
            if (tsn.isEmpty() == true)
            {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Не задано количество значений выборки", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            else {
                if (iii < N) {
                    tX = samplex.getText().toString();
                    tY = sampley.getText().toString();
                    if (tX.isEmpty() == true || tY.isEmpty() == true || tX == "0" || tY == "0") {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Значения выбоки заданы неверно", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        samplex.setText("");
                        sampley.setText("");
                    } else {
                        X[iii] = Double.parseDouble(tX);
                        Y[iii] = Double.parseDouble(tY);
                        iii++;
                        samplex.setText("");
                        sampley.setText("");
                    }
                } else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Заданы все значения выборки", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            break;
    }
    }

    static public double F(double z) {
        double f = 0;
        if (z * z <= 5)
            f = 0.335 - 0.067 * z * z;
        return f;
    }

    static public double G(double x, double[] X, double[] Y, int N, double C) {
        double y = 0;
        double s1 = 0;
        double s2 = 0;
        for (int i = 0; i < N; i++) {
            s1 += Y[i] * F((x - X[i]) / C);
            s2 += F((x - X[i]) / C);
        }
        if (s2 == 0) y = 0;
        else y = s1 / s2;
        return y;
    }

    static public double M(double x, double[] X, double[] Y, int N, double C) {
        double y = 0;
        double s1 = 0;
        double s2 = 0;
        for (int i = 0; i < N; i++) {
            if (X[i] != x) {
                s1 += Y[i] * F((x - X[i]) / C);
                s2 += F((x - X[i]) / C);
            }
        }
        if (s2 == 0) y = 0;
        else y = s1 / s2;
        return y;
    }

}