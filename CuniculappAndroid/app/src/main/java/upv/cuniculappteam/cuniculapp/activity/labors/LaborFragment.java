package upv.cuniculappteam.cuniculapp.activity.labors;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result;
import upv.cuniculappteam.cuniculapp.activity.utils.ModelLifecycleFragment;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.view.labors.LaborAdapter;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.SelectableAdapter;
import upv.cuniculappteam.cuniculapp.viewmodel.LaborViewModel;

public class LaborFragment extends ModelLifecycleFragment<Labor>
{
    private static final int SEARCH_REQUEST_CODE = 184;

    private LaborViewModel tasks;

    private Result filters;

    private List<Labor> labors;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_labor, container, false);
    }

    /**
     * Inicializa las vistas del fragmento de las tareas.
     *
     * @param view La vista raíz del fragmento.
     * @param savedInstanceState El estado de la instancia de la aplicación.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        tasks = ViewModelProviders.of(this).get(LaborViewModel.class);
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Infla la vista del menú cabecera con los botones para ejecutar diversas acciones
     * sobre las tareas de la vista reciclable.
     *
     * @param menu El menú cabecera.
     * @param inflater El objeto mediador para inflar la vista de menú.
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.action_labor_items, menu);
    }

    @Override
    protected int getMenuLayout()
    {
        return R.menu.action_archived_items;
    }

    /**
     * Maneja el control de la vista de reciclaje para ejecurar una acción cuando
     * un elemento concreta ha sido seleccionado.
     *
     * @param item El elemento seleccionado.
     */
    @Override
    public void onItemClicked(Labor item) { }

    /**
     * Maneja el control de la vista de menú para añadir o quitar una objeto reciclable en
     * función del botón de menú seleccionado.
     *
     * @param item El botón de menú seleccionado.
     *
     * @return Si el control ha sido manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            // Si se quiere ordenar las actividades,
            case R.id.action_sort: return true;

            // Si se quiere buscar una tarea específica, cambia a la actividad correspondiente.
            case R.id.action_search: switchToSearch(); return true;

            // Si se quiere ver las tareas archivadas, cambia a la actividad correspondiente.
            case R.id.action_archived: switchToArchived(); return true;

            // En caso contrario, el control no ha sido manejado.
            default: return false;
        }
    }

    private void switchToSearch()
    {
        Intent intent = new Intent(getActivity(), SearchLaborsActivity.class);
        if (filters != null) intent.putExtra(SearchLaborsActivity.FILTER_PARAMS_PARCEL_KEY, filters);
        startActivityForResult(intent, SEARCH_REQUEST_CODE);
    }

    private void switchToArchived()
    {
        startActivity(new Intent(getActivity(), ArchivedLaborsActivity.class));
    }

    /**
     * Evento ejecutado al ocultar una actividad hija en la que se controla el resultado
     * que ha devuelto dicha actividad a la principal. En este caso, para los
     * parámetros de búsqueda de tareas.
     *
     * @param requestCode El código de petición del Intento lanzado por la Actividad.
     * @param resultCode El código de resultado devuelto por la Actividad hija ejecutada.
     * @param data Los datos devueltos por la Actividad hija ejecutada.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case SEARCH_REQUEST_CODE: applySearchFilters(resultCode, data); break;
            default: return;
        }
    }

    /**
     * Aplica los parámetros de búsqueda devueltos por la Actividad de parámetros de búsqueda
     * de tareasa la lista de tareas mostrada en esta Actividad.
     *
     * @param resultCode El código de resultado de la Actividad de parámetros de búsqueda.
     * @param data Los parámetros de búsqueda devueltos.
     */
    private void applySearchFilters(int resultCode, @Nullable Intent data)
    {
        if (data == null) return;

        this.filters = (Result) data.getSerializableExtra(SearchLaborsActivity.FILTER_PARAMS_PARCEL_KEY);
        if (filters == null) return;

        switch (resultCode)
        {
            case SearchLaborsActivity.RESULT_OK: super.updateItems(filters.filter(labors)); return;
            case SearchLaborsActivity.RESULT_CANCELED: return;
        }
    }

    /**
     * Obtiene una instancia concreta del adaptador principal del fragmento que muestra
     * los elementos seleccionables.
     *
     * @return Una instancia de un adaptador multiselección
     */
    @Override
    public SelectableAdapter<Labor> getAdapter()
    {
        return new LaborAdapter();
    }

    /**
     * Obtiene el identificador del recurso de la vista de reciclaje donde se muestran los
     * elementos del fragmento.
     *
     * @return El identificador del recurso.
     */
    @Override
    public int getAdapterId()
    {
        return R.id.recycler_labors;
    }

    /**
     * Obtiene una tarea programada de la que se obtienen los elementos a mostrar en el
     * adaptador de elementos principal del fragmento.
     *
     * @return Una tarea para obtener elementos.
     */
    @Override
    public Task<List<Labor>> getAdapterData()
    {
        return tasks.getLabors().continueWith(task -> this.labors = task.getResult());
    }

    /**
     * Obtiene una tarea programada en la que se eliminan los elementos seleccionados del
     * adaptador de elementos principal del fragmento.
     *
     * @param labors Los elementos a eliminar.
     * @return La tarea en la que se eliminan dichos elementos.
     */
    @Override
    public Task<List<Labor>> onDeleteSelected(Collection<Labor> labors)
    {
        return tasks.deleteLabors(labors);
    }

    /**
     * Obtiene una instancia de un diálogo donde se recogen los atributos de un nuevo elemento
     * a añadir en el adaptador de elementos principal del fragmento.
     *
     * @return Una instancia de un diálogo.
     */
    @Override
    public DialogFragment getAddDialog()
    {
        throw new IllegalAccessError();
    }
}