package upv.cuniculappteam.cuniculapp.activity.labors;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import upv.cuniculappteam.cuniculapp.R;

public class SearchLaborsActivity extends AppCompatActivity
{
    public static final String FILTER_PARAMS_PARCEL_KEY = "labor_filters";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labors_search);
    }
}
