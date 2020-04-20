package upv.cuniculappteam.cuniculapp.model.animals;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Kitten extends Traceable
{
    public static final Creator<Kitten> CREATOR = new TraceableCreator<>(Kitten.class);

    private Integer maternalNest;

    private Integer maternalFatten;

    private Integer meatNest;

    private Integer meatFatten;

    private Integer maternalNestJails;

    private Integer maternalFattenJails;

    private Integer meatNestJails;

    private Integer meatFattenJails;

    private Cycle cycle;

    public Kitten() { }

    public Integer getMaternalNest() {
        return maternalNest;
    }

    public void setMaternalNest(Integer maternalNest) {
        this.maternalNest = maternalNest;
    }

    public Integer getMaternalFatten() {
        return maternalFatten;
    }

    public void setMaternalFatten(Integer maternalFatten) {
        this.maternalFatten = maternalFatten;
    }

    public Integer getMeatNest() {
        return meatNest;
    }

    public void setMeatNest(Integer meatNest) {
        this.meatNest = meatNest;
    }

    public Integer getMeatFatten() {
        return meatFatten;
    }

    public void setMeatFatten(Integer meatFatten) {
        this.meatFatten = meatFatten;
    }

    public Integer getMaternalNestJails() {
        return maternalNestJails;
    }

    public void setMaternalNestJails(Integer maternalNestJails) {
        this.maternalNestJails = maternalNestJails;
    }

    public Integer getMaternalFattenJails() {
        return maternalFattenJails;
    }

    public void setMaternalFattenJails(Integer maternalFattenJails) {
        this.maternalFattenJails = maternalFattenJails;
    }

    public Integer getMeatNestJails() {
        return meatNestJails;
    }

    public void setMeatNestJails(Integer meatNestJails) {
        this.meatNestJails = meatNestJails;
    }

    public Integer getMeatFattenJails() {
        return meatFattenJails;
    }

    public void setMeatFattenJails(Integer meatFattenJails) {
        this.meatFattenJails = meatFattenJails;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
}
