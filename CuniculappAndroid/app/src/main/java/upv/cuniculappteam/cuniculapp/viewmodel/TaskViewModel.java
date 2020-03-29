package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import upv.cuniculappteam.cuniculapp.model.Labor;

import static upv.cuniculappteam.cuniculapp.logic.firebase.Firebase.Database;

public class TaskViewModel extends ViewModel
{
    public Task<Labor> getLabors()
    {
        return Database.fetch("tasks", Labor.class);
    }

    public Task<Void> addLabor(Labor labor)
    {
        return Database.add(labor.getId(), labor);
    }

    public Task<Void> completeLabor(Labor labor)
    {
        return null;
    }

}
