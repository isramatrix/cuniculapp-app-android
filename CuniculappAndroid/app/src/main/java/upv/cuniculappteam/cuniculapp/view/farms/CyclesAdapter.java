package upv.cuniculappteam.cuniculapp.view.farms;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.Adapter;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;

public class CyclesAdapter extends SelectableAdapter<Cycle>
{
    public CyclesAdapter() {
        super();
    }

    public CyclesAdapter(List<Cycle> items) { super(items); }

    @Override
    public int getLayout() {
        return R.layout.recycler_cycle;
    }

    @Override
    public void onBindView(View view, Cycle cycle)
    {
        super.onBindView(view, cycle);

        TextView nameText = view.findViewById(R.id.cycle_name_text);
        nameText.setText(cycle.getName());
    }
}
