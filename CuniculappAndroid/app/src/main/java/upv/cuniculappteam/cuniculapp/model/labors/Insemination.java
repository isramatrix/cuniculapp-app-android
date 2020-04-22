package upv.cuniculappteam.cuniculapp.model.labors;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Insemination extends Traceable
{
    public static final Creator<Insemination> CREATOR = new TraceableCreator<>(Insemination.class);

    private Date date;

    private String labor;

    private Integer inseminatedRabbits;

    private String cycle;

    public Insemination() { }

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

    public Integer getInseminatedRabbits() {
        return inseminatedRabbits;
    }

    public void setInseminatedRabbits(Integer inseminatedRabbits) {
        this.inseminatedRabbits = inseminatedRabbits;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }
}
