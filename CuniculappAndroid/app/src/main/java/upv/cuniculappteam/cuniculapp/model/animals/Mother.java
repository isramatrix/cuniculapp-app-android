package upv.cuniculappteam.cuniculapp.model.animals;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Mother extends Traceable
{
    public static final Creator<Mother> CREATOR = new TraceableCreator<>(Mother.class);

    private Integer id;

    private Integer alive;

    private Integer[] jails;

    private Cycle cycle;

    public Mother() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlive() {
        return alive;
    }

    public void setAlive(Integer alive) {
        this.alive = alive;
    }

    public Integer[] getJails() {
        return jails;
    }

    public void setJails(Integer[] jails) {
        this.jails = jails;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
}
