package upv.cuniculappteam.cuniculapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;

public abstract class Adapter<T extends Traceable> extends RecyclerView.Adapter<Holder<T>>
{
    private List<T> items;

    private OnItemClickListener<T> listener;

    public Adapter(List<T> items) { super(); this.items = items; }

    @NonNull
    @Override
    public Holder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(getLayout(), parent, false);
        return new Holder<>(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder<T> holder, int position) {
        onBindView(holder.itemView, items.get(position));
        holder.itemView.setOnClickListener((view) -> {
            if (listener != null) listener.onItemClicked(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
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
