package sdg.speedy_desired_graph;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DataActivity extends Fragment {
    private TextView dataView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_data, null);
        dataView = (TextView) v.findViewById(R.id.dataView);
        SetValueActivity.E /= (double) SetValueActivity.X.size();
        if(SetValueActivity.E==0) dataView.setText("Значения не заданы");
        else
        {
        dataView.setText("Ошибка="+Double.toString(SetValueActivity.E)+ "\n");
        int i=0;
        for (double x = SetValueActivity.Left; x < SetValueActivity.Right; x = x + SetValueActivity.H) {
            if(SetValueActivity.Count>SetValueActivity.ii) {
                dataView.setText(dataView.getText().toString() + "Входной параметр\n x=" + (SetValueActivity.a.get(i)) + "\n");
                dataView.setText(dataView.getText().toString() + "Непараметрическая оценка регрессии\n y=" + (SetValueActivity.b.get(i)) + "\n");
                dataView.setText(dataView.getText().toString() + "Действительное значение функции в точке y=" + (Math.sin(SetValueActivity.a.get(i))) + "\n");
                i++;
            }
        }
        }
        return v;
    }
}
