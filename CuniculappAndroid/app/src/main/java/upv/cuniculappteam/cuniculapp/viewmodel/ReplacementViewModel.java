package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;

public class ReplacementViewModel extends ViewModel
{
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
}
