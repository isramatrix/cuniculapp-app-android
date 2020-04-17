package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.DialogFragment;

import upv.cuniculappteam.cuniculapp.R;

public abstract class DialogForResult<T> extends DialogFragment
{
    public enum Header
    {
        ADD(R.string.rabbits_dialog_header_add),
        REMOVE(R.string.rabbits_dialog_header_remove);

        private @StringRes int text;

        Header(int text) { this.text = text; }

        public @StringRes int getText() { return text; }
    };

    private final Header header;

    private final OnAcceptClickedListener<T> result;

    DialogForResult(Header header, OnAcceptClickedListener<T> result)
    {
        this.result = result;
        this.header = header;
    }

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setView(getLayout()).create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        // Se inicializa el comportamiento del botón para devolver el resultado.
        Button confirmButton = view.findViewById(R.id.rabbits_dialog_confirm);
        confirmButton.setOnClickListener(this::onConfirmClicked);

        // Se muestra el título del diálogo a mostrar
        TextView headerText = view.findViewById(R.id.rabbits_dialog_header);
        headerText.setText(header.getText());
    }

    private void onConfirmClicked(View view) {
        dismiss();
        result.onAccept(getResult());
    }

    abstract T getResult();

    @LayoutRes
    abstract int getLayout();

    public interface OnAcceptClickedListener<T> { void onAccept(T result); }

}
