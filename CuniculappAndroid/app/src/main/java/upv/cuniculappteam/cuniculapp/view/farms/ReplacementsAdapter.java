package upv.cuniculappteam.cuniculapp.view.farms;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.Adapter;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;

public class ReplacementsAdapter extends SelectableAdapter<Replacement>
{
    public ReplacementsAdapter() { }

    public ReplacementsAdapter(List<Replacement> items) { super(items); }

    @Override
    public int getLayout() {
        return R.layout.recycler_replacement;
    }

    @Override
    public void onBindView(View view, Replacement replacement)
    {
        super.onBindView(view, replacement);

        TextView daysText = view.findViewById(R.id.replacement_days_text);
        daysText.setText(replacement.getDays().toString());

        TextView rabbitsText = view.findViewById(R.id.replacement_rabbits_text);
        rabbitsText.setText(replacement.getRabbitsAmount().toString());
    }
}
