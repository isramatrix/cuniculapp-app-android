package upv.cuniculappteam.cuniculapp.activity.farms;

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
import upv.cuniculappteam.cuniculapp.activity.farms.main.FarmActivity;
import upv.cuniculappteam.cuniculapp.activity.utils.ModelLifecycleFragment;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.farms.FarmsAdapter;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.FarmDialog;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult.Header;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;
import upv.cuniculappteam.cuniculapp.viewmodel.FarmViewModel;

public class FarmsFragment extends ModelLifecycleFragment<Farm>
{
    private FarmViewModel farms;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_farms, container, false);
    }

    /**
     * Inicializa las vistas del fragmento de las granjas.
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState El estado de la instancia de la aplicación.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        farms = ViewModelProviders.of(this).get(FarmViewModel.class);
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Maneja el control de la vista de reciclaje para cambiar de actividad cuando
     * una granja concreta ha sido seleccionada.
     *
     * @param farm La granja seleccionada.
     */
    @Override
    public void onItemClicked(Farm farm)
    {
        Intent intent = new Intent(getActivity(), FarmActivity.class);
        intent.putExtra(FarmActivity.FARM_INTENT_KEY, (Parcelable) farm);
        startActivity(intent);
    }

    /**
     * Actualiza los datos de las granjas disponibles cuando reaparece en primer
     * plano el fragmento que muestra las granjas.
     *
     * @param hidden El estado de visibilidad del fragmento (<code>false</code> =
     *               está visible y debe actualizarse).
     */
    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);

        // Se muestran los datos de las granjas disponibles.
        if (!hidden) farms.getFarms().addOnSuccessListener(this::updateItems);
    }

    /**
     * Obtiene una instancia concreta del adaptador principal del fragmento que muestra
     * los elementos seleccionables.
     *
     * @return Una instancia de un adaptador multiselección
     */
    @Override
    public SelectableAdapter<Farm> getAdapter() {
        return new FarmsAdapter();
    }

    /**
     * Obtiene el identificador del recurso de la vista de reciclaje donde se muestran los
     * elementos del fragmento.
     *
     * @return El identificador del recurso.
     */
    @Override
    public int getAdapterId() {
        return R.id.recycler_farm;
    }

    /**
     * Obtiene una tarea programada de la que se obtienen los elementos a mostrar en el
     * adaptador de elementos principal del fragmento.
     *
     * @return Una tarea para obtener elementos.
     */
    @Override
    public Task<List<Farm>> getAdapterData() {
        return farms.getFarms();
    }

    /**
     * Obtiene una tarea programada en la que se eliminan los elementos seleccionados del
     * adaptador de elementos principal del fragmento.
     *
     * @param items Los elementos a eliminar.
     * @return La tarea en la que se eliminan dichos elementos.
     */
    @Override
    public Task<List<Farm>> onDeleteSelected(Collection<Farm> items) {
        return farms.deleteFarms(items);
    }

    /**
     * Obtiene una instancia de un diálogo donde se recogen los atributos de un nuevo elemento
     * a añadir en el adaptador de elementos principal del fragmento.
     *
     * @return Una instancia de un diálogo.
     */
    @Override
    public DialogFragment getAddDialog() {
        return new FarmDialog(Header.ADD, (r) -> farms.addFarm(r).addOnSuccessListener(this::updateItems));
    }
}