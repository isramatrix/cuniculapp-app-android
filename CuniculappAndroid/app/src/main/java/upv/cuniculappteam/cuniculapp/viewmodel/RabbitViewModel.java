package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.animals.Kitten;
import upv.cuniculappteam.cuniculapp.model.animals.Mother;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog.KittenResult;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog.MotherResult;

public class RabbitViewModel extends ViewModel
{
    public Task<Mother> getMothers(Cycle cycle)
    {
        return Tasks.call(() -> null);
    }

    public Task<Kitten> getKittens(Cycle cycle)
    {
        return Tasks.call(() -> null);
    }

    public Task<Void> addMother(MotherResult result)
    {
        return null;
    }

    public Task<Void> removeMother(MotherResult result)
    {
        return null;
    }

    public Task<Void> addKitten(KittenResult result)
    {
        return null;
    }

    public Task<Void> removeKitten(KittenResult result)
    {
        return null;
    }

}
