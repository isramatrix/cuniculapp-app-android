package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import upv.cuniculappteam.cuniculapp.logic.firebase.Firebase;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.model.facilities.Location;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.FarmDialog;

public class FarmViewModel extends ViewModel
{
    private Task<List<Farm>> getFarms(Object... params) { return getFarms(); }

    public Task<List<Farm>> getFarms()
    {
        return Firebase.Database.fetchAll(Farm.class);
    }

    public Task<Void> addFarm(Farm farm)
    {
        return Firebase.Database.add(Farm.class, farm);
    }

    public Task<Void> deleteFarms(Collection<Farm> farms)
    {
        List<Task<?>> tasks = new ArrayList<>();
        for (Farm farm : farms) tasks.add(Firebase.Database.delete(Farm.class, farm));
        return Tasks.whenAll(tasks);
    }
}
