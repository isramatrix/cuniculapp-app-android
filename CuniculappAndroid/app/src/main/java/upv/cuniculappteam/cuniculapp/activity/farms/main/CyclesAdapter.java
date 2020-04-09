package upv.cuniculappteam.cuniculapp.activity.farms.main;

import android.view.View;

import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.view.Adapter;

public class CyclesAdapter extends Adapter<Cycle>
{
    public CyclesAdapter() { }

    public CyclesAdapter(List<Cycle> items) { super(items); }

    @Override
    public int getLayout() {
        return R.layout.recycler_cycle;
    }

    @Override
    public void onBindView(View view, Cycle item)
    {


    }
}
