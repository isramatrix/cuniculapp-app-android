package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import upv.cuniculappteam.cuniculapp.model.Labor;

import static upv.cuniculappteam.cuniculapp.logic.firebase.Firebase.Database;

public class LaborViewModel extends ViewModel
{
    public Task<List<Labor>> getLabors()
    {
        return Database.fetchAll(Labor.class);
    }

    public Task<List<Labor>> addLabor(Labor labor)
    {
        return null;// Database.add(labor.getId(), labor);
    }

    public Task<Void> completeLabor(Labor labor)
    {
        return null;
    }

    public Task<List<Labor>> deleteLabors(Collection<Labor> labors)
    {
        return Tasks.call(ArrayList::new);
    }
}
