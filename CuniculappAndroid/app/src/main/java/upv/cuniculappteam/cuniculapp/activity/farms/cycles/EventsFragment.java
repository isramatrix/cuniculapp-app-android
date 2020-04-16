package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.viewmodel.EventViewModel;
import upv.cuniculappteam.cuniculapp.viewmodel.EventViewModel.Event;

public class EventsFragment extends NamedFragment
{
    private final Cycle cycle;

    private EventViewModel events;

    private View view;

    EventsFragment(Cycle cycle) { this.cycle = cycle; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.events = ViewModelProviders.of(this).get(EventViewModel.class);
        this.view = view;

        // Se muestran los datos de los eventos asociados al ciclo.
        events.getEvent(cycle).addOnSuccessListener(this::showBirthsData);
    }

    private void showData(Event event)
    {
        showInseminationData(event);
        showPalpationData(event);
        showBirthsData(event);
        showSalesData(event);
    }

    private void showInseminationData(Event event)
    {
        TextView date = view.findViewById(R.id.insemination_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        TextView amount = view.findViewById(R.id.insemination_amount_text);
        amount.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showPalpationData(Event event)
    {
        TextView date = view.findViewById(R.id.palpation_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        TextView pregnant = view.findViewById(R.id.palpation_pregnant_text);
        pregnant.setText(""); // TODO: Inflar el dato correspondiente.

        TextView failed = view.findViewById(R.id.palpation_failed_text);
        failed.setText(""); // TODO: Inflar el dato correspondiente.

        TextView fr = view.findViewById(R.id.palpation_fr_text);
        fr.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showBirthsData(Event event)
    {
        TextView date = view.findViewById(R.id.births_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        TextView amount = view.findViewById(R.id.births_amount_text);
        amount.setText(""); // TODO: Inflar el dato correspondiente.

        TextView alive = view.findViewById(R.id.births_alive_text);
        alive.setText(""); // TODO: Inflar el dato correspondiente.

        TextView dead = view.findViewById(R.id.births_dead_text);
        dead.setText(""); // TODO: Inflar el dato correspondiente.

        TextView average = view.findViewById(R.id.births_average_text);
        average.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showSalesData(Event event)
    {
        TextView date = view.findViewById(R.id.sales_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        TextView amount = view.findViewById(R.id.sales_amount_text);
        amount.setText(""); // TODO: Inflar el dato correspondiente.

        TextView prize = view.findViewById(R.id.sales_prize_text);
        prize.setText(""); // TODO: Inflar el dato correspondiente.

        TextView size = view.findViewById(R.id.sales_size_text);
        size.setText(""); // TODO: Inflar el dato correspondiente.

        TextView feed = view.findViewById(R.id.sales_feed_text);
        feed.setText(""); // TODO: Inflar el dato correspondiente.

        TextView ic = view.findViewById(R.id.sales_ic_text);
        ic.setText(""); // TODO: Inflar el dato correspondiente.

        TextView kgIa = view.findViewById(R.id.sales_kg_ia_text);
        kgIa.setText(""); // TODO: Inflar el dato correspondiente.
    }

    @Override
    public int getFragmentName() { return R.string.main_events; }
}
