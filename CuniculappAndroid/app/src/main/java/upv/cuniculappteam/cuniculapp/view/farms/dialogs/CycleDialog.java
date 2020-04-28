package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.widget.EditText;

import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.view.utils.DateEditText;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.CycleDialog.*;

public class CycleDialog extends DialogForResult<Result>
{
    private DateEditText inseminationDayEditText;

    public CycleDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        inseminationDayEditText = view.findViewById(R.id.cycle_dialog_insemination_etext);
        inseminationDayEditText.setDate(new Date(System.currentTimeMillis()));

        super.onBindView(view);
    }

    @Override
    public Collection<? extends EditText> getMandatoryFields()
    {
        return Lists.newArrayList(inseminationDayEditText);
    }

    @Override
    public Result getResult()
    {
        return new Result(inseminationDayEditText.getDate());
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_cycle;
    }

    public static class Result
    {
        private Date date;

        Result(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }
    }
}
