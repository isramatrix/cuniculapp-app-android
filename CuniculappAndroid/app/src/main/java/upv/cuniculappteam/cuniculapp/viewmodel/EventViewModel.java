package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import upv.cuniculappteam.cuniculapp.model.Cycle;

public class EventViewModel extends ViewModel
{
    public Task<Event> getEvent(Cycle cycle)
    {
        return Tasks.call(() -> {

            return new Event();
        });
    }

    public static class Event { }
}
