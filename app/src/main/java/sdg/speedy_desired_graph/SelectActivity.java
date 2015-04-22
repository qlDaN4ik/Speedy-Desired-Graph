package sdg.speedy_desired_graph;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class SelectActivity extends Fragment implements OnClickListener {
    Button Save, Load;
    private final static String SampleX = "sample.txt";
    private final static String SampleY = "sample2.txt";
    private TextView ValuesSampleXView ,ValuesSampleYView;
    int N = 0;
    double[] X;
    double[] Y;
    double H=0;
    double C=0;
    double Right=0;
    double Left=0;
    double Count=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_select, null);

        ValuesSampleXView = (TextView) v.findViewById(R.id.ValuesSampleXView);
        ValuesSampleYView = (TextView) v.findViewById(R.id.ValuesSampleYView);

        if (SetValueActivity.E == 0) ValuesSampleXView.setText("Значения не заданы");
        else {
            for (int i = 0; i < SetValueActivity.X.size(); i++) {
                SetValueActivity.X.set(i, SetValueActivity.X.get(i)*10000);
                SetValueActivity.X.set(i, SetValueActivity.X.get(i));
                SetValueActivity.X.set(i, SetValueActivity.X.get(i)/10000);
                ValuesSampleXView.setText(ValuesSampleXView.getText().toString() + "\n" + Double.toString(SetValueActivity.X.get(i)));
                SetValueActivity.Y.set(i, SetValueActivity.Y.get(i)*10000);
                SetValueActivity.Y.set(i, SetValueActivity.Y.get(i));
                SetValueActivity.Y.set(i, SetValueActivity.Y.get(i)/10000);
                ValuesSampleYView.setText(ValuesSampleYView.getText().toString() + "\n" + Double.toString(SetValueActivity.Y.get(i)));
            }
        }

        Save = (Button) v.findViewById(R.id.Save);
        Save.setOnClickListener(this);

        Load = (Button) v.findViewById(R.id.Load);
        Load.setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Save:
                saveText(SampleX);
                saveText2(SampleY);
                break;
            case R.id.Load:
                loadText(SampleX);
                loadText2(SampleY);
                break;
        }
    }
    private void saveText(String SampleX) {
        N=SetValueActivity.N;
        C=SetValueActivity.C;
        Left=SetValueActivity.Left;
        Right=SetValueActivity.Right;
        H=SetValueActivity.H;
        Count=SetValueActivity.Count;
        X = new double[N];
        Y = new double[N];
        for(int i=0; i<N;i++)
        {
            X[i]=SetValueActivity.X.get(i);
            Y[i]=SetValueActivity.Y.get(i);
        }
        try {
            OutputStream outputStream = getActivity().openFileOutput(SampleX, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(ValuesSampleXView.getText().toString());
            osw.close();
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Выборка сохранена", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        } catch (Throwable t) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void saveText2(String SampleY) {
        try {
            OutputStream outputStream = getActivity().openFileOutput(SampleY, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(ValuesSampleYView.getText().toString());
            osw.close();
        } catch (Throwable t) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }


    private void loadText(String SampleX) {
        ValuesSampleXView.setText("");
        ValuesSampleYView.setText("");
        try {
            InputStream inputStream = getActivity().openFileInput(SampleX);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }

                inputStream.close();
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Выборка загружена", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
                ValuesSampleXView.setText(builder.toString());
            }
        } catch (Throwable t) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }

    }
    private void loadText2(String SampleY) {
        try {
            InputStream inputStream = getActivity().openFileInput(SampleY);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                inputStream.close();
                ValuesSampleYView.setText(builder.toString());
            }
        } catch (Throwable t) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
        SetValueActivity.X.clear();
        SetValueActivity.Y.clear();
        SetValueActivity.a.clear();
        SetValueActivity.b.clear();
        SetValueActivity.E=0;
        for(int i=0; i<N;i++)
        {
            SetValueActivity.X.add(X[i]);
            SetValueActivity.Y.add(Y[i]);
        }

        int ii = 0;
        double y=0;
        for (double x = Left; x < Right; x = x + H) {
            if(Count>ii) {
                y = SetValueActivity.G(x, SetValueActivity.X, SetValueActivity.Y, C);
                SetValueActivity.a.add(x);
                SetValueActivity.b.add(y);
                ii++;
            }
        }
        for (int i = 0; i < SetValueActivity.X.size(); i++) {
            SetValueActivity.E = SetValueActivity.E + Math.abs(SetValueActivity.X.get(i) - SetValueActivity.M(SetValueActivity.X.get(i), SetValueActivity.X, SetValueActivity.Y, C));
        }
        SetValueActivity.E /= (double) SetValueActivity.X.size();
    }

}