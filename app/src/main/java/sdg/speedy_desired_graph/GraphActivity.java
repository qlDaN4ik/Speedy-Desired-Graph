package sdg.speedy_desired_graph;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;


public class GraphActivity extends Fragment implements View.OnClickListener {
    Button oC;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_graph, null);
        oC = (Button)v.findViewById(R.id.click);
        oC.setOnClickListener(this);
        GraphView graph = (GraphView) v.findViewById(R.id.graph);
        DataPoint[] DP = new DataPoint[SetValueActivity.X.size()];
        for (int i=0;i<SetValueActivity.X.size();i++)
            DP[i] = new DataPoint(SetValueActivity.a.get(i), SetValueActivity.b.get(i));
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(DP);
        graph.addSeries(series);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        series.setTitle("sinusoid");
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(SetValueActivity.Left);
        graph.getViewport().setMaxX(SetValueActivity.Right);

        DataPoint[] DP2 = new DataPoint[SetValueActivity.X.size()];
        for (int i=0;i<SetValueActivity.X.size();i++)
            DP2[i] = new DataPoint(SetValueActivity.X.get(i), SetValueActivity.Y.get(i));
        PointsGraphSeries<DataPoint> series2 = new PointsGraphSeries<DataPoint>(DP2);
        graph.addSeries(series2);
        series2.setTitle("sample");
        series2.setShape(PointsGraphSeries.Shape.POINT);
        series2.setColor(Color.RED);
        series2.setSize(10);
        return v;
    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.click:
                MainActivity.fTrans = getFragmentManager().beginTransaction();
                MainActivity.fTrans.replace(R.id.frgmCont, MainActivity.frag8);
                MainActivity.fTrans.commit();
        }
    }
}