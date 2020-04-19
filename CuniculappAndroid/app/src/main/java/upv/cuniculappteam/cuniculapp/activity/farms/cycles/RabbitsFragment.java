package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.animals.Kitten;
import upv.cuniculappteam.cuniculapp.model.animals.Mother;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog;
import upv.cuniculappteam.cuniculapp.viewmodel.RabbitViewModel;

import static upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult.*;

public class RabbitsFragment extends NamedFragment
{
    private final Cycle cycle;

    private RabbitViewModel rabbits;

    private View view;

    RabbitsFragment(Cycle cycle) { this.cycle = cycle; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rabbits, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(this.view = view, savedInstanceState);

        this.rabbits = ViewModelProviders.of(this).get(RabbitViewModel.class);

        // Se muestran los datos de los conejos asociados al ciclo.
        rabbits.getMothers(cycle).addOnSuccessListener(this::showMothers);
        rabbits.getKittens(cycle).addOnSuccessListener(this::showKittens);

        // Se inicializan los botones que lanzan las ventanas emergentes.
        view.findViewById(R.id.rabbits_mother_add_button).setOnClickListener(this::openAddMother);
        view.findViewById(R.id.rabbits_mother_remove_button).setOnClickListener(this::openRemoveMother);
        view.findViewById(R.id.rabbits_kitten_add_button).setOnClickListener(this::openAddKitten);
        view.findViewById(R.id.rabbits_kitten_remove_button).setOnClickListener(this::openRemoveKitten);
    }

    private void showMothers(Mother mother)
    {
        TextView mothersAlive = view.findViewById(R.id.rabbits_mothers_alive_text);
        mothersAlive.setText(""); // TODO: Inflar los datos de esta vista.

        TableLayout jailsTable = view.findViewById(R.id.rabbits_mother_jails_table);
        // TODO: Inflar los datos de esta vista.
    }

    private void showKittens(Kitten kitten)
    {
        TextView kittensAlive = view.findViewById(R.id.rabbits_kittens_alive_text);
        kittensAlive.setText(""); // TODO: Inflar los datos de esta vista.

        TableLayout jailsTable = view.findViewById(R.id.rabbits_kitten_jails_table);
        // TODO: Inflar los datos de esta vista.
    }

    private static final String ADD_MOTHER_TAG = "AddMother";
    private void openAddMother(View view)
    {
        MotherDialog dialog = new MotherDialog(Header.ADD, rabbits::addMother);

        if (getFragmentManager() != null)
            dialog.show(getFragmentManager(), ADD_MOTHER_TAG);
    }

    private static final String REMOVE_MOTHER_TAG = "RemoveMother";
    private void openRemoveMother(View view)
    {
        MotherDialog dialog = new MotherDialog(Header.REMOVE, rabbits::removeMother);

        if (getFragmentManager() != null)
            dialog.show(getFragmentManager(), REMOVE_MOTHER_TAG);
    }

    private static final String ADD_KITTEN_TAG = "AddKitten";
    private void openAddKitten(View view)
    {
        KittenDialog dialog = new KittenDialog(Header.ADD, rabbits::addKitten);

        if (getFragmentManager() != null)
            dialog.show(getFragmentManager(), ADD_KITTEN_TAG);
    }

    private static final String REMOVE_KITTEN_TAG = "RemoveKitten";
    private void openRemoveKitten(View view)
    {
        KittenDialog dialog = new KittenDialog(Header.REMOVE, rabbits::removeKitten);

        if (getFragmentManager() != null)
            dialog.show(getFragmentManager(), REMOVE_KITTEN_TAG);
    }

    @Override
    public int getFragmentName() {
        return R.string.main_rabbits;
    }
}