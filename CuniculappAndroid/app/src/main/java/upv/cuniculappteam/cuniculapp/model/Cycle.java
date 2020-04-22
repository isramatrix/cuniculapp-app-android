package upv.cuniculappteam.cuniculapp.model;

import android.os.Parcelable;

import upv.cuniculappteam.cuniculapp.model.facilities.Farm;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Cycle extends Traceable
{
    public static final Parcelable.Creator<Cycle> CREATOR = new TraceableCreator<>(Cycle.class);

    public enum State { A, B, C }

    private String name;

    private State state;

    private String farm;

    public Cycle() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
