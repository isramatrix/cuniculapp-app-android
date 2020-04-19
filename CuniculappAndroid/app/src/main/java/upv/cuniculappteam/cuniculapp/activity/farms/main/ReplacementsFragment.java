package upv.cuniculappteam.cuniculapp.activity.farms.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.activity.farms.replacements.ReplacementActivity;
import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.utils.recycler.Adapter;
import upv.cuniculappteam.cuniculapp.view.farms.ReplacementsAdapter;
import upv.cuniculappteam.cuniculapp.viewmodel.ReplacementViewModel;

public class ReplacementsFragment extends NamedFragment implements
        Adapter.OnItemClickListener<Replacement>
{
    private final Farm farm;

    private ReplacementViewModel replacements;

    private Adapter<Replacement> adapter;

    ReplacementsFragment(Farm farm) { this.farm = farm; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_replacements, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        replacements = ViewModelProviders.of(this).get(ReplacementViewModel.class);

        // Se inicializa la vista de las reposiciones creadas.
        RecyclerView replacementRecylcer = view.findViewById(R.id.recycler_replacements);
        replacementRecylcer.setLayoutManager(new LinearLayoutManager(getContext()));
        replacementRecylcer.setAdapter(adapter = new ReplacementsAdapter());

        // Se muestran los datos de las granjas disponibles.
        replacements.getReplacements(farm).addOnSuccessListener(adapter::changeData);

        // Muestra la actividad de información de Reemplazo cuando se selecciona uno.
        adapter.setOnItemClickedListener(this);
    }

    @Override
    public void onItemClicked(Replacement replacement)
    {
        Intent intent = new Intent(getActivity(), ReplacementActivity.class);
        intent.putExtra(ReplacementActivity.REPLACEMENT_INTENT_KEY, (Parcelable) replacement);
        startActivity(intent);
    }

    @Override
    public int getFragmentName() { return R.string.main_replacement; }
}
