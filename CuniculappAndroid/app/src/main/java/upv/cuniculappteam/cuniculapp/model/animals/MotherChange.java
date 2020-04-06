package upv.cuniculappteam.cuniculapp.model.animals;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class MotherChange extends Traceable
{
    public static final Creator<MotherChange> CREATOR = new TraceableCreator<>(MotherChange.class);

    private Integer id;

    private Integer amount;

    private Date date;

    private String reason;

    private Mother mother;

    public MotherChange() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }
}
