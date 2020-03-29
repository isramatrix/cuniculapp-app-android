package upv.cuniculappteam.cuniculapp.activity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.calendar.CalendarFragment;
import upv.cuniculappteam.cuniculapp.activity.cycles.CyclesFragment;
import upv.cuniculappteam.cuniculapp.activity.labor.LaborFragment;
import upv.cuniculappteam.cuniculapp.activity.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener
{
    private static final Fragment mainFragment = new CyclesFragment();

    private BottomNavigationView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.menu = findViewById(R.id.main_menu);
        this.menu.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.main_calendar: switchFragment(new CalendarFragment()); break;
            case R.id.main_labor: switchFragment(new LaborFragment()); break;
            case R.id.main_cycles: switchFragment(new CyclesFragment()); break;
            case R.id.main_settings: switchFragment(new SettingsFragment()); break;
            default: break;
        }

        return true;
    }

    private void switchFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        //if (!fragment.equals(mainFragment)) transaction.addToBackStack(null);
        transaction.commit();
    }
}
