package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.CycleDialog.*;

public class CycleDialog extends DialogForResult<Result>
{
    public CycleDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        super.onBindView(view);
    }

    @Override
    public Result getResult() {
        return new Result();
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_mothers;
    }

    public static class Result { }
}
