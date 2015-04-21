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
    int N=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_select, null);

        ValuesSampleXView = (TextView) v.findViewById(R.id.ValuesSampleXView);
        ValuesSampleYView = (TextView) v.findViewById(R.id.ValuesSampleYView);

        if (SetValueActivity.E == 0) ValuesSampleXView.setText("Значения не заданы");
        else {
            for (int i = 0; i < SetValueActivity.N; i++) {
                SetValueActivity.X[i] *= 10000;
                SetValueActivity.X[i] = (int) SetValueActivity.X[i];
                SetValueActivity.X[i] /= 10000;
                ValuesSampleXView.setText(ValuesSampleXView.getText().toString() + "\n" + Double.toString(SetValueActivity.X[i]));
                SetValueActivity.Y[i] *= 10000;
                SetValueActivity.Y[i] = (int) SetValueActivity.Y[i];
                SetValueActivity.Y[i] /= 10000;
                ValuesSampleYView.setText(ValuesSampleYView.getText().toString() + "\n" + Double.toString(SetValueActivity.Y[i]));
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
    }

}