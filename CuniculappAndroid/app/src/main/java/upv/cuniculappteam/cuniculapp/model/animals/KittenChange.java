package upv.cuniculappteam.cuniculapp.model.animals;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class KittenChange extends Traceable
{
    public static final Creator<KittenChange> CREATOR = new TraceableCreator<>(KittenChange.class);

    private Integer maternalAmount;

    private Integer meatAmount;

    private String reason;

    private Date date;

    private String kittens;

    public KittenChange() { }

    public Integer getMaternalAmount() {
        return maternalAmount;
    }

    public void setMaternalAmount(Integer maternalNestAmount) {
        this.maternalAmount = maternalNestAmount;
    }

    public Integer getMeatAmount() {
        return meatAmount;
    }

    public void setMeatAmount(Integer meatAmount) {
        this.meatAmount = meatAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getKittens() {
        return kittens;
    }

    public void setKittens(String kittens) {
        this.kittens = kittens;
    }
}
