package upv.cuniculappteam.cuniculapp.model.facilities;

import androidx.annotation.StringRes;

import upv.cuniculappteam.cuniculapp.model.User;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Farm extends Traceable
{
    public static final Creator<Farm> CREATOR = new TraceableCreator<>(Farm.class);

    private String name;

    private Integer jailsAmount;

    private String localization;

    private Integer rabbitsGestatingAmount;

    private Integer rabbitsGestatingDays;

    private Integer[] rabbitsGestatingJails;

    private String user;

    public Farm() { }

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

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
