package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.List;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.animals.Kitten;
import upv.cuniculappteam.cuniculapp.model.animals.Mother;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog;


public class RabbitViewModel extends ViewModel
{
    public Task<List<Mother>> getMothers(Cycle cycle)
    {
        return Tasks.call(() -> null);
    }

    public Task<List<Kitten>> getKittens(Cycle cycle)
    {
        return Tasks.call(() -> null);
    }

    public Task<Void> addMother(MotherDialog.Result result)
    {
        return null;
    }

    public Task<Void> removeMother(MotherDialog.Result result)
    {
        return null;
    }

    public Task<Void> addKitten(KittenDialog.Result result)
    {
        return null;
    }

    public Task<Void> removeKitten(KittenDialog.Result result)
    {
        return null;
    }
}