package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;

public class CycleViewModel extends ViewModel
{
    public Task<List<Cycle>> getCycles(Farm farm)
    {
        return Tasks.call(() -> {

            List<Cycle> cycles = new ArrayList<>();
            Cycle c;

            c = new Cycle();
            c.setName("cicloA");
            cycles.add(c);
            c = new Cycle();
            c.setName("cicloF");
            cycles.add(c);
            c = new Cycle();
            c.setName("cicloFA");
            cycles.add(c);

            return cycles;
        });
    }
}
