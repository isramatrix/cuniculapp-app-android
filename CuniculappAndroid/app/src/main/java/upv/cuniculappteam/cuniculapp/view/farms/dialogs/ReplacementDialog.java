package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.widget.EditText;

import com.google.common.collect.Lists;

import java.util.Collection;

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
    public Collection<? extends EditText> getMandatoryFields()
    {
        return Lists.newArrayList();
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
