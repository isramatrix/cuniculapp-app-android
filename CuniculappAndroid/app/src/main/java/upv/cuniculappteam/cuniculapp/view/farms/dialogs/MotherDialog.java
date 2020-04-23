package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.collect.Lists;

import java.util.Collection;

import upv.cuniculappteam.cuniculapp.R;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog.*;

public class MotherDialog extends RabbitDialog<Result>
{
    private EditText motherAmount;

    public MotherDialog(Header header, OnAcceptClickedListener<Result> result)
    {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        motherAmount = view.findViewById(R.id.rabbits_dialog_mother_amount);

        super.onBindView(view);
    }

    @Override
    public Collection<? extends EditText> getMandatoryFields()
    {
        return Lists.newArrayList(motherAmount);
    }

    @Override
    public Result getResult()
    {
        return new Result(
                Integer.parseInt(motherAmount.getText().toString()),
                getSelectedReason(),
                getReasonMessage()
        );
    }

    @Override
    public int getLayout() { return R.layout.dialog_mothers; }

    public static class Result
    {
        private final int amount;

        private final Reason reason;

        private final String notes;

        private Result(int amount, Reason reason, String notes)
        {
            this.amount = amount;
            this.reason = reason;
            this.notes = notes;
        }

        public int getAmount() {
            return amount;
        }

        public Reason getReason() {
            return reason;
        }

        public String getNotes() {
            return notes;
        }
    }
}
