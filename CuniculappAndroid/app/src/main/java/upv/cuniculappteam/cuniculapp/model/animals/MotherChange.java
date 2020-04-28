package upv.cuniculappteam.cuniculapp.model.animals;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class MotherChange extends Traceable
{
    public static final Creator<MotherChange> CREATOR = new TraceableCreator<>(MotherChange.class);

    private Integer amount;

    private Date date;

    private String reason;

    private String mother;

    public MotherChange() {}

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

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }
}
