package upv.cuniculappteam.cuniculapp.activity.farms.replacements;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Replacement;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog.Result;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult.Header;
import upv.cuniculappteam.cuniculapp.viewmodel.ReplacementViewModel;

public class ReplacementActivity extends AppCompatActivity
{
    public static final String REPLACEMENT_INTENT_KEY = "replacement";

    private static final String REPLACEMENT_DIALOG_ADD_TAG = "AddReplacementDialog";

    private static final String REPLACEMENT_DIALOG_REMOVE_TAG = "RemoveReplacementDialog";

    private ReplacementViewModel replacements;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement);

        // Obtiene una instancia del VM de ___.
        // this.replacements = ViewModelProviders.of(this).get(ReplacementViewModel.class);

        Replacement replacement = getIntent().getParcelableExtra(REPLACEMENT_INTENT_KEY);
        if (replacement == null) { finish(); return; }

        TextView daysText = findViewById(R.id.replacement_days_text);
        daysText.setText(replacement.getDays().toString());

        TextView rabbitsText = findViewById(R.id.replacement_rabbits_text);
        rabbitsText.setText(replacement.getRabbitsAmount().toString());

        EditText jailsEditText = findViewById(R.id.replacement_jails_etext);
        // TODO: Inflar el contenido de esta vista.

        TableLayout timelineTable = findViewById(R.id.replacement_timeline_table);
        // TODO: Inlfar el contenido de esta vista.

        findViewById(R.id.replacement_add_jail_button).setOnClickListener(this::showAddReplacementDialog);
        findViewById(R.id.replacement_remove_jail_button).setOnClickListener(this::showRemoveReplacementDialog);
    }

    /**
     * Mustra el diálogo para crear una reposición de la granja en la que se añaden
     * conejos a la instalación.
     *
     * @param view La vista que ha generado el evento.
     */
    private void showAddReplacementDialog(View view)
    {
        DialogFragment dialog = new MotherDialog(Header.ADD, this::makeAddReplacement);
        dialog.show(getSupportFragmentManager(), REPLACEMENT_DIALOG_ADD_TAG);
    }

    /**
     * Mustra el diálogo para crear una reposición de la granja en la que se eliminan
     * conejos de la instalación.
     *
     * @param view La vista que ha generado el evento.
     */
    private void showRemoveReplacementDialog(View view)
    {
        DialogFragment dialog = new MotherDialog(Header.REMOVE, this::makeRemoveReplacement);
        dialog.show(getSupportFragmentManager(), REPLACEMENT_DIALOG_ADD_TAG);
    }

    /**
     * Crea y efectúa una transacción en base de datos para registrar una reposición
     * añadiendo conejos en la granja seleccionada.
     *
     * @param result El resultado del diálogo para crear la reposición
     */
    private void makeAddReplacement(Result result)
    {

    }

    /**
     * Crea y efectúa una transacción en base de datos para registrar una reposición
     * eliminando conejos en la granja seleccionada.
     *
     * @param result El resultado del diálogo para crear la reposición
     */
    private void makeRemoveReplacement(Result result)
    {

    }
}