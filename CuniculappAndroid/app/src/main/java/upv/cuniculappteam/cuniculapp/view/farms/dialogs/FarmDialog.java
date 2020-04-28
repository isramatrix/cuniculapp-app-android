package upv.cuniculappteam.cuniculapp.view.farms.dialogs;

import android.app.AlertDialog;
import android.widget.EditText;

import com.google.common.collect.Lists;

import java.util.Collection;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.view.utils.dialog.DialogForResult;

import static upv.cuniculappteam.cuniculapp.view.farms.dialogs.FarmDialog.*;

public class FarmDialog extends DialogForResult<Result>
{
    private EditText nameEditText;

    private EditText jailsEditText;

    private EditText locationEditText;

    public FarmDialog(Header header, OnAcceptClickedListener<Result> result) {
        super(header, result);
    }

    @Override
    public void onBindView(AlertDialog view)
    {
        nameEditText = view.findViewById(R.id.farm_dialog_name_etext);
        jailsEditText = view.findViewById(R.id.farm_dialog_jails_etext);
        locationEditText = view.findViewById(R.id.farm_dialog_location_etext);

        super.onBindView(view);
    }

    @Override
    public Collection<? extends EditText> getMandatoryFields()
    {
        return Lists.newArrayList(nameEditText, jailsEditText, locationEditText);
    }

    @Override
    public Result getResult()
    {
        return new Result(
                nameEditText.getText().toString(),
                locationEditText.getText().toString(),
                Integer.parseInt(jailsEditText.getText().toString())
        );
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_farm;
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
