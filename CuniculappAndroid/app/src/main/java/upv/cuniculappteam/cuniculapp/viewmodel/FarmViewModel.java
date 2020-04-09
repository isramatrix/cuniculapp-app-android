package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.model.facilities.Location;

public class FarmViewModel extends ViewModel
{
    public Task<List<Farm>> getFarms()
    {
        return Tasks.call(() -> {
                List<Farm> list = new ArrayList();

                Farm f1 = new Farm();
                f1.setName("Granjamiento");
                f1.setJailsAmount(32);
                f1.setLocalization(new Location());
                list.add(f1);

                Farm f2 = new Farm();
                f2.setName("Granjerina");
                f2.setJailsAmount(17);
                f2.setLocalization(new Location());
                list.add(f2);

                return list;
            }
        );
    }
}
