package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.Date;
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
        return Database.fetchWhere("cycle", cycle.getId(), Labour.class);
    }

    public Task<List<Insemination>> getInseminations(Cycle cycle)
    {
        return Database.fetchWhere("cycle", cycle.getId(), Insemination.class);
    }

    public Task<List<Palpation>> getPalpations(Cycle cycle)
    {
        return Database.fetchWhere("cycle", cycle.getId(), Palpation.class);
    }

    public Task<List<Sale>> getSales(Cycle cycle)
    {
        return Database.fetchWhere("cycle", cycle.getId(), Sale.class);
    }

    public Task<Void> setInseminated(Insemination insemination, int inseminated)
    {
        insemination.setInseminatedRabbits(inseminated);
        insemination.setDate(new Date());
        return Database.update(insemination, Insemination.class);
    }

    public Task<Void> setPalpated(Palpation palpation, int palpated)
    {
        palpation.setPregnantRabbits(palpated);
        palpation.setDate(new Date());
        return Database.update(palpation, Palpation.class);
    }

    public Task<Void> setBorn(Labour labour, int births, int bornAlive, int bornDead)
    {
        labour.setBirthsAmount(births);
        labour.setBornDead(bornDead);
        labour.setBornAlive(bornAlive);
        labour.setDate(new Date());
        return Database.update(labour, Labour.class);
    }

    public Task<Void> setSold(Sale sale, int sold, int salePrice, int averageWeight)
    {
        sale.setSalePrize(salePrice);
        sale.setAverageWeight(averageWeight);
        sale.setSold(sold);
        sale.setDate(new Date());
        return Database.update(sale, Sale.class);
    }

    public Task<Void> updateLabour(Labour labour)
    {
        return Database.update(labour, Labour.class);
    }

    public Task<Void> updateInsemination(Insemination insemination)
    {
        return Database.update(insemination, Insemination.class);
    }

    public Task<Void> updatePalpation(Palpation palpation)
    {
        return Database.update(palpation, Palpation.class);
    }

    public Task<Void> updateSale(Sale sale)
    {
        return Database.update(sale, Sale.class);
    }
}
