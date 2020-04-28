package upv.cuniculappteam.cuniculapp.view.labors;

import android.view.View;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;

public class LaborAdapter extends SelectableAdapter<Labor>
{
    @Override
    public int getLayout() { return R.layout.recycler_labor; }

    @Override
    public void onBindView(View view, Labor item)
    {
        super.onBindView(view, item);
    }
}
