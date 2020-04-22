package upv.cuniculappteam.cuniculapp.activity.farms.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.Task;

import java.util.Collection;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.ModelLifecycleFragment;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.activity.farms.replacements.ReplacementActivity;
import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.ReplacementDialog;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.ReplacementDialog.Result;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult.Header;
import upv.cuniculappteam.cuniculapp.view.farms.adapters.ReplacementsAdapter;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;
import upv.cuniculappteam.cuniculapp.viewmodel.ReplacementViewModel;

public class ReplacementsFragment extends ModelLifecycleFragment<Replacement> implements
        NamedFragment
{
    private final Farm farm;

    private ReplacementViewModel replacements;

    ReplacementsFragment(Farm farm) { this.farm = farm; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_replacements, container, false);
    }

    /**
     * Inicializa las vistas del fragmento de las reposiciones de una granja.
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState El estado de la instancia de la aplicación.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        replacements = ViewModelProviders.of(this).get(ReplacementViewModel.class);
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Maneja el control de la vista de reciclaje para cambiar de actividad cuando
     * una reposición concreta ha sido seleccionada.
     *
     * @param replacement La reposición seleccionada.
     */
    @Override
    public void onItemClicked(Replacement replacement)
    {
        Intent intent = new Intent(getActivity(), ReplacementActivity.class);
        intent.putExtra(ReplacementActivity.REPLACEMENT_INTENT_KEY, (Parcelable) replacement);
        startActivity(intent);
    }

    /**
     * Obtiene una instancia concreta del adaptador principal del fragmento que muestra
     * los elementos seleccionables.
     *
     * @return Una instancia de un adaptador multiselección.
     */
    @Override
    public SelectableAdapter<Replacement> getAdapter() {
        return new ReplacementsAdapter();
    }

    /**
     * Obtiene el identificador del recurso de la vista de reciclaje donde se muestran los
     * elementos del fragmento.
     *
     * @return El identificador del recurso.
     */
    @Override
    public int getAdapterId() {
        return R.id.recycler_replacements;
    }

    /**
     * Obtiene una tarea programada de la que se obtienen los elementos a mostrar en el
     * adaptador de elementos principal del fragmento.
     *
     * @return Una tarea para obtener elementos.
     */
    @Override
    public Task<List<Replacement>> getAdapterData() {
        return replacements.getReplacements(farm);
    }

    /**
     * Obtiene una tarea programada en la que se eliminan los elementos seleccionados del
     * adaptador de elementos principal del fragmento.
     *
     * @param replacements Los elementos a eliminar.
     * @return La tarea en la que se eliminan dichos elementos.
     */
    @Override
    public Task<List<Replacement>> onDeleteSelected(Collection<Replacement> replacements)
    {
        return this.replacements.deleteReplacements(replacements).continueWithTask(
                (t) -> this.replacements.getReplacements(farm)
        );
    }

    /**
     * Obtiene una instancia de un diálogo donde se recogen los atributos de un nuevo elemento
     * a añadir en el adaptador de elementos principal del fragmento.
     *
     * @return Una instancia de un diálogo.
     */
    @Override
    public DialogFragment getAddDialog() {
        return new ReplacementDialog(Header.ADD, (r) -> createItem(makeReplacement(r)) );
    }

    private Task<List<Replacement>> makeReplacement(Result result)
    {
        Replacement replacement = new Replacement();
        replacement.setDays(result.getDays());
        replacement.setRabbitsAmount(result.getRabbits());
        replacement.setFarm(farm.getId());

        return replacements.addReplacement(replacement).continueWithTask(
                (t) -> replacements.getReplacements(farm)
        );
    }

    @Override
    public int getFragmentName() { return R.string.main_replacement; }
}
