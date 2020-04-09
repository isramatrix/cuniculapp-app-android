package upv.cuniculappteam.cuniculapp.activity.farms.replacements;

import android.os.Bundle;

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
    }
}
