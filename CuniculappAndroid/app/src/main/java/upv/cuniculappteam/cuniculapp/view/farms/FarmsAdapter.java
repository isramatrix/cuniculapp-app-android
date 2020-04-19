package upv.cuniculappteam.cuniculapp.view.farms;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;

public class FarmsAdapter extends SelectableAdapter<Farm>
{
    public FarmsAdapter() { super(new ArrayList<>()); }

    public FarmsAdapter(List<Farm> items) { super(items); }

    @Override
    public int getLayout() { return R.layout.recycler_farm; }

    @Override
    public void onBindView(View view, Farm farm)
    {
        super.onBindView(view, farm);

        TextView farmTitleText = view.findViewById(R.id.farm_title);
        farmTitleText.setText(farm.getName());

        TextView jailsText = view.findViewById(R.id.farm_jails);
        jailsText.setText(farm.getJailsAmount().toString());

        TextView cyclesText = view.findViewById(R.id.farm_cycles);
        cyclesText.setText("¿?");

        TextView locationText = view.findViewById(R.id.farm_location);
        locationText.setText(farm.getLocalization().toString());
    }
}
