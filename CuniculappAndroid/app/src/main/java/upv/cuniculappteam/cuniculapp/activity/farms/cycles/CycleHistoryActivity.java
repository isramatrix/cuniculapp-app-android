package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.animals.Kitten;
import upv.cuniculappteam.cuniculapp.model.animals.Mother;

public class CycleHistoryActivity extends AppCompatActivity
{
    public static final String MOTHERS_INTENT_KEY = "mothers_key";

    public static final String KITTENS_INTENT_KEY = "kittens_key";

    private List<Mother> mothers;

    private List<Kitten> kittens;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit_history);

        this.mothers = getIntent().getParcelableArrayListExtra(MOTHERS_INTENT_KEY);
        if (this.mothers == null) { finish(); return; }

        this.kittens = getIntent().getParcelableArrayListExtra(KITTENS_INTENT_KEY);
        if (this.kittens == null) { finish(); return; }

        showMothers(mothers);
        showKittens(kittens);
    }

    /**
     * Inicializa e infla los datos de vista sobre las madres pertenecientes al ciclo
     * que se está gestionando.
     *
     * @param mothers La lista de madres del ciclo.
     */
    private void showMothers(List<Mother> mothers)
    {
        TextView initialText = findViewById(R.id.rabbit_history_mother_initial_text);
        initialText.setText(""); // TODO: Inflar los datos de esta vista.

        int alive = 0; for (Mother mother : mothers) alive += mother.getAlive();
        TextView aliveText = findViewById(R.id.rabbit_history_mother_alive_text);
        aliveText.setText(String.valueOf(alive));

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
    private void inflateMotherRows(TableLayout table, List<Mother> mothers)
    {
        for (Mother mother : mothers)
        {
            TableRow tableRow = new TableRow(this);

            TextView changesCell = new TextView(this);
            changesCell.setText(String.valueOf(mother.getAlive()));
            tableRow.addView(changesCell);

            TextView reasonText = new TextView(this);
            reasonText.setText(mother.getJails().toString());
            tableRow.addView(reasonText);

            TextView dateText = new TextView(this);
            dateText.setText(""); // TODO: Inflar los datos de esta vista.
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
    private void showKittens(List<Kitten> kittens)
    {
        TextView maternalInitialText = findViewById(R.id.rabbit_history_kitten_maternal_initial_text);
        maternalInitialText.setText(""); // TODO: Inflar los datos de esta vista.

        TextView meetInitialText = findViewById(R.id.rabbit_history_kitten_meet_initial_text);
        meetInitialText.setText(""); // TODO: Inflar los datos de esta vista.

        TextView maternalAliveText = findViewById(R.id.rabbit_history_kitten_maternal_alive_text);
        int maternalAlive = 0; for (Kitten kitten : kittens) maternalAlive += kitten.getMaternalFatten() + kitten.getMaternalNest();
        maternalAliveText.setText(String.valueOf(maternalAlive));

        TextView meetAliveText = findViewById(R.id.rabbit_history_kitten_meet_alive_text);
        int meetAlive = 0; for (Kitten kitten : kittens) meetAlive += kitten.getMeatFatten() + kitten.getMeatNest();
        meetAliveText.setText(String.valueOf(meetAlive));

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
    private void inflateKittenRows(TableLayout table, List<Kitten> kittens)
    {
        for (Kitten kitten : kittens)
        {
            TableRow tableRow = new TableRow(this);

            TextView changesCell = new TextView(this);
            changesCell.setText(String.valueOf("")); // TODO: Inflar los datos de esta vista.
            tableRow.addView(changesCell);

            TextView reasonText = new TextView(this);
            reasonText.setText(""); // TODO: Inflar los datos de esta vista.
            tableRow.addView(reasonText);

            TextView dateText = new TextView(this);
            dateText.setText(""); // TODO: Inflar los datos de esta vista.
            tableRow.addView(dateText);

            table.addView(tableRow);
        }
    }
}