package upv.cuniculappteam.cuniculapp.activity.labors;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;
import upv.cuniculappteam.cuniculapp.view.utils.DateEditText;

import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Sorting.FINISHED;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Sorting.PRIORITY;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Sorting.STARTED;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize.ARCHIVE;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize.MANUAL;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize.UNFINISHED;

public class SearchLaborsActivity extends AppCompatActivity
{
    public static final String FILTER_PARAMS_PARCEL_KEY = "labor_filters";

    private CheckBox visualizeUnfinishedCheckBox;

    private CheckBox visualizeArchiveCheckBox;

    private CheckBox visualizeManualCheckBox;

    private DateEditText dateStartEditText;

    private DateEditText dateFinishEditText;

    private RadioGroup sortRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labors_search);

        Result result = null;
        if (getIntent().hasExtra(FILTER_PARAMS_PARCEL_KEY))
            result = (Result) getIntent().getSerializableExtra(FILTER_PARAMS_PARCEL_KEY);

        visualizeUnfinishedCheckBox = findViewById(R.id.labors_visualize_unfinished);
        if (result != null) visualizeUnfinishedCheckBox.setChecked(result.visualize.contains(UNFINISHED));

        visualizeArchiveCheckBox = findViewById(R.id.labors_visualize_archived);
        if (result != null) visualizeArchiveCheckBox.setChecked(result.visualize.contains(ARCHIVE));

        visualizeManualCheckBox = findViewById(R.id.labors_visualize_manual);
        if (result != null) visualizeManualCheckBox.setChecked(result.visualize.contains(MANUAL));

        dateStartEditText = findViewById(R.id.labors_date_start_text);
        if (result != null && result.getStartDate() != null) dateStartEditText.setDate(result.getStartDate());
        findViewById(R.id.labors_date_start_clear_button).setOnClickListener((v) -> dateStartEditText.clear());

        dateFinishEditText = findViewById(R.id.labors_date_end_text);
        if (result != null && result.getFinishDate() != null) dateFinishEditText.setDate(result.getFinishDate());
        findViewById(R.id.labors_date_end_clear_button).setOnClickListener((v) -> dateFinishEditText.clear());

        sortRadioGroup = findViewById(R.id.labor_sort_rgroup);
        if (result != null) switch (result.getSorting()) {
            case FINISHED: sortRadioGroup.check(R.id.labor_sort_finished_radio); break;
            case STARTED: sortRadioGroup.check(R.id.labor_sort_started_radio); break;
            case PRIORITY: sortRadioGroup.check(R.id.labor_sort_priority_radio); break;
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent();
        intent.putExtra(FILTER_PARAMS_PARCEL_KEY, getResult());
        setResult(RESULT_OK, intent);
        finish();
    }

    private Result getResult()
    {
        ArrayList<Visualize> visualize = new ArrayList<>();
        if (visualizeUnfinishedCheckBox.isChecked()) visualize.add(UNFINISHED);
        if (visualizeArchiveCheckBox.isChecked()) visualize.add(ARCHIVE);
        if (visualizeManualCheckBox.isChecked()) visualize.add(MANUAL);

        Result.Sorting sorting = null;
        if (sortRadioGroup.getCheckedRadioButtonId() == R.id.labor_sort_finished_radio) sorting = FINISHED;
        if (sortRadioGroup.getCheckedRadioButtonId() == R.id.labor_sort_started_radio) sorting = STARTED;
        if (sortRadioGroup.getCheckedRadioButtonId() == R.id.labor_sort_priority_radio) sorting = PRIORITY;

        return new Result(visualize, sorting, dateStartEditText.getDate(), dateFinishEditText.getDate());
    }

    public static class Result implements Serializable
    {
        public enum Visualize { UNFINISHED, ARCHIVE, MANUAL }

        public enum Sorting { FINISHED, STARTED, PRIORITY }

        private final ArrayList<Visualize> visualize;

        private final Sorting sorting;

        private final Date startDate;

        private final Date finishDate;

        public Result(ArrayList<Visualize> visualize, Sorting sorting, Date startDate, Date finishDate) {
            this.visualize = visualize;
            this.sorting = sorting;
            this.startDate = startDate;
            this.finishDate = finishDate;
        }

        public List<Visualize> getVisualize() {
            return visualize;
        }

        public Sorting getSorting() {
            return sorting;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getFinishDate() {
            return finishDate;
        }
    }
}