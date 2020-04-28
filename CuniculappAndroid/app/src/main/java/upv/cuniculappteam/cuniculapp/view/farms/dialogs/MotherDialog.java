package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.widget.EditText;

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
                getSelectedReason()
        );
    }

    @Override
    public int getLayout() { return R.layout.dialog_mothers; }

    public static class Result
    {
        private final int amount;

        private final String reason;

        private Result(int amount, String reason)
        {
            this.amount = amount;
            this.reason = reason;
        }

        public int getAmount() {
            return amount;
        }

        public String getReason() {
            return reason;
        }
    }
}
