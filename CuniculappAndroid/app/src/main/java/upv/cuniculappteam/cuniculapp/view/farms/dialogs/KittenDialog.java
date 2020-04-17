package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog.KittenResult.Reason;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog.*;

public class KittenDialog extends DialogForResult<KittenResult>
{
    public KittenDialog(Header header, OnAcceptClickedListener<KittenResult> result) {
        super(header, result);
    }

    @Override
    int getLayout() { return R.layout.dialog_kittens; }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    KittenResult getResult()
    {
        return new KittenResult(0, Reason.DEAD, null);
    }

    public static class KittenResult
    {
        public enum Reason { DEAD, TRANSFER, OTHER }

        private final int amount;

        private final Reason reason;

        private final String notes;

        KittenResult(int amount, Reason reason, String notes)
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
