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
import java.util.concurrent.atomic.AtomicBoolean;

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

    private AtomicBoolean finished = new AtomicBoolean(false);

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
        if (inseminations == null || inseminations.size() == 0) { finishActivity(); return; }
        this.insemination = inseminations.get(0);

        inseminationDate = view.findViewById(R.id.insemination_date_text);
        if (insemination.getDate() != null) inseminationDate.setDate(insemination.getDate());

        inseminationAmount = view.findViewById(R.id.insemination_amount_text);
        if (insemination.getInseminatedRabbits() != null) inseminationAmount.setText(String.valueOf(insemination.getInseminatedRabbits()));
    }

    private void showPalpationData(List<Palpation> palpations)
    {
        if (palpations == null || palpations.size() == 0) { finishActivity(); return; }
        this.palpation = palpations.get(0);

        palpationDate = view.findViewById(R.id.palpation_date_text);
        if (palpation.getDate() != null) palpationDate.setDate(palpation.getDate());

        palpationPregnant = view.findViewById(R.id.palpation_pregnant_text);
        if (palpation.getPregnantRabbits() != null) palpationPregnant.setText(String.valueOf(palpation.getPregnantRabbits()));

        palpationFailed = view.findViewById(R.id.palpation_failed_text);
        palpationFailed.setText(""); // TODO: Inflar el dato correspondiente.

        palpationFr = view.findViewById(R.id.palpation_fr_text);
        palpationFr.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showLabourData(List<Labour> births)
    {
        if (births == null || births.size() == 0) { finishActivity(); return; }
        this.birth = births.get(0);

        birthDate = view.findViewById(R.id.births_date_text);
        if (birth.getDate() != null) birthDate.setDate(birth.getDate());

        birthAmount = view.findViewById(R.id.births_amount_text);
        if (birth.getBirthsAmount() != null) birthAmount.setText(String.valueOf(birth.getBirthsAmount()));

        birthAlive = view.findViewById(R.id.births_alive_text);
        if (birth.getBornAlive() != null) birthAlive.setText(String.valueOf(birth.getBornAlive()));

        birthDead = view.findViewById(R.id.births_dead_text);
        if (birth.getBornAlive() != null) birthDead.setText(String.valueOf(birth.getBornDead()));

        birthAverage = view.findViewById(R.id.births_average_text);
        birthAverage.setText(""); // TODO: Inflar el dato correspondiente.
    }

    private void showSalesData(List<Sale> sales)
    {
        if (sales == null || sales.size() == 0) { finishActivity(); return; }
        this.sale = sales.get(0);

        saleDate = view.findViewById(R.id.sales_date_text);
        if (sale.getDate() != null) saleDate.setDate(sale.getDate());

        saleAmount = view.findViewById(R.id.sales_amount_text);
        if (sale.getSold() != null) saleAmount.setText(String.valueOf(sale.getSold()));

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
        if (inseminationDate.getText() != null && inseminationDate.getText().length() > 0)
            insemination.setDate(inseminationDate.getDate());

        if (inseminationAmount.getText() != null && inseminationAmount.getText().length() > 0)
            insemination.setInseminatedRabbits(Integer.parseInt(inseminationAmount.getText().toString()));

        return insemination;
    }

    private Palpation getAvailablePalpation()
    {
        if (palpationDate.getText() != null && palpationDate.getText().length() > 0)
            palpation.setDate(palpationDate.getDate());

        if (palpationPregnant.getText() != null && palpationPregnant.getText().length() > 0)
            palpation.setPregnantRabbits(Integer.parseInt(palpationPregnant.getText().toString()));

        return palpation;
    }

    private Labour getAvailableBirth()
    {
        if (birthDate.getText() != null && birthDate.getText().length() > 0)
            birth.setDate(birthDate.getDate());

        if (birthAmount.getText() != null && birthAmount.getText().length() > 0)
            birth.setBirthsAmount(Integer.parseInt(birthAmount.getText().toString()));

        if (birthAlive.getText() != null && birthAlive.getText().length() > 0)
            birth.setBornAlive(Integer.parseInt(birthAlive.getText().toString()));

        if (birthDead.getText() != null && birthDead.getText().length() > 0)
            birth.setBornDead(Integer.parseInt(birthDead.getText().toString()));

        return birth;
    }

    private Sale getAvailableSale()
    {
        if (saleDate.getText() != null && saleDate.getText().length() > 0)
            sale.setDate(saleDate.getDate());

        if (saleAmount.getText() != null && saleAmount.getText().length() > 0)
            sale.setSold(Integer.valueOf(saleAmount.getText().toString()));

        if (saleSize.getText() != null && saleSize.getText().length() > 0)
            sale.setAverageWeight(Float.parseFloat(saleSize.getText().toString()));

        if (saleFeed.getText() != null && saleFeed.getText().length() > 0)
            sale.setFeedCost(Float.parseFloat(saleFeed.getText().toString()));

        if (salePrize.getText() != null && salePrize.getText().length() > 0)
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

    private void finishActivity()
    {
        if (!finished.get() && getActivity() != null) {
            finished.set(true); getActivity().finish();
        }
    }

    @Override
    public int getFragmentName() { return R.string.main_events; }
}
