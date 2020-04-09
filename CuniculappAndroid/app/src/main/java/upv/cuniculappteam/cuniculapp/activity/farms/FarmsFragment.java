package upv.cuniculappteam.cuniculapp.activity.farms;

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
import upv.cuniculappteam.cuniculapp.activity.farms.main.FarmActivity;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.view.Adapter;
import upv.cuniculappteam.cuniculapp.viewmodel.FarmViewModel;

public class FarmsFragment extends Fragment implements Adapter.OnItemClickListener<Farm>
{
    private FarmViewModel farms;

    private FarmsAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_farms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        farms = ViewModelProviders.of(this).get(FarmViewModel.class);

        // Se inicializa la vista de las granjas disponibles.
        RecyclerView farmRecycler = view.findViewById(R.id.recycler_farm);
        farmRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        farmRecycler.setAdapter(adapter = new FarmsAdapter());
        adapter.setOnItemClickedListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);

        // Se muestran los datos de las granjas disponibles.
        if (!hidden) farms.getFarms().addOnSuccessListener(adapter::changeData);
    }

    @Override
    public void onItemClicked(Farm farm)
    {
        Intent intent = new Intent(getActivity(), FarmActivity.class);
        intent.putExtra(FarmActivity.FARM_INTENT_KEY, (Parcelable) farm);
        startActivity(intent);
    }
}