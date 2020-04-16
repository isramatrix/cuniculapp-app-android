package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.model.Cycle;

public class RabbitsFragment extends NamedFragment
{
    private final Cycle cycle;

    RabbitsFragment(Cycle cycle) { this.cycle = cycle; }

    @Override
    public int getFragmentName() {
        return R.string.main_rabbits;
    }

}
