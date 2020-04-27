package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.labors.Insemination;
import upv.cuniculappteam.cuniculapp.model.labors.Labour;
import upv.cuniculappteam.cuniculapp.model.labors.Palpation;
import upv.cuniculappteam.cuniculapp.model.labors.Sale;
import upv.cuniculappteam.cuniculapp.view.utils.DateEditText;
import upv.cuniculappteam.cuniculapp.view.utils.LoadingView;
import upv.cuniculappteam.cuniculapp.viewmodel.EventViewModel;
import upv.cuniculappteam.cuniculapp.viewmodel.EventViewModel.Event;

public class EventsFragment extends Fragment implements NamedFragment
{
    private final Cycle cycle;

    private EventViewModel events;

    private View view;

    private Insemination insemination;

    private DateEditText inseminationDate;

    private EditText inseminationAmount;

    private Palpation palpation;

    private DateEditText palpationDate;

    private EditText palpationPregnant;

    private EditText palpationFailed;

    private EditText palpationFr;

    private Labour birth;

    private DateEditText birthDate;

    private EditText birthAmount;

    private EditText birthAlive;

    private EditText birthDead;

    private EditText birthAverage;

    private Sale sale;

    private DateEditText saleDate;

    private EditText saleAmount;

    private EditText salePrize;

    private EditText saleSize;

    private EditText saleFeed;

    private EditText saleIc;

    private EditText saleKgIa;

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

        // Se inicializa el comportamiento del botón de guardado de eventos.
        this.saveButton = view.findViewById(R.id.events_save_button);
        saveButton.setOnClickListener(this::saveEvents);

        updateView();
    }

    /**
     * Realiza las llamadas al VM de conejos para actualizar la vista de los datos a visualizar en
     * la actividad actual.
     */
    private void updateView(Object... params)
    {
        LoadingView.show(getActivity());
        events.getInseminations(cycle)
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(this::showInseminationData);

        LoadingView.show(getActivity());
        events.getPalpations(cycle)
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(this::showPalpationData);

        LoadingView.show(getActivity());
        events.getLabours(cycle)
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(this::showLabourData);

        LoadingView.show(getActivity());
        events.getSales(cycle)
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(this::showSalesData);
    }

    private void showInseminationData(List<Insemination> inseminations)
    {
        this.insemination = inseminations.get(0);

        inseminationDate = view.findViewById(R.id.insemination_date_text);
        inseminationDate.setDate(insemination.getDate());

        inseminationAmount = view.findViewById(R.id.insemination_amount_text);
        inseminationAmount.setText(String.valueOf(insemination.getInseminatedRabbits()));
    }

    private void showPalpationData(List<Palpation> palpations)
    {
        this.palpation = palpations.get(0);

        palpationDate = view.findViewById(R.id.palpation_date_text);
        palpationDate.setDate(palpation.getDate());

        palpationPregnant = view.findViewById(R.id.palpation_pregnant_text);
        palpationPregnant.setText(String.valueOf(palpation.getPregnantRabbits()));

        palpationFailed = view.findViewById(R.id.palpation_failed_text);
        palpationFailed.setText(""); // TODO: Inflar el dato correspondiente.

        palpationFr = view.findViewById(R.id.palpation_fr_text);
        palpationFr.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showLabourData(List<Labour> births)
    {
        this.birth = births.get(0);

        birthDate = view.findViewById(R.id.births_date_text);
        birthDate.setDate(birth.getDate());

        birthAmount = view.findViewById(R.id.births_amount_text);
        birthAmount.setText(String.valueOf(birth.getBirthsAmount()));

        birthAlive = view.findViewById(R.id.births_alive_text);
        birthAlive.setText(String.valueOf(birth.getBornAlive()));

        birthDead = view.findViewById(R.id.births_dead_text);
        birthDead.setText(String.valueOf(birth.getBornDead()));

        birthAverage = view.findViewById(R.id.births_average_text);
        birthAverage.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showSalesData(List<Sale> sales)
    {
        this.sale = sales.get(0);

        saleDate = view.findViewById(R.id.sales_date_text);
        saleDate.setDate(sale.getDate());

        saleAmount = view.findViewById(R.id.sales_amount_text);
        saleAmount.setText(String.valueOf(sale.getSold()));

        salePrize = view.findViewById(R.id.sales_prize_text);
        salePrize.setText(String.valueOf(sale.getSalePrize()));

        saleSize = view.findViewById(R.id.sales_size_text);
        saleSize.setText(String.valueOf(sale.getAverageWeight()));

        saleFeed = view.findViewById(R.id.sales_feed_text);
        saleFeed.setText(String.valueOf(sale.getFeedCost()));

        saleIc = view.findViewById(R.id.sales_ic_text);
        saleIc.setText(""); // TODO: Inflar el dato correspondiente.

        saleKgIa = view.findViewById(R.id.sales_kg_ia_text);
        saleKgIa.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void saveEvents(View view)
    {
        List<Task<?>> tasks = new ArrayList<>();

        tasks.add(events.updateInsemination(getAvailableInsemination()));
        tasks.add(events.updatePalpation(getAvailablePalpation()));
        tasks.add(events.updateLabour(getAvailableBirth()));
        tasks.add(events.updateSale(getAvailableSale()));

        LoadingView.show(getActivity());
        Tasks.whenAll(tasks)
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(this::updateView);
    }

    private Insemination getAvailableInsemination()
    {
        insemination.setDate(inseminationDate.getDate());
        insemination.setInseminatedRabbits(Integer.parseInt(inseminationAmount.getText().toString()));
        return insemination;
    }

    private Palpation getAvailablePalpation()
    {
        palpation.setDate(palpationDate.getDate());
        palpation.setPregnantRabbits(Integer.parseInt(palpationPregnant.getText().toString()));
        return palpation;
    }

    private Labour getAvailableBirth()
    {
        birth.setDate(birthDate.getDate());
        birth.setBirthsAmount(Integer.parseInt(birthAmount.getText().toString()));
        birth.setBornAlive(Integer.parseInt(birthAlive.getText().toString()));
        birth.setBornDead(Integer.parseInt(birthDead.getText().toString()));
        return birth;
    }

    private Sale getAvailableSale()
    {
        sale.setDate(saleDate.getDate());
        sale.setSold(Integer.valueOf(saleAmount.getText().toString()));
        sale.setAverageWeight(Integer.parseInt(saleSize.getText().toString()));
        sale.setFeedCost(Float.parseFloat(saleFeed.getText().toString()));
        sale.setSalePrize(Float.parseFloat(salePrize.getText().toString()));
        return sale;
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
