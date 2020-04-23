package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import upv.cuniculappteam.cuniculapp.logic.firebase.Firebase;
import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.ReplacementDialog;

public class ReplacementViewModel extends ViewModel
{
    public Task<List<Replacement>> getReplacements(Farm farm)
    {
        return Firebase.Database.fetchWhere("farm", farm.getId(), Replacement.class);
    }

    public Task<Void> deleteReplacements(Collection<Replacement> replacements)
    {
        List<Task<?>> tasks = new ArrayList<>();
        for (Replacement replacement : replacements)
            tasks.add(Firebase.Database.delete(Replacement.class, replacement));
        return Tasks.whenAll(tasks);
    }

    public Task<Void> addReplacement(Replacement replacement)
    {
        return Firebase.Database.add(Replacement.class, replacement);
    }
}
