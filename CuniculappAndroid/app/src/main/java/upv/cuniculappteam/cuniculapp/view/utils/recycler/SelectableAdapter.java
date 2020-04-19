package upv.cuniculappteam.cuniculapp.view.utils.recycler;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.CallSuper;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;

public abstract class SelectableAdapter<T extends Traceable> extends Adapter<T> implements
        View.OnLongClickListener
{
    private SelectionLifecycleObserver<T> observer;

    private Map<View, T> availableItems = new HashMap<>();

    private Map<View, T> selectedItems = new HashMap<>();

    private boolean isClickableMode = false;

    public SelectableAdapter() { super(); }

    public SelectableAdapter(List<T> items) { super(items); }

    @Override @CallSuper
    public void onBindView(View view, T item)
    {
        view.setOnLongClickListener(this);
        availableItems.put(view, item);
    }

    @Override @CallSuper
    public void onClick(View view)
    {
        if (isClickableMode) select(view);
        else super.onClick(view);
    }

    @Override @CallSuper
    public boolean onLongClick(View view)
    {
        setSelectionMode(true);
        select(view);
        return true;
    }

    private void select(View view)
    {
        // Obtiene el objeto asociado a la vista seleccionada.
        T item = availableItems.get(view);

        // Si estaba previamente selccionado, cancela la selección
        if (selectedItems.containsKey(view)) {
            selectedItems.remove(view);
            view.setBackgroundColor(Color.TRANSPARENT);
            if (observer != null) observer.onItemRemoved(item);

        // Si no estaba seleccionado, añade el objeto a la selección.
        } else {
            selectedItems.put(view, item);
            view.setBackgroundColor(Color.CYAN);
            if (observer != null) observer.onItemSelected(item);
        }

        // Si no hay ningún objeto seleccionado, cancela el modo selección.
        if (selectedItems.size() == 0) setSelectionMode(false);
    }

    private void clearSelection()
    {
        Map<View, T> items = new HashMap<>(selectedItems);
        for (View view : items.keySet()) select(view);
    }

    public void setSelectionMode(boolean mode)
    {
        if (!mode) clearSelection();

        this.isClickableMode = mode;
        this.selectedItems = new HashMap<>();

        if (observer != null)
            if (mode) observer.onSelectionStart(); else observer.onSelectionFinished();
    }

    public Collection<T> getSelectedItems()
    {
        return Lists.newArrayList(selectedItems.values());
    }

    public void setSelectionLifecycleObserver(SelectionLifecycleObserver<T> observer)
    {
        this.observer = observer;
    }

    public interface SelectionLifecycleObserver<T extends Traceable>
    {
        void onSelectionStart();
        void onItemSelected(T item);
        void onItemRemoved(T item);
        void onSelectionFinished();
    }
}
