package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;

import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.ReplacementDialog.*;

public class ReplacementDialog extends DialogForResult<Result>
{
    public ReplacementDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view) {
        super.onBindView(view);
    }

    @Override
    public Result getResult() {
        return new Result();
    }

    @Override
    public int getLayout() {
        return 0;
    }

    public static class Result { }
}
