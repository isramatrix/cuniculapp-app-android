package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.widget.RadioGroup;
import android.widget.TextView;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult;

public abstract class RabbitDialog<T> extends DialogForResult<T>
{
    public enum Reason { DEAD, TRANSFER, OTHER; }

    private RadioGroup radioGroup;

    private TextView otherReasonText;

    RabbitDialog(Header header, OnAcceptClickedListener<T> result)
    {
       super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        radioGroup = view.findViewById(R.id.rabbits_dialog__rgroup);

        // Se inicializa el comportamiento del texto editable de otra razón.
        otherReasonText = view.findViewById(R.id.rabbits_dialog_other_etext);
        otherReasonText.setOnFocusChangeListener((v, f) -> { if (f) radioGroup.check(R.id.rabbits_dialog_other_radio); });
    }

    Reason getSelectedReason()
    {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rabbits_dialog_transfer_radio:
                return Reason.TRANSFER;

            case R.id.rabbits_dialog_death_radio:
                return Reason.DEAD;

            case R.id.rabbits_dialog_other_radio: default:
                return Reason.OTHER;
        }
    }

    String getReasonMessage()
    {
        return otherReasonText.getText().toString();
    }
}