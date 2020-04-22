package upv.cuniculappteam.cuniculapp.model.labors;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Labour extends Traceable
{
    public static final Creator<Labour> CREATOR = new TraceableCreator<>(Labour.class);

    private Date date;

    private String labor;

    private Integer mothersAmount;

    private Integer bornAlive;

    private Integer bornDead;

    private String cycle;

    public Labour() { }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLabor() {
        return labor;
    }

    public void setLabor(String labor) {
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

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}
