package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.model.Cycle;

public class CycleActivity extends AppCompatActivity
{
    public static final String CYCLE_INTENT_KEY = "cycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        Cycle cycle = getIntent().getParcelableExtra(CYCLE_INTENT_KEY);
        if (cycle == null) return;

        // Indica el nombre del ciclo que se está gestionando.
        TextView farmTitle = findViewById(R.id.cycle_title);
        farmTitle.setText(cycle.getName());

        // Inicializa los atributos del ciclo que se pueden gestionar.
        ViewPager cyclePager = findViewById(R.id.cycle_pager);
        cyclePager.setAdapter(new CycleActivity.CycleAdapter(cycle));

        // Construye una vista deslizable de los atributos del ciclo.
        TabLayout tabLayout = findViewById(R.id.cycle_tab);
        tabLayout.setupWithViewPager(cyclePager);
    }

    class CycleAdapter extends FragmentPagerAdapter
    {
        private final List<NamedFragment> fragments;

        CycleAdapter(Cycle cycle)
        {
            super(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

            // Crea las vistas de los atributos de la granja como fragmentos Android.
            this.fragments = new ArrayList<>();
            this.fragments.add(new RabbitsFragment(cycle));
            this.fragments.add(new EventsFragment(cycle));
        }

        @NonNull
        @Override
        public Fragment getItem(int position) { return fragments.get(position); }

        @Override
        public int getCount() { return fragments.size(); }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(fragments.get(position).getFragmentName());
        }
    }
}
