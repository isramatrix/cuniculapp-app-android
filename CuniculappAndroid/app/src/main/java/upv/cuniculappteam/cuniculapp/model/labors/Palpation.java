package upv.cuniculappteam.cuniculapp.model.labors;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Palpation extends Traceable
{
    public static final Creator<Palpation> CREATOR = new TraceableCreator<>(Palpation.class);

    private Integer id;

    private Date date;

    private Labor labor;

    private Integer pregnantRabbits;

    private Cycle cycle;

    public Palpation() { }

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

    public Integer getPregnantRabbits() {
        return pregnantRabbits;
    }

    public void setPregnantRabbits(Integer pregnantRabbits) {
        this.pregnantRabbits = pregnantRabbits;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
}
