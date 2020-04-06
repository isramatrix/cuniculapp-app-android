package upv.cuniculappteam.cuniculapp.model;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class User extends Traceable
{
    public static final Creator<User> CREATOR = new TraceableCreator<>(User.class);

    private Integer id;

    private String name;

    private Boolean alertsOn;

    private Boolean rememberTasks;

    private Boolean reminders;

    private Boolean dailySummary;

    public User() { }

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

    public Boolean getAlertsOn() {
        return alertsOn;
    }

    public void setAlertsOn(Boolean alertsOn) {
        this.alertsOn = alertsOn;
    }

    public Boolean getRememberTasks() {
        return rememberTasks;
    }

    public void setRememberTasks(Boolean rememberTasks) {
        this.rememberTasks = rememberTasks;
    }

    public Boolean getReminders() {
        return reminders;
    }

    public void setReminders(Boolean reminders) {
        this.reminders = reminders;
    }

    public Boolean getDailySummary() {
        return dailySummary;
    }

    public void setDailySummary(Boolean dailySummary) {
        this.dailySummary = dailySummary;
    }
}
