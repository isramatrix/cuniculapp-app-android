package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.common.collect.Lists;

import java.util.Collection;

import upv.cuniculappteam.cuniculapp.R;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.KittenDialog.*;

public class KittenDialog extends RabbitDialog<Result>
{
    private EditText maternalNest;

    private EditText maternalBait;

    private EditText meetNest;

    private EditText meetBait;

    public KittenDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        maternalNest = view.findViewById(R.id.rabbits_dialog_kitten_maternal_nest);
        maternalBait = view.findViewById(R.id.rabbits_dialog_kitten_maternal_bait);
        meetNest = view.findViewById(R.id.rabbits_dialog_kitten_meet_nest);
        meetBait = view.findViewById(R.id.rabbits_dialog_kitten_meet_bait);

        super.onBindView(view);
    }

    @Override
    public Collection<? extends EditText> getMandatoryFields()
    {
        return Lists.newArrayList(maternalNest, maternalBait, meetNest, meetBait);
    }

    @Override
    public Result getResult()
    {
        return new Result(
                Integer.parseInt(maternalNest.getText().toString()),
                Integer.parseInt(maternalBait.getText().toString()),
                Integer.parseInt(meetNest.getText().toString()),
                Integer.parseInt(meetBait.getText().toString()),
                getSelectedReason(),
                getReasonMessage()
        );
    }

    @Override
    public int getLayout() { return R.layout.dialog_kittens; }

    public static class Result
    {
        private final int maternalNest;

        private final int maternalBait;

        private final int meetNest;

        private final int meetBait;

        private final Reason reason;

        private final String notes;

        private Result(int maternalNest, int maternalBait, int meetNest, int meetBait, Reason reason, String notes) {
            this.maternalNest = maternalNest;
            this.maternalBait = maternalBait;
            this.meetNest = meetNest;
            this.meetBait = meetBait;
            this.reason = reason;
            this.notes = notes;
        }

        public int getMaternalNest() {
            return maternalNest;
        }

        public int getMaternalBait() {
            return maternalBait;
        }

        public int getMeetNest() {
            return meetNest;
        }

        public int getMeetBait() {
            return meetBait;
        }

        public Reason getReason() {
            return reason;
        }

        public String getNotes() {
            return notes;
        }
    }
}
