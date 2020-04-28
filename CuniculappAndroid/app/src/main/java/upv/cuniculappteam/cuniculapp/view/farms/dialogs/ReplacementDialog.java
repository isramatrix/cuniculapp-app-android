package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.widget.EditText;

import com.google.common.collect.Lists;

import java.util.Collection;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.ReplacementDialog.*;

public class ReplacementDialog extends DialogForResult<Result>
{
    private EditText rabbitsEditText;

    private EditText daysEditText;

    public ReplacementDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        rabbitsEditText = view.findViewById(R.id.replacement_dialog_rabbits_etext);
        daysEditText = view.findViewById(R.id.replacement_dialog_days_etext);

        super.onBindView(view);
    }

    @Override
    public Collection<? extends EditText> getMandatoryFields()
    {
        return Lists.newArrayList(rabbitsEditText, daysEditText);
    }

    @Override
    public Result getResult()
    {
        return new Result(
               Integer.parseInt(daysEditText.getText().toString()),
               Integer.parseInt(rabbitsEditText.getText().toString())
        );
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_replacement;
    }

    public static class Result {
        private int days;
        private int rabbits;

        Result(int days, int rabbits) {
            this.days = days;
            this.rabbits = rabbits;
        }

        public int getDays() {
            return days;
        }

        public int getRabbits() {
            return rabbits;
        }
    }
}