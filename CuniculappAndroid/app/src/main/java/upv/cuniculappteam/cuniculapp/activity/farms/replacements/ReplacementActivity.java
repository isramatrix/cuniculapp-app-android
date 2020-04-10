package upv.cuniculappteam.cuniculapp.activity.farms.replacements;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.Replacement;

public class ReplacementActivity extends AppCompatActivity
{
    public static final String REPLACEMENT_INTENT_KEY = "replacement";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replacement);

        Replacement replacement = getIntent().getParcelableExtra(REPLACEMENT_INTENT_KEY);

        TextView daysText = findViewById(R.id.replacement_days_text);
        daysText.setText(replacement.getDays().toString());

        TextView rabbitsText = findViewById(R.id.replacement_rabbits_text);
        rabbitsText.setText(replacement.getRabbitsAmount().toString());

        EditText jailsEditText = findViewById(R.id.replacement_jails_etext);
        // TODO: Inflar el contenido de esta vista.

        TableLayout timelineTable = findViewById(R.id.replacement_timeline_table);
        // TODO: Inlfar el contenido de esta vista.

        findViewById(R.id.replacement_close_button).setOnClickListener((v) -> finish());
        findViewById(R.id.replacement_add_jail_button).setOnClickListener(this::addJail);
        findViewById(R.id.replacement_remove_jail_button).setOnClickListener(this::removeJail);
    }

    private void addJail(View view)
    {

    }

    private void removeJail(View view)
    {

    }
}
