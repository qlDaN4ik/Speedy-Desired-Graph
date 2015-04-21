package sdg.speedy_desired_graph;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class XFunctionActivity extends Fragment implements View.OnClickListener {
    static private TextView xfunction;
    static private EditText valuex;
    Button findy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_xfunction, null);
        findy = (Button)v.findViewById(R.id.findy);
        findy.setOnClickListener(this);
        xfunction = (TextView) v.findViewById(R.id.xfunction);
        valuex = (EditText) v.findViewById(R.id.valuex);
        return v;
    }

    public void onClick(View v) {
        String xs = valuex.getText().toString();
        double x = Double.parseDouble(xs);
        if (SetValueActivity.Left>x || SetValueActivity.Right<x) {
            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Заданного значения x не существует", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            xfunction.setText("");
        }
        else;
        double y = SetValueActivity.G(x, SetValueActivity.X, SetValueActivity.Y, SetValueActivity.N, SetValueActivity.C);
        xfunction.setText("y="+Double.toString(y));
    }
}
