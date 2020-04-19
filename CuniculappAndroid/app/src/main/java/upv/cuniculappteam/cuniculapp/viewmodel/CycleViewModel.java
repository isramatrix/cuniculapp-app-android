package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.CycleDialog;

public class CycleViewModel extends ViewModel
{
    private List<Cycle> cycles = new ArrayList<>();

    public CycleViewModel() {
        super();
        Cycle c;
        c = new Cycle();
        c.setName("cicloA");
        cycles.add(c);
        c = new Cycle();
        c.setName("cicloF");
        cycles.add(c);
        c = new Cycle();
        c.setName("cicloFA");
        cycles.add(c);
    }

    public Task<List<Cycle>> getCycles(Farm farm)
    {
        return Tasks.call(() -> cycles );
    }

    public Task<List<Cycle>> deleteCycles(Collection<Cycle> cycles)
    {
        return Tasks.call(() -> {
            this.cycles.removeAll(cycles);
            return this.cycles;
        });
    }

    public Task<List<Cycle>> addCycle(CycleDialog.Result result)
    {
        return Tasks.call(() -> {
            this.cycles.add(new Cycle());
            return this.cycles;
        });
    }
}
