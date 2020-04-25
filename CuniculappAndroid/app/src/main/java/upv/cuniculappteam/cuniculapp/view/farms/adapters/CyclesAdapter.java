package upv.cuniculappteam.cuniculapp.view.farms.adapters;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;

import static java.util.Calendar.*;
import static java.util.Calendar.YEAR;

public class CyclesAdapter extends SelectableAdapter<Cycle>
{
    @Override
    public int getLayout() {
        return R.layout.recycler_cycle;
    }

    @Override
    public void onBindView(View view, Cycle cycle)
    {
        super.onBindView(view, cycle);

        TextView nameText = view.findViewById(R.id.cycle_name_text);
        nameText.setText(cycle.getName(view.getContext()));
    }
}
