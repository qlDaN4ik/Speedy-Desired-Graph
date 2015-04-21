package sdg.speedy_desired_graph;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MenuListActivity extends Fragment implements OnClickListener {

    Button graph, data, select, about, functionx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_listmenu, null);

        graph = (Button) v.findViewById(R.id.graph);
        graph.setOnClickListener(this);

        data = (Button) v.findViewById(R.id.data);
        data.setOnClickListener(this);

        select = (Button) v.findViewById(R.id.select);
        select.setOnClickListener(this);

        about = (Button) v.findViewById(R.id.about);
        about.setOnClickListener(this);

        functionx = (Button) v.findViewById(R.id.functionx);
        functionx.setOnClickListener(this);
        return v;
    }
    public void onClick(View v) {
        MainActivity.fTrans = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.graph:
                MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag2);
                MainActivity.fTrans.commit();
                break;
            case R.id.data:
                MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag3);
                MainActivity.fTrans.commit();
                break;
            case R.id.select:
                MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag4);
                MainActivity.fTrans.commit();
                break;
            case R.id.functionx:
                MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag5);
                MainActivity.fTrans.commit();
                break;
            case R.id.about:
                MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag6);
                MainActivity.fTrans.commit();
                break;
        }
    }
}