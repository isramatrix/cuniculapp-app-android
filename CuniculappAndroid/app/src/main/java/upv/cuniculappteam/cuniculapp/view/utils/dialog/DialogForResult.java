package upv.cuniculappteam.cuniculapp.view.utils.dialog;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.StringRes;

import upv.cuniculappteam.cuniculapp.R;

public abstract class DialogForResult<T> extends Dialog
{
    public enum Header
    {
        ADD(R.string.rabbits_dialog_header_add),
        REMOVE(R.string.rabbits_dialog_header_remove);

        private @StringRes
        int text;

        Header(int text) { this.text = text; }

        public @StringRes int getText() { return text; }
    };

    private final OnAcceptClickedListener<T> result;

    private final Header header;

    public DialogForResult(Header header, OnAcceptClickedListener<T> result)
    {
        this.header = header;
        this.result = result;
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        // Se inicializa el comportamiento del botón para devolver el resultado.
        Button confirmButton = view.findViewById(R.id.rabbits_dialog_confirm);
        confirmButton.setOnClickListener(this::onConfirmClicked);

        // Se muestra el título del diálogo a mostrar.
        TextView headerText = view.findViewById(R.id.rabbits_dialog_header);
        headerText.setText(header.getText());
    }

    private void onConfirmClicked(View view)
    {
        dismiss();
        result.onAccept(getResult());
    }

    public abstract T getResult();

    public interface OnAcceptClickedListener<T> { void onAccept(T result); }

}
