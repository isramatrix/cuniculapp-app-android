package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import upv.cuniculappteam.cuniculapp.R;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog.*;
import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.MotherDialog.MotherResult.*;

public class MotherDialog extends DialogForResult<MotherResult>
{
    public MotherDialog(Header header, OnAcceptClickedListener<MotherResult> result) {
        super(header, result);
    }

    @Override
    int getLayout() { return R.layout.dialog_mothers; }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    MotherResult getResult()
    {
        return new MotherResult(0, Reason.DEAD, null);
    }

    public static class MotherResult
    {
        public enum Reason { DEAD, TRANSFER, OTHER }

        private final int amount;

        private final Reason reason;

        private final String notes;

        MotherResult(int amount, Reason reason, String notes)
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
