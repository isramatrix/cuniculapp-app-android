package upv.cuniculappteam.cuniculapp.model.facilities;

import upv.cuniculappteam.cuniculapp.model.User;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Farm extends Traceable
{
    public static final Creator<Farm> CREATOR = new TraceableCreator<>(Farm.class);

    private Integer id;

    private String name;

    private Integer jailsAmount;

    private Location localization;

    private Integer rabbitsGestatingAmount;

    private Integer rabbitsGestatingDays;

    private Integer[] rabbitsGestatingJails;

    private User user;

    public Farm() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getJailsAmount() {
        return jailsAmount;
    }

    public void setJailsAmount(Integer jailsAmount) {
        this.jailsAmount = jailsAmount;
    }

    public Location getLocalization() {
        return localization;
    }

    public void setLocalization(Location localization) {
        this.localization = localization;
    }

    public Integer getRabbitsGestatingAmount() {
        return rabbitsGestatingAmount;
    }

    public void setRabbitsGestatingAmount(Integer rabbitsGestatingAmount) {
        this.rabbitsGestatingAmount = rabbitsGestatingAmount;
    }

    public Integer getRabbitsGestatingDays() {
        return rabbitsGestatingDays;
    }

    public void setRabbitsGestatingDays(Integer rabbitsGestatingDays) {
        this.rabbitsGestatingDays = rabbitsGestatingDays;
    }

    public Integer[] getRabbitsGestatingJails() {
        return rabbitsGestatingJails;
    }

    public void setRabbitsGestatingJails(Integer[] rabbitsGestatingJails) {
        this.rabbitsGestatingJails = rabbitsGestatingJails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
