package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;

import upv.cuniculappteam.cuniculapp.R;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog.*;

public class KittenDialog extends RabbitDialog<Result>
{
    public KittenDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public Collection<? extends EditText> getMandatoryFields() {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public Result getResult()
    {
        return new Result(0, getSelectedReason(), getReasonMessage());
    }

    @Override
    public int getLayout() { return R.layout.dialog_kittens; }

    public static class Result
    {
        private final int amount;

        private final Reason reason;

        private final String notes;

        Result(int amount, Reason reason, String notes)
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
