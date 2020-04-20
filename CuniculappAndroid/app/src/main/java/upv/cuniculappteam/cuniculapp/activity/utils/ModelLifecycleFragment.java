package upv.cuniculappteam.cuniculapp.activity.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;

import java.util.Collection;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.view.utils.LoadingView;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.Adapter;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;

public abstract class ModelLifecycleFragment<T extends Traceable> extends Fragment implements
        SelectableAdapter.SelectionLifecycleObserver<T>,
        Adapter.OnItemClickListener<T>
{
    private static final String MODEL_LIFECYCLE_ADD_TAG = "ModelLifecycleDialog";

    private Menu menu;

    private SelectableAdapter<T> adapter;

    private TextView deletionCount;

    /**
     * Inicializa las vistas del fragmento de los ciclos de una granja.
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState El estado de la instancia de la aplicación.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);

        // Se inicializa la vista de los elementos creadas.
        RecyclerView replacementRecylcer = view.findViewById(getAdapterId());
        replacementRecylcer.setLayoutManager(new LinearLayoutManager(getContext()));
        replacementRecylcer.setAdapter(adapter = getAdapter());

        // Se muestran los datos de los elementos disponibles.
        getAdapterData().addOnSuccessListener(adapter::changeData);

        // Se inicializa la lógica del adaptador que muestra los elementos.
        adapter.setOnItemClickedListener(this);
        adapter.setSelectionLifecycleObserver(this);
    }

    /**
     * Actualiza los elementos que se muestran el el adaptador de elementos.
     *
     * @param items Los elementos a mostrar.
     */
    public void updateItems(List<T> items) { adapter.changeData(items); }

    /**
     * Reinicia el estado de multiselección del adaptador seleccionable cuando el
     * fragmento actual pasa a un estado inactivo.
     *
     * @param hidden El estado de visibilidad del fragmento.
     */
    @Override @CallSuper
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);

        // En caso de ocultarse, deshabilita la multiselección del adaptador.
        if (hidden) adapter.setSelectionMode(false);
    }

    /**
     * Infla la vista del menú cabecera con los botones para añadir y quitar objetos del reciclable.
     *
     * @param menu El menú cabecera.
     * @param inflater El objeto mediador para inflar la vista de menú.
     */
    @Override @CallSuper
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(this.menu = menu, inflater);
        inflater.inflate(R.menu.action_manage_items, menu);
    }

    /**
     * Maneja el control de la vista de menú para añadir o quitar una objeto reciclable en
     * función del botón de menú seleccionado.
     *
     * @param item El botón de menú seleccionado.
     * @return Si el control ha sido manejado correctamente.
     */
    @Override @CallSuper
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            // Si se quiere añadir una elemento, muestra un diálogo con los datos a introducir.
            case R.id.action_add: showAddDialog(); return true;

            // Si se quiere eliminar elementos, cambia el estado del adaptador a multiselección.
            case R.id.action_remove: adapter.setSelectionMode(true); return true;

            // Si se quiere confirmar el borrado de elementos, muestra un diálogo de confirmación.
            case R.id.action_count: showConfirmDeletionDialog(); return true;

            // Si se quiere salir del modo de borrado de elementos, cambia el estado de multiseleción.
            case R.id.action_clear: adapter.setSelectionMode(false); return true;

            // En caso contrario, el control no ha sido manejado.
            default: return false;
        }
    }

    /**
     * Crea y muestra un diálogo específico para introducir los datos de un nuevo elemento.
     */
    private void showAddDialog()
    {
        DialogFragment dialog = getAddDialog();

        if (getFragmentManager() != null)
            dialog.show(getFragmentManager(), MODEL_LIFECYCLE_ADD_TAG);
    }

    /**
     * Crea y muestra un diálogo específico para confirmar el borrado de elementos.
     */
    private void showConfirmDeletionDialog()
    {
        if (getActivity() == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_delete_header)
                .setMessage(R.string.dialog_delete_text)
                .setPositiveButton(R.string.dialog_delete_confirm,
                        (v, id) -> deleteSelected(adapter.getSelectedItems()))
                .setNegativeButton(R.string.dialog_delete_dismiss,
                        (v, id) -> adapter.setSelectionMode(false));

        builder.create().show();
    }

    /**
     * Crea y muestra un diálogo específico que informa que el borrado de elementos no ha
     * podido ser efectuado.
     */
    private void showFailedDeletionDialog(Object... params)
    {
        if (getActivity() == null) return;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_error_header)
                .setMessage(R.string.dialog_error_text)
                .setPositiveButton(R.string.dialog_error_confirm, null);

        builder.create().show();
    }

    protected void createItem(Task<List<T>> task)
    {
        LoadingView.show();

        task
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(adapter::changeData)
                .addOnFailureListener(this::showFailedDeletionDialog);
    }

    /**
     * Borra los elementos seleccionadas y actualiza los datos a mostrar en la vista del adaptador.
     *
     * @param items Las elementos a borrar.
     */
    private void deleteSelected(Collection<T> items)
    {
        LoadingView.show();

        onDeleteSelected(items)
            .addOnCompleteListener(LoadingView::hide)
            .addOnSuccessListener(adapter::changeData)
            .addOnFailureListener(this::showFailedDeletionDialog);

        adapter.setSelectionMode(false);
    }

    /**
     * Cuando la multiselección de elemnetos disponibles se inicia, se muestra el menú
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
     * Cuando un elemento de la multiselección se selecciona, actializa el
     * conteo de granjas seleccionadas del menú de borrado de granjas.
     *
     * @param item El elemento seleccioanda.
     */
    @Override
    public void onItemSelected(T item)
    {
        if (deletionCount != null)
            deletionCount.setText(String.valueOf(adapter.getSelectedItems().size()));
    }

    /**
     * Cuando un elemento de la multiselección se elimina, actializa el
     * conteo de elementos seleccionadas del menú de borrado de elementos.
     *
     * @param item El elemento seleccioanda.
     */
    @Override
    public void onItemRemoved(T item)
    {
        if (deletionCount != null)
            deletionCount.setText(String.valueOf(adapter.getSelectedItems().size()));
    }

    /**
     * Cuando la multiselección de las granjas disponibles finaliza, se oculta el menú
     * auxiliar para borrar los elementos seleccionadas.
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

    /**
     * Maneja el control de la vista de reciclaje para ejecurar una acción cuando
     * un elemento concreta ha sido seleccionado.
     *
     * @param item El elemento seleccionado.
     */
    @Override
    public abstract void onItemClicked(T item);

    /**
     * Obtiene una instancia concreta del adaptador principal del fragmento que muestra
     * los elementos seleccionables.
     *
     * @return Una instancia de un adaptador multiselección
     */
    public abstract SelectableAdapter<T> getAdapter();

    /**
     * Obtiene el identificador del recurso de la vista de reciclaje donde se muestran los
     * elementos del fragmento.
     *
     * @return El identificador del recurso.
     */
    public abstract @IdRes int getAdapterId();

    /**
     * Obtiene una tarea programada de la que se obtienen los elementos a mostrar en el
     * adaptador de elementos principal del fragmento.
     *
     * @return Una tarea para obtener elementos.
     */
    public abstract Task<List<T>> getAdapterData();

    /**
     * Obtiene una tarea programada en la que se eliminan los elementos seleccionados del
     * adaptador de elementos principal del fragmento.
     *
     * @param items Los elementos a eliminar.
     * @return La tarea en la que se eliminan dichos elementos.
     */
    public abstract Task<List<T>> onDeleteSelected(Collection<T> items);

    /**
     * Obtiene una instancia de un diálogo donde se recogen los atributos de un nuevo elemento
     * a añadir en el adaptador de elementos principal del fragmento.
     *
     * @return Una instancia de un diálogo.
     */
    public abstract DialogFragment getAddDialog();
}
