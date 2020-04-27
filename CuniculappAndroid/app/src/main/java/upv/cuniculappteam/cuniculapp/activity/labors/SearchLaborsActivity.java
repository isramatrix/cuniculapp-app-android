package upv.cuniculappteam.cuniculapp.activity.labors;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.view.utils.DateEditText;

import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Sorting.FINISHED;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Sorting.PRIORITY;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Sorting.STARTED;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize.ARCHIVE;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize.MANUAL;
import static upv.cuniculappteam.cuniculapp.activity.labors.SearchLaborsActivity.Result.Visualize.UNFINISHED;
import static upv.cuniculappteam.cuniculapp.model.Labor.State.TO_DO;

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
        if (result != null && result.startDate != null) dateStartEditText.setDate(result.startDate);
        findViewById(R.id.labors_date_start_clear_button).setOnClickListener((v) -> dateStartEditText.clear());

        dateFinishEditText = findViewById(R.id.labors_date_end_text);
        if (result != null && result.finishDate != null) dateFinishEditText.setDate(result.finishDate);
        findViewById(R.id.labors_date_end_clear_button).setOnClickListener((v) -> dateFinishEditText.clear());

        sortRadioGroup = findViewById(R.id.labor_sort_rgroup);
        if (result != null) switch (result.sorting) {
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

        public List<Labor> filter(List<Labor> labors)
        {
            ArrayList<Labor> filter = new ArrayList<>();

            for (Labor labor : labors) {
                if (!labor.isFinished() && visualize.contains(UNFINISHED)) addFilter(filter, labor);
                else if (labor.getManual() && visualize.contains(MANUAL)) addFilter(filter, labor);
                else if (labor.getState() == Labor.State.ARCHIVED && visualize.contains(ARCHIVE)) addFilter(filter, labor);
            }

            switch (sorting) {
                case STARTED: Collections.sort(new ArrayList<>(labors), getStartedComparator()); break;
                case FINISHED: Collections.sort(new ArrayList<>(labors), getFinishedComparator()); break;
                case PRIORITY: Collections.sort(new ArrayList<>(labors), getPriorityComparator()); break;
            }

            return filter;
        }

        private void addFilter(List<Labor> labors, Labor labor)
        {
            boolean afterMin = startDate == null || labor.getDeadline().after(startDate);
            boolean beforeMax = finishDate == null || labor.getDeadline().before(finishDate);
            if (afterMin && beforeMax) labors.add(labor);
        }

        private Comparator<Labor> getFinishedComparator()
        {
            return (labor, other) -> {
                if (labor.isFinished() != other.isFinished()) return labor.isFinished() ? -1 : 1;
                return labor.getDeadline().compareTo(other.getDeadline());
            };
        }

        private Comparator<Labor> getStartedComparator()
        {
            return (labor, other) -> {
                if (labor.getState() == TO_DO && other.getState() != TO_DO)
                    return -1;
                else if (labor.getState() == TO_DO && other.getState() == TO_DO)
                    return labor.getDeadline().compareTo(other.getDeadline());
                else if (labor.getState() != TO_DO && other.getState() == TO_DO)
                    return 1;
                else
                    return labor.getDeadline().compareTo(other.getDeadline());
            };
        }

        private Comparator<Labor> getPriorityComparator()
        {
            return (labor, other) -> labor.getPriority().compareTo(other.getPriority());
        }
    }
}