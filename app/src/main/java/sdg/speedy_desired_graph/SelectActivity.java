package sdg.speedy_desired_graph;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SelectActivity extends Fragment implements OnClickListener {
    Button Save, Load;
    final String LOG_TAG = "myLogs";
    final String FILENAME = "file.txt";
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
                saveText();
                break;
            case R.id.Load:
                loadText();
                break;
        }
    }
    void saveText() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE)));
            bw.write("Содержимое файла");
            bw.close();
            Log.d(LOG_TAG, "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Выборка сохранена", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    void loadText(){
        ValuesSampleXView.setText("");
        ValuesSampleYView.setText("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    getActivity().openFileInput(FILENAME)));
            String str = "";
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Выборка загружена", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}