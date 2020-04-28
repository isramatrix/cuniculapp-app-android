package upv.cuniculappteam.cuniculapp.viewmodel.utils;

import com.google.android.gms.tasks.Task;

import java.util.List;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;

public interface BasicViewModel<T extends Traceable>
{
    Task<List<T>> get();

    Task<List<T>> add(T item);

    Task<List<T>> remove();

    Task<List<T>> set(Object id, T item);
}
