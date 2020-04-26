package upv.cuniculappteam.cuniculapp.model;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Labor extends Traceable
{
    public enum TaskType
    {
        INSEMINATION,
        LABOUR,
        SALE,
        SET_UP_NESTS,
        PHOTOPERIOD_START,
        PHOTOPERIOD_END,
        MOTHER_FEED_START,
        FODDER_FEED_START,
        RETIRE_FEED_START,
        PALPABLE_RABBITS_START,
        PALPABLE_RABBITS_FINISH,
        USER_MADE
    };

    public enum State { TO_DO, DONE, ARCHIVED }

    public enum Priority { LOW, MEDIUM, HIGH }

    public static final Creator<Labor> CREATOR = new TraceableCreator<>(Labor.class);

    private String user;

    private String name;

    private String description;

    private Date deadline;

    private Date finishedDate;

    private Boolean manual;

    private Integer icon;

    private State state;

    private TaskType taskType;

    private Priority priority;

    public Labor() { }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public boolean isFinished() { return finishedDate != null; }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public void setNotFinished() { finishedDate = null; }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
