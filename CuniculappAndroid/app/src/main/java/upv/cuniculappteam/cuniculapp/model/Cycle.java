package upv.cuniculappteam.cuniculapp.model;

import android.content.Context;
import android.os.Parcelable;

import androidx.annotation.StringRes;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import upv.cuniculappteam.cuniculapp.R;
import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

public class Cycle extends Traceable
{
    public static final Parcelable.Creator<Cycle> CREATOR = new TraceableCreator<>(Cycle.class);



    public enum State { IN_PROGRESS, EDITABLE, FINISHED;}
    private String name;

    private Date date;

    private State state;

    private String farm;

    public Cycle() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getName(Context context)
    {
        String[] months = context.getResources().getStringArray(R.array.months);
        Calendar calendar = getInstance(); calendar.setTime(getDate());
        return String.format(Locale.getDefault(), "%s - %d", months[calendar.get(MONTH)], calendar.get(YEAR));
    }
}
