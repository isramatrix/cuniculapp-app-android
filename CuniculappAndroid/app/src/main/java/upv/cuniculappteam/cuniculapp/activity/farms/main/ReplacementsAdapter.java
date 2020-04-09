package upv.cuniculappteam.cuniculapp.activity.farms.main;

import android.view.View;

import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.view.Adapter;

public class ReplacementsAdapter extends Adapter<Replacement>
{
    public ReplacementsAdapter() { }

    public ReplacementsAdapter(List<Replacement> items) { super(items); }

    @Override
    public int getLayout() {
        return R.layout.recycler_replacement;
    }

    @Override
    public void onBindView(View view, Replacement item) {

    }
}
