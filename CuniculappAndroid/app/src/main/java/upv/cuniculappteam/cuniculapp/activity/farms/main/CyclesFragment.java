package upv.cuniculappteam.cuniculapp.activity.farms.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.farms.cycles.CycleActivity;
import upv.cuniculappteam.cuniculapp.activity.farms.replacements.ReplacementActivity;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.Adapter;
import upv.cuniculappteam.cuniculapp.viewmodel.CycleViewModel;

public class CyclesFragment extends FarmFragment implements
        Adapter.OnItemClickListener<Cycle>
{
    private CycleViewModel cycles;

    private final Farm farm;

    private Adapter<Cycle> adapter;

    CyclesFragment(Farm farm) { this.farm = farm; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cycles, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cycles = ViewModelProviders.of(this).get(CycleViewModel.class);

        // Se inicializa la vista de los ciclos creadas.
        RecyclerView replacementRecylcer = view.findViewById(R.id.recycler_cycles);
        replacementRecylcer.setLayoutManager(new LinearLayoutManager(getContext()));
        replacementRecylcer.setAdapter(adapter = new CyclesAdapter());
        adapter.setOnItemClickedListener(this);
    }

    @Override
    public void onItemClicked(Cycle cycle)
    {
        Intent intent = new Intent(getActivity(), CycleActivity.class);
        intent.putExtra(ReplacementActivity.REPLACEMENT_INTENT_KEY, (Parcelable) cycle);
        startActivity(intent);
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);

        // Se muestran los datos de las granjas disponibles.
        if (!hidden) cycles.getCycles(farm).addOnSuccessListener(adapter::changeData);
    }

    @Override
    int getFragmentName() { return R.string.main_cycles; }
}
