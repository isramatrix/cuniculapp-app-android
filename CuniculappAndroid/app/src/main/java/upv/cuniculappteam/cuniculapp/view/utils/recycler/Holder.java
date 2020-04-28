package upv.cuniculappteam.cuniculapp.view.utils.recycler;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;

class Holder<T extends Traceable> extends RecyclerView.ViewHolder
{
    Holder(@NonNull View itemView) {
        super(itemView);
    }
}
