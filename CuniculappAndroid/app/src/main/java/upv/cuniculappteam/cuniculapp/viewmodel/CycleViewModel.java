package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import upv.cuniculappteam.cuniculapp.logic.firebase.Firebase;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.CycleDialog;

public class CycleViewModel extends ViewModel
{

    public Task<List<Cycle>> getCycles(Farm farm)
    {
        return Firebase.Database.fetchWhere("farm", farm.getId(), Cycle.class);
    }

    public Task<Void> deleteCycles(Collection<Cycle> cycles)
    {
        List<Task<?>> tasks = new ArrayList<>();
        for (Cycle cycle : cycles)
            tasks.add(Firebase.Database.delete(Cycle.class, cycle));
        return Tasks.whenAll(tasks);
    }

    public Task<Void> addCycle(Cycle cycle)
    {
        return Firebase.Database.add(Cycle.class, cycle);
    }
}
