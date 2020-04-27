package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.List;

import upv.cuniculappteam.cuniculapp.logic.firebase.Firebase;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.animals.Kitten;
import upv.cuniculappteam.cuniculapp.model.animals.KittenChange;
import upv.cuniculappteam.cuniculapp.model.animals.Mother;
import upv.cuniculappteam.cuniculapp.model.animals.MotherChange;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog;


public class RabbitViewModel extends ViewModel
{
    public Task<Mother> getMothers(Cycle cycle)
    {
        return Firebase.Database.fetchWhere("cycle", cycle.getId(), Mother.class).continueWith(
                (mothers) -> mothers.getResult().get(0)
        );
    }

    public Task<Kitten> getKittens(Cycle cycle)
    {
        return Firebase.Database.fetchWhere("cycle", cycle.getId(), Kitten.class).continueWith(
                (kittens) -> kittens.getResult().get(0)
        );
    }

    public Task<Void> addMother(MotherChange mother)
    {
        return Firebase.Database.add(MotherChange.class, mother)
                .continueWithTask((t) -> (Task<Mother>) Firebase.Database.fetch(mother.getId(), Mother.class))
                .continueWithTask((t) -> {
                    if (t.getResult() == null) throw new NullPointerException();
                    t.getResult().setAlive(t.getResult().getAlive() + mother.getAmount());
                    return Firebase.Database.update(t.getResult(), Mother.class);
                });
}

    public Task<Void> removeMother(MotherChange mother)
    {
        return Firebase.Database.add(MotherChange.class, mother)
                .continueWithTask((t) -> (Task<Mother>) Firebase.Database.fetch(mother.getId(), Mother.class))
                .continueWithTask((t) -> {
                    if (t.getResult() == null) throw new NullPointerException();
                    t.getResult().setAlive(t.getResult().getAlive() - mother.getAmount());
                    return Firebase.Database.update(t.getResult(), Mother.class);
                });
    }

    public Task<Void> addKitten(KittenChange kitten)
    {
        return Firebase.Database.add(KittenChange.class, kitten)
                .continueWithTask((t) -> (Task<Kitten>) Firebase.Database.fetch(kitten.getId(), Kitten.class))
                .continueWithTask((t) -> {
                    if (t.getResult() == null) throw new NullPointerException();
                    t.getResult().setMaternal(t.getResult().getMaternal() + kitten.getMaternalAmount());
                    t.getResult().setMeat(t.getResult().getMeat() + kitten.getMeatAmount());
                    return Firebase.Database.update(t.getResult(), Kitten.class);
                });
    }

    public Task<Void> removeKitten(KittenChange kitten)
    {
        return Firebase.Database.add(KittenChange.class, kitten)
                .continueWithTask((t) -> (Task<Kitten>) Firebase.Database.fetch(kitten.getId(), Kitten.class))
                .continueWithTask((t) -> {
                    if (t.getResult() == null) throw new NullPointerException();
                    t.getResult().setMaternal(t.getResult().getMaternal() - kitten.getMaternalAmount());
                    t.getResult().setMeat(t.getResult().getMeat() - kitten.getMeatAmount());
                    return Firebase.Database.update(t.getResult(), Kitten.class);
                });
    }

    public Task<List<MotherChange>> getMotherChanges(Mother mother)
    {
        return Firebase.Database.fetchWhere("mother", mother.getId(), MotherChange.class);
    }

    public Task<List<KittenChange>> getKittenChanges(Kitten kitten)
    {
        return Firebase.Database.fetchWhere("kitten", kitten.getId(), KittenChange.class);
    }
}