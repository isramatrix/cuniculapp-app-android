package upv.cuniculappteam.cuniculapp.view.utils.dialog;

import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.CallSuper;
import androidx.annotation.StringRes;

import com.google.errorprone.annotations.DoNotCall;

import java.util.Collection;

import upv.cuniculappteam.cuniculapp.R;

public abstract class DialogForResult<T> extends Dialog implements TextWatcher {
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

    private Button confirmButton;

    public DialogForResult(Header header, OnAcceptClickedListener<T> result)
    {
        this.header = header;
        this.result = result;
    }

    @Override @CallSuper
    public void onBindView(AlertDialog view)
    {
        // Se inicializa el comportamiento del botón para devolver el resultado.
        confirmButton = view.findViewById(R.id.dialog_confirm);
        confirmButton.setOnClickListener(this::onConfirmClicked);

        // Se muestra el título del diálogo a mostrar.
        TextView headerText = view.findViewById(R.id.dialog_header);
        headerText.setText(header.getText());

        for (EditText editText : getMandatoryFields()) editText.addTextChangedListener(this);
        onTextChanged(null, 0, 0, 0);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
    {
        boolean enabled = true;

        for (EditText editText : getMandatoryFields())
            enabled &= !editText.getText().toString().isEmpty();

        confirmButton.setEnabled(enabled);
    }

    @Override
    public void afterTextChanged(Editable editable) { }

    private void onConfirmClicked(View view)
    {
        dismiss();
        result.onAccept(getResult());
    }

    public abstract Collection<? extends EditText> getMandatoryFields();

    public abstract T getResult();

    public interface OnAcceptClickedListener<T> { void onAccept(T result); }

}
