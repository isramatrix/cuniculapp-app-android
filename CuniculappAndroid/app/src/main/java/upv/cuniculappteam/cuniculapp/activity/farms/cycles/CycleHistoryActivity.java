package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.animals.Kitten;
import upv.cuniculappteam.cuniculapp.model.animals.KittenChange;
import upv.cuniculappteam.cuniculapp.model.animals.Mother;
import upv.cuniculappteam.cuniculapp.model.animals.MotherChange;
import upv.cuniculappteam.cuniculapp.view.utils.LoadingView;
import upv.cuniculappteam.cuniculapp.viewmodel.RabbitViewModel;

import static android.view.Gravity.CENTER;
import static android.widget.TableRow.LayoutParams.MATCH_PARENT;
import static android.widget.TableRow.LayoutParams.WRAP_CONTENT;

public class CycleHistoryActivity extends AppCompatActivity
{
    public static final String MOTHERS_INTENT_KEY = "mothers_key";

    public static final String KITTENS_INTENT_KEY = "kittens_key";

    private Mother mother;

    private Kitten kitten;

    private RabbitViewModel rabbits;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit_history);

        // Indica el título de la actividad de la conejos que se está gestionando.
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(R.string.rabbits_history);

        this.mother = getIntent().getParcelableExtra(MOTHERS_INTENT_KEY);
        if (this.mother == null) { finish(); return; }

        this.kitten = getIntent().getParcelableExtra(KITTENS_INTENT_KEY);
        if (this.kitten == null) { finish(); return; }

        this.rabbits = ViewModelProviders.of(this).get(RabbitViewModel.class);

        LoadingView.show(this);
        rabbits.getMotherChanges(mother)
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(this::showMothers);

        LoadingView.show(this);
        rabbits.getKittenChanges(kitten)
                .addOnCompleteListener(LoadingView::hide)
                .addOnSuccessListener(this::showKittens);
    }

    /**
     * Inicializa e infla los datos de vista sobre las madres pertenecientes al ciclo
     * que se está gestionando.
     *
     * @param mothers La lista de madres del ciclo.
     */
    private void showMothers(List<MotherChange> mothers)
    {
        TextView initialText = findViewById(R.id.rabbit_history_mother_initial_text);
        initialText.setText(""); // TODO: Inflar los datos de esta vista.

        TextView aliveText = findViewById(R.id.rabbit_history_mother_alive_text);
        aliveText.setText(String.valueOf(mother.getAlive()));

        TableLayout table = findViewById(R.id.rabbit_history_mother_table);
        inflateMotherRows(table, mothers);
    }

    /**
     * Infla los datos del histórico de cambios de conejos madres que ha habido en la Granja
     * desde su creación.
     *
     * @param table La vista de la tabla en la que inflar el contenido.
     * @param mothers La lista de cambios de conejos madre que ha habido.
     */
    private void inflateMotherRows(TableLayout table, List<MotherChange> mothers)
    {
        for (MotherChange mother : mothers)
        {
            TableRow tableRow = new TableRow(this);

            TextView changesCell = new TextView(this);
            changesCell.setText(String.valueOf(mother.getAmount()));
            changesCell.setGravity(CENTER);
            changesCell.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT, 1f));
            tableRow.addView(changesCell);

            TextView reasonText = new TextView(this);
            reasonText.setText(mother.getReason());
            reasonText.setGravity(CENTER);
            reasonText.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT, 4f));
            tableRow.addView(reasonText);

            TextView dateText = new TextView(this);
            dateText.setText(sdf.format(mother.getDate()));
            dateText.setGravity(CENTER);
            dateText.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT, 1f));
            tableRow.addView(dateText);

            table.addView(tableRow);
        }
    }

    /**
     * Inicializa e infla los datos de vista sobre los gazapos pertenecientes al ciclo
     * que se está gestionando.
     *
     * @param kittens La lista de gazapos del ciclo.
     */
    private void showKittens(List<KittenChange> kittens)
    {
        TextView maternalInitialText = findViewById(R.id.rabbit_history_kitten_maternal_initial_text);
        maternalInitialText.setText(""); // TODO: Inflar los datos de esta vista.

        TextView meetInitialText = findViewById(R.id.rabbit_history_kitten_meet_initial_text);
        meetInitialText.setText(""); // TODO: Inflar los datos de esta vista.

        TextView maternalAliveText = findViewById(R.id.rabbit_history_kitten_maternal_alive_text);
        maternalAliveText.setText(String.valueOf(kitten.getMaternal()));

        TextView meetAliveText = findViewById(R.id.rabbit_history_kitten_meet_alive_text);
        meetAliveText.setText(String.valueOf(kitten.getMeat()));

        TableLayout table = findViewById(R.id.rabbit_history_kitten_table);
        inflateKittenRows(table, kittens);
    }

    /**
     * Infla los datos del histórico de cambios de conejos gazapos que ha habido en la Granja
     * desde su creación.
     *
     * @param table La vista de la tabla en la que inflar el contenido.
     * @param kittens La lista de cambios de conejos gazapos que ha habido.
     */
    private void inflateKittenRows(TableLayout table, List<KittenChange> kittens)
    {
        for (KittenChange kitten : kittens)
        {
            TableRow tableRow = new TableRow(this);

            TextView changesCell = new TextView(this);
            changesCell.setText(String.valueOf(kitten.getMaternalAmount() + kitten.getMeatAmount()));
            changesCell.setGravity(CENTER);
            changesCell.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT, 1f));
            tableRow.addView(changesCell);

            TextView reasonText = new TextView(this);
            reasonText.setText(kitten.getReason());
            reasonText.setGravity(CENTER);
            reasonText.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT, 4f));
            tableRow.addView(reasonText);

            TextView dateText = new TextView(this);
            dateText.setText(sdf.format(kitten.getDate()));
            dateText.setGravity(CENTER);
            dateText.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT, 1f));
            tableRow.addView(dateText);

            table.addView(tableRow);
        }
    }
}