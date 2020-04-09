package upv.cuniculappteam.cuniculapp.activity.farms.main;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

abstract class FarmFragment extends Fragment
{
    abstract @StringRes int getFragmentName();
}
