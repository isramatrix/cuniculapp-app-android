package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.farms.main.FarmActivity;
import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;

public class CycleActivity extends AppCompatActivity
{
    public static final String CYCLE_INTENT_KEY = "cycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        Cycle cycle = getIntent().getParcelableExtra(CYCLE_INTENT_KEY);
    }
}
