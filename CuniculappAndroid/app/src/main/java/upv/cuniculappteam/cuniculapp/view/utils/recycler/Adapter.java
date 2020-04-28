package upv.cuniculappteam.cuniculapp.view.utils.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;

public abstract class Adapter<T extends Traceable> extends RecyclerView.Adapter<Holder<T>> implements
        View.OnClickListener
{
    private Map<View, T> binds = new HashMap<>();

    private List<T> items;

    private OnItemClickListener<T> listener;

    public Adapter() { this.items = new ArrayList<>(); }

    public Adapter(List<T> items) { super(); this.items = items; }

    @NonNull
    @Override
    public Holder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        return new Holder<>(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder<T> holder, int position)
    {
        binds.put(holder.itemView, items.get(position));
        onBindView(holder.itemView, items.get(position));
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public final int getItemCount() {
        return items.size();
    }

    @Override @CallSuper
    public void onClick(View view)
    {
        if (listener != null) listener.onItemClicked(binds.get(view));
    }

    public void changeData(List<T> items)
    {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setOnItemClickedListener(OnItemClickListener<T> listener)
    {
        this.listener = listener;
    }

    public abstract @LayoutRes int getLayout();

    public abstract void onBindView(View view, T item);

    public interface OnItemClickListener<T> { void onItemClicked(T item); }
}
