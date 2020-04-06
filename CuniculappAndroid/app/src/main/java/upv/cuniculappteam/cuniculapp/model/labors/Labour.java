package upv.cuniculappteam.cuniculapp.model.labors;

import android.os.Parcelable;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Labour extends Traceable
{
    public static final Creator<Labour> CREATOR = new TraceableCreator<>(Labour.class);

    private Integer id;

    private Date date;

    private Labor labor;

    private Integer mothersAmount;

    private Integer bornAlive;

    private Integer bornDead;

    private Cycle cycle;

    public Labour() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Labor getLabor() {
        return labor;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
    }

    public Integer getMothersAmount() {
        return mothersAmount;
    }

    public void setMothersAmount(Integer mothersAmount) {
        this.mothersAmount = mothersAmount;
    }

    public Integer getBornAlive() {
        return bornAlive;
    }

    public void setBornAlive(Integer bornAlive) {
        this.bornAlive = bornAlive;
    }

    public Integer getBornDead() {
        return bornDead;
    }

    public void setBornDead(Integer bornDead) {
        this.bornDead = bornDead;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
}
