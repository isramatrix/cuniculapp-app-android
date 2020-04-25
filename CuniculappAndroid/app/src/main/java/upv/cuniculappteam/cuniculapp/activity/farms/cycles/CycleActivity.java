package upv.cuniculappteam.cuniculapp.activity.farms.cycles;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.utils.NamedFragment;
import upv.cuniculappteam.cuniculapp.model.Cycle;

public class CycleActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener
{
    public static final String CYCLE_INTENT_KEY = "cycle";

    private CycleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);

        Cycle cycle = getIntent().getParcelableExtra(CYCLE_INTENT_KEY);
        if (cycle == null) { finish(); return; }

        // Indica el nombre del ciclo que se está gestionando.
        TextView farmTitle = findViewById(R.id.cycle_title);
        farmTitle.setText(cycle.getName());

        // Inicializa los atributos del ciclo que se pueden gestionar.
        ViewPager cyclePager = findViewById(R.id.cycle_pager);
        cyclePager.setAdapter(this.adapter = new CycleAdapter(cycle));
        cyclePager.addOnPageChangeListener(this);

        // Construye una vista deslizable de los atributos del ciclo.
        TabLayout tabLayout = findViewById(R.id.cycle_tab);
        tabLayout.setupWithViewPager(cyclePager);
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position)
    {
        List<NamedFragment> fragments = adapter.fragments;

        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = (Fragment) fragments.get(i);
            fragment.onHiddenChanged(position != i);
        }
    }

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see ViewPager#SCROLL_STATE_IDLE
     * @see ViewPager#SCROLL_STATE_DRAGGING
     * @see ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) { }

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
        public Fragment getItem(int position) { return (Fragment) fragments.get(position); }

        @Override
        public int getCount() { return fragments.size(); }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(fragments.get(position).getFragmentName());
        }
    }
}
