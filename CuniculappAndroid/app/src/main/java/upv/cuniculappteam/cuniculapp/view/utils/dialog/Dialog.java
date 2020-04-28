package upv.cuniculappteam.cuniculapp.view.utils.dialog;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public abstract class Dialog extends DialogFragment
{
    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        AlertDialog alertDialog = builder.setView(getLayout()).create();
        alertDialog.show();
        onBindView(alertDialog);

        return alertDialog;
    }

    public abstract void onBindView(AlertDialog view);

    @LayoutRes
    public abstract int getLayout();
}
