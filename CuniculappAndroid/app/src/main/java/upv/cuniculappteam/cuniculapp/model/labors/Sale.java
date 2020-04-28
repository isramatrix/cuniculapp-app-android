package upv.cuniculappteam.cuniculapp.model.labors;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Sale extends Traceable
{
    public static final Creator<Sale> CREATOR = new TraceableCreator<>(Sale.class);

    private Date date;

    private String labor;

    private float salePrize;

    private float averageWeight;

    private float feedCost;

    private Integer sold;

    private String cycle;

    public Sale() { }

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

    public float getSalePrize() {
        return salePrize;
    }

    public void setSalePrize(float salePrize) {
        this.salePrize = salePrize;
    }

    public float getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(float averageWeight) {
        this.averageWeight = averageWeight;
    }

    public float getFeedCost() {
        return feedCost;
    }

    public void setFeedCost(float feedCost) {
        this.feedCost = feedCost;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getSold() { return sold; }

    public void setSold(Integer sold) { this.sold = sold; }
}
