package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.viewmodel.EventViewModel;
import upv.cuniculappteam.cuniculapp.viewmodel.EventViewModel.Event;

public class EventsFragment extends Fragment implements NamedFragment
{
    private final Cycle cycle;

    private EventViewModel events;

    private View view;

    private Button saveButton;

    EventsFragment(Cycle cycle) { this.cycle = cycle; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(this.view = view, savedInstanceState);

        this.events = ViewModelProviders.of(this).get(EventViewModel.class);

        // Se muestran los datos de los eventos asociados al ciclo.
        events.getEvent(cycle).addOnSuccessListener(this::showData);

        // Se inicializa el comportamiento del botón de guardado de eventos.
        this.saveButton = view.findViewById(R.id.events_save_button);
        saveButton.setOnClickListener(this::saveEvents);
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
        EditText date = view.findViewById(R.id.insemination_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        EditText amount = view.findViewById(R.id.insemination_amount_text);
        amount.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showPalpationData(Event event)
    {
        EditText date = view.findViewById(R.id.palpation_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        EditText pregnant = view.findViewById(R.id.palpation_pregnant_text);
        pregnant.setText(""); // TODO: Inflar el dato correspondiente.

        EditText failed = view.findViewById(R.id.palpation_failed_text);
        failed.setText(""); // TODO: Inflar el dato correspondiente.

        EditText fr = view.findViewById(R.id.palpation_fr_text);
        fr.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showBirthsData(Event event)
    {
        EditText date = view.findViewById(R.id.births_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        EditText amount = view.findViewById(R.id.births_amount_text);
        amount.setText(""); // TODO: Inflar el dato correspondiente.

        EditText alive = view.findViewById(R.id.births_alive_text);
        alive.setText(""); // TODO: Inflar el dato correspondiente.

        EditText dead = view.findViewById(R.id.births_dead_text);
        dead.setText(""); // TODO: Inflar el dato correspondiente.

        EditText average = view.findViewById(R.id.births_average_text);
        average.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showSalesData(Event event)
    {
        EditText date = view.findViewById(R.id.sales_date_text);
        date.setText(""); // TODO: Inflar el dato correspondiente.

        EditText amount = view.findViewById(R.id.sales_amount_text);
        amount.setText(""); // TODO: Inflar el dato correspondiente.

        EditText prize = view.findViewById(R.id.sales_prize_text);
        prize.setText(""); // TODO: Inflar el dato correspondiente.

        EditText size = view.findViewById(R.id.sales_size_text);
        size.setText(""); // TODO: Inflar el dato correspondiente.

        EditText feed = view.findViewById(R.id.sales_feed_text);
        feed.setText(""); // TODO: Inflar el dato correspondiente.

        EditText ic = view.findViewById(R.id.sales_ic_text);
        ic.setText(""); // TODO: Inflar el dato correspondiente.

        EditText kgIa = view.findViewById(R.id.sales_kg_ia_text);
        kgIa.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void saveEvents(View view)
    {
        // TODO: Guardar cambios de eventos
    }

    /**
     * Quita el foco de edición a la vista en la que está y cierra el teclado del modo
     * de edición cuando el Fragmento de los Eventos pasa a modo oculto.
     *
     * @param hidden Si dicho fragmento cambia a modo oculto o modo visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        Context context = getContext();
        saveButton.requestFocus();

        if (context == null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public int getFragmentName() { return R.string.main_events; }
}
