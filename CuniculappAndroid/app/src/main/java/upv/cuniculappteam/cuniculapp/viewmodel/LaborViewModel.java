package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import upv.cuniculappteam.cuniculapp.logic.firebase.Firebase;
import upv.cuniculappteam.cuniculapp.model.Labor;

import static upv.cuniculappteam.cuniculapp.logic.firebase.Firebase.Database;

public class LaborViewModel extends ViewModel
{
    public Task<List<Labor>> getLabors()
    {
        return Database.fetchAll(Labor.class);
    }

    public Task<Void> addLabor(Labor labor)
    {
        return Firebase.Database.add(Labor.class, labor);
    }

    public Task<List<Labor>> getArchivedLabors()
    {
        return Firebase.Database.fetchWhere("state", Labor.State.ARCHIVED, Labor.class);
    }

    public Task<List<Labor>> getUnarchivedLabors()
    {
        Task<List<Labor>> taskDone =
                Firebase.Database.fetchWhere("state", Labor.State.DONE, Labor.class);
        Task<List<Labor>> taskTodo =
                Firebase.Database.fetchWhere("state", Labor.State.TO_DO, Labor.class);
        List<Labor> taskResult =  new ArrayList<Labor>();
        /*
        return Tasks.whenAllSuccess(taskTodo, taskDone).continueWith((Task<List<Labor>> task) ->
            {
                List<List<Labor>> lRes = task.getResult();
                for (List<Labor> t : lRes) taskResult.addAll(t);
                return taskResult;
            }
        );*/
        return taskDone;
    }

    public Task<Void> completeLabor(Labor labor)
    {
        if (!labor.isFinished()) labor.setFinishedDate(new Date());
        return Database.update(labor, Labor.class);
    }

    public Task<Void> doLabor(Labor labor)
    {
        if (labor.getState() == Labor.State.TO_DO) labor.setState(Labor.State.DONE);
        return Database.update(labor, Labor.class);
    }

    public Task<Void> UndoLabor(Labor labor)
    {
        if (labor.getState() == Labor.State.DONE) labor.setState(Labor.State.TO_DO);
        return Database.update(labor, Labor.class);
    }

    public Task<Void> archiveLabor(Labor labor)
    {
        if (labor.getState() == Labor.State.DONE) labor.setState(Labor.State.ARCHIVED);
        return Database.update(labor, Labor.class);
    }

    public Task<Void> unArchiveLabor(Labor labor)
    {
        if (labor.getState() == Labor.State.ARCHIVED) labor.setState(Labor.State.DONE);
        return Database.update(labor, Labor.class);
    }

    public Task<List<Labor>> deleteLabors(Collection<Labor> labors)
    {
        for (Labor labor : labors)
            Firebase.Database.delete(Labor.class, labor);
        return null;
    }

}
