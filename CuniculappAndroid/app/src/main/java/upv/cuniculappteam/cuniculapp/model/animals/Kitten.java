package upv.cuniculappteam.cuniculapp.model.animals;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Kitten extends Traceable
{
    public static final Creator<Kitten> CREATOR = new TraceableCreator<>(Kitten.class);

    private Integer maternal;

    private Integer meat;

    private Integer maternalJails;

    private Integer meatJails;

    private String cycle;

    public Kitten() { }

    public Integer getMaternal() {
        return maternal;
    }

    public void setMaternal(Integer maternalNest) {
        this.maternal = maternalNest;
    }

    public Integer getMeat() {
        return meat;
    }

    public void setMeat(Integer meatNest) {
        this.meat = meatNest;
    }

    public Integer getMaternalJails() {
        return maternalJails;
    }

    public void setMaternalJails(Integer maternalNestJails) {
        this.maternalJails = maternalNestJails;
    }

    public Integer getMeatJails() {
        return meatJails;
    }

    public void setMeatJails(Integer meatJails) {
        this.meatJails = meatJails;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycleID(String cycle) {
        this.cycle = cycle;
    }
}
