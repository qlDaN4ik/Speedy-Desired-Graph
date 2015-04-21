package sdg.speedy_desired_graph;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class StartActivity extends Fragment implements View.OnClickListener {
    Button start;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_start, null);
        start = (Button)v.findViewById(R.id.start);
        start.setOnClickListener(this);
        return v;
    }
    public void onClick(View v){
        MainActivity.fTrans = getFragmentManager().beginTransaction();
        MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag1);
        MainActivity.fTrans.commit();
    }
}
