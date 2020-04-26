package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.List;

import static upv.cuniculappteam.cuniculapp.logic.firebase.Firebase.Database;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.labors.Insemination;
import upv.cuniculappteam.cuniculapp.model.labors.Labour;
import upv.cuniculappteam.cuniculapp.model.labors.Palpation;
import upv.cuniculappteam.cuniculapp.model.labors.Sale;

public class EventViewModel extends ViewModel
{
    public Task<Event> getEvent(Cycle cycle)
    {
        return Tasks.call(() -> {

            return new Event();
        });
    }

    public static class Event { }

    public Task<List<Labour>> getLabours(Cycle cycle)
    {
        return Database.fetchWhere("cycleID", cycle.getId(), Labour.class);
    }

    public Task<List<Insemination>> getInseminations(Cycle cycle)
    {
        return Database.fetchWhere("cycleID", cycle.getId(), Insemination.class);
    }

    public Task<List<Palpation>> getPalpations(Cycle cycle)
    {
        return Database.fetchWhere("cycleID", cycle.getId(), Palpation.class);
    }

    public Task<List<Sale>> getSales(Cycle cycle)
    {
        return Database.fetchWhere("cycleID", cycle.getId(), Sale.class);
    }



}
