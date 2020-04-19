package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.model.facilities.Location;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.FarmDialog;

public class FarmViewModel extends ViewModel
{
    private List<Farm> farms = new ArrayList<>();

    public FarmViewModel()
    {
        Farm f1 = new Farm();
        f1.setName("Granjamiento");
        f1.setJailsAmount(32);
        f1.setLocalization(new Location());
        farms.add(f1);

        Farm f2 = new Farm();
        f2.setName("Granjerina");
        f2.setJailsAmount(17);
        f2.setLocalization(new Location());
        farms.add(f2);
    }

    public Task<List<Farm>> getFarms()
    {
        return Tasks.call(() -> farms);
    }

    public Task<List<Farm>> addFarm(FarmDialog.Result result)
    {
        return Tasks.call(() -> {
            farms.add(new Farm());
            return farms;
        });
    }

    public Task<List<Farm>> deleteFarms(Collection<Farm> farms)
    {
        return Tasks.call(() -> {
            this.farms.removeAll(farms);
            return this.farms;
        });
    }
}
