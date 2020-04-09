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
        return Tasks.call(ArrayList::new);
    }
}
