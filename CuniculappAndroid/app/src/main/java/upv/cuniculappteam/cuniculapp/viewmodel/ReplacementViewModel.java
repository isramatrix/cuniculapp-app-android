package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.ReplacementDialog;

public class ReplacementViewModel extends ViewModel
{
    private List<Replacement> replacements;

    public Task<List<Replacement>> getReplacements(Farm farm)
    {
        return Tasks.call(() -> {

            List<Replacement> replacements = new ArrayList<>();

            Replacement r1 = new Replacement();
            r1.setDays(8);
            r1.setRabbitsAmount(35);
            replacements.add(r1);
            Replacement r2 = new Replacement();
            r2.setDays(11);
            r2.setRabbitsAmount(98);
            replacements.add(r2);
            Replacement r3 = new Replacement();
            r3.setDays(21);
            r3.setRabbitsAmount(175);
            replacements.add(r3);

            return replacements;
        });
    }

    public Task<List<Replacement>> deleteReplacements(Collection<Replacement> deletionReplacements)
    {
        return Tasks.call(() -> {
            replacements.removeAll(deletionReplacements);
            return replacements;
        });
    }

    public Task<List<Replacement>> addReplacement(ReplacementDialog.Result result)
    {
        return Tasks.call(() -> {
            replacements.add(new Replacement());
            return replacements;
        });
    }
}
