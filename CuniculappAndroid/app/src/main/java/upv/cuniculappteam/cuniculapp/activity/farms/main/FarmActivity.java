package upv.cuniculappteam.cuniculapp.activity.farms.main;

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
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;

public class FarmActivity extends AppCompatActivity
{
    public static final String FARM_INTENT_KEY = "farms";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        Farm farm = getIntent().getParcelableExtra(FARM_INTENT_KEY);
        if (farm == null) { finish(); return; }

        // Indica el nombre de la granja que se está gestionando.
        TextView farmTitle = findViewById(R.id.farm_title);
        farmTitle.setText(farm.getName());

        // Inicializa los atributos de la granja que se pueden gestionar.
        ViewPager farmPager = findViewById(R.id.farm_pager);
        farmPager.setAdapter(new FarmAdapter(farm));

        // Construye una vista deslizable de los atributos de la granja.
        TabLayout tabLayout = findViewById(R.id.farm_tab);
        tabLayout.setupWithViewPager(farmPager);
    }

    class FarmAdapter extends FragmentPagerAdapter
    {
        private final List<NamedFragment> fragments;

        FarmAdapter(Farm farm)
        {
            super(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

            // Crea las vistas de los atributos de la granja como fragmentos Android.
            this.fragments = new ArrayList<>();
            this.fragments.add(new CyclesFragment(farm));
            this.fragments.add(new ReplacementsFragment(farm));
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