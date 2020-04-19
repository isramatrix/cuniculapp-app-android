package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.FarmDialog.*;

public class FarmDialog extends DialogForResult<Result>
{
    public FarmDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view) {
        super.onBindView(view);
    }

    @Override
    public Result getResult() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_mothers;
    }

    public static class Result
    {
        private final String name;
        private final String location;
        private final int jailsAmount;

        public Result(String name, String location, int jailsAmount) {
            this.name = name;
            this.location = location;
            this.jailsAmount = jailsAmount;
        }

        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }

        public int getJailsAmount() {
            return jailsAmount;
        }
    }
}
