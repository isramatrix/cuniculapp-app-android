package upv.cuniculappteam.cuniculapp.activity.farms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;

import java.util.Collection;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.farms.main.FarmActivity;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.Adapter;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult.Header;
import upv.cuniculappteam.cuniculapp.view.farms.FarmsAdapter;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.FarmDialog;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;
import upv.cuniculappteam.cuniculapp.viewmodel.FarmViewModel;

public class FarmsFragment extends Fragment implements
        Adapter.OnItemClickListener<Farm>,
        SelectableAdapter.SelectionLifecycleObserver<Farm>
{
    private static final String ADD_FARM_TAG = "FarmDialog";

    private FarmViewModel farms;

    private FarmsAdapter adapter;

    private Menu menu;

    private TextView deletionCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_farms, container, false);
    }

    /**
     * Inicializa las vistas del fragmento de las granjas.
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState El estado de la instancia de la aplicación.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        farms = ViewModelProviders.of(this).get(FarmViewModel.class);

        // Se inicializa la vista de las granjas disponibles.
        RecyclerView farmRecycler;
        farmRecycler = view.findViewById(R.id.recycler_farm);
        farmRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        farmRecycler.setAdapter(adapter = new FarmsAdapter());

        // Se inicializa la lógica del adaptador que muestra las granjas.
        adapter.setOnItemClickedListener(this);
        adapter.setSelectionLifecycleObserver(this);
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
        if (!hidden) farms.getFarms().addOnSuccessListener(adapter::changeData);

        // En caso de ocultarse, deshabilita la multiselección del adaptador.
        else adapter.setSelectionMode(false);
    }

    /**
     * Infla la vista del menú cabecera con los botones para añadir y quitar granjas.
     *
     * @param menu El menú cabecera.
     * @param inflater El objeto mediador para inflar la vista de menú.
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(this.menu = menu, inflater);
        inflater.inflate(R.menu.action_manage_items, menu);
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
     * Maneja el control de la vista de menú para añadir o quitar una granja en
     * función del botón de menú seleccionado.
     *
     * @param item El botón de menú seleccionado.
     * @return Si el control ha sido manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            // Si se quiere añadir una granja, muestra un diálogo con los datos a introducir.
            case R.id.action_add: showAddDialog(); return true;

            // Si se quiere eliminar las granjas, cambia el estado del adaptador a multiselección.
            case R.id.action_remove: adapter.setSelectionMode(true); return true;

            // Si se quiere confirmar el borrado de granjas, muestra un diálogo de confirmación.
            case R.id.action_count: showConfirmDeletionDialog(); return true;

            // Si se quiere salir del modo de borrado de granjas, cambia el estado de multiseleción.
            case R.id.action_clear: adapter.setSelectionMode(false); return true;

            // En caso contrario, el control no ha sido manejado.
            default: return false;
        }
    }

    /**
     * Crea y muestra un diálogo específico para introducir los datos de una nueva granja.
     */
    private void showAddDialog()
    {
        DialogFragment dialog = new FarmDialog(Header.ADD, farms::addFarm);

        if (getFragmentManager() != null)
            dialog.show(getFragmentManager(), ADD_FARM_TAG);
    }

    /**
     * Crea y muestra un diálogo específico para confirmar el borrado de granjas.
     */
    private void showConfirmDeletionDialog()
    {
        if (getActivity() == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.farm_dialog_delete_header)
                .setMessage(R.string.farm_dialog_delete_text)
                .setPositiveButton(R.string.farm_dialog_delete_confirm,
                        (v, id) -> deleteSelectedFarms(adapter.getSelectedItems()))
                .setNegativeButton(R.string.farm_dialog_delete_dismiss,
                        (v, id) -> adapter.setSelectionMode(false));

        builder.create().show();
    }

    /**
     * Crea y muestra un diálogo específico que informa que el borrado de granjas no ha
     * podido ser efectuado.
     */
    private void showFailedDeletionDialog()
    {
        // TODO: Crear el diálogo.
    }

    /**
     * Borra las granjas seleccionadas y actualiza los datos a mostrar en la vista del adaptador.
     *
     * @param deletionFarms Las granjas a borrar.
     */
    private void deleteSelectedFarms(Collection<Farm> deletionFarms)
    {
        farms.deleteFarms(deletionFarms).addOnSuccessListener(
                (v) -> farms.getFarms().addOnSuccessListener(adapter::changeData)
        ).addOnFailureListener(
                (v) -> showFailedDeletionDialog()
        );

        adapter.setSelectionMode(false);
    }

    /**
     * Cuando la multiselección de las granjas disponibles se inicia, se muestra el menú
     * auxiliar para borrar las granjas seleccionadas.
     */
    @Override
    public void onSelectionStart()
    {
        // Oculta el nombre de la actividad.
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity != null ? activity.getSupportActionBar() : null;
        if (actionBar != null) actionBar.setDisplayShowTitleEnabled(false);

        // Cambia la vista del menú de acciones.
        if (menu == null || activity == null) return;
        menu.clear();
        activity.getMenuInflater().inflate(R.menu.action_delete_items, menu);

        // AVISO: Dado que el element 'action_count' es un layout y no un botón, se debe
        // manejar de manera explicita la llamada a la acción como un item de menú.
        MenuItem item = menu.findItem(R.id.action_count);
        item.getActionView().setOnClickListener(
                (i) -> menu.performIdentifierAction(item.getItemId(), 0)
        );

        // Inicializa el conteo de elementos seleccionados.
        deletionCount = activity.findViewById(R.id.action_menu_deletion_count);
        deletionCount.setText("0");
    }

    /**
     * Cuando un elemento de la multiselección de granjas se selecciona, actializa el
     * conteo de granjas seleccionadas del menú de borrado de granjas.
     *
     * @param item La granja seleccioanda.
     */
    @Override
    public void onItemSelected(Farm item)
    {
        if (deletionCount != null)
            deletionCount.setText(String.valueOf(adapter.getSelectedItems().size()));
    }

    /**
     * Cuando un elemento de la multiselección de granjas se elimina, actializa el
     * conteo de granjas seleccionadas del menú de borrado de granjas.
     *
     * @param item La granja seleccioanda.
     */
    @Override
    public void onItemRemoved(Farm item)
    {
        if (deletionCount != null)
            deletionCount.setText(String.valueOf(adapter.getSelectedItems().size()));
    }

    /**
     * Cuando la multiselección de las granjas disponibles finaliza, se oculta el menú
     * auxiliar para borrar las granjas seleccionadas.
     */
    @Override
    public void onSelectionFinished()
    {
        // Muestra el nombre de la actividad.
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity != null ? activity.getSupportActionBar() : null;
        if (actionBar != null) actionBar.setDisplayShowTitleEnabled(true);

        // Cambia la vista del menú de acciones.
        if (menu == null || activity == null) return;
        menu.clear();
        activity.getMenuInflater().inflate(R.menu.action_manage_items, menu);
    }
}