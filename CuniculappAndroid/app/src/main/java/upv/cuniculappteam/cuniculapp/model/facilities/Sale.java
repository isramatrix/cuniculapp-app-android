package upv.cuniculappteam.cuniculapp.model.facilities;

import java.util.Date;

import upv.cuniculappteam.cuniculapp.model.Cycle;
import upv.cuniculappteam.cuniculapp.model.Labor;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Sale extends Traceable
{
    public static final Creator<Sale> CREATOR = new TraceableCreator<>(Sale.class);

    private Integer id;

    private Date date;

    private Labor labor;

    private Integer salePrize;

    private Integer averageWeight;

    private Integer feedCost;

    private Cycle cycle;

    public Sale() { }

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

    public Integer getSalePrize() {
        return salePrize;
    }

    public void setSalePrize(Integer salePrize) {
        this.salePrize = salePrize;
    }

    public Integer getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(Integer averageWeight) {
        this.averageWeight = averageWeight;
    }

    public Integer getFeedCost() {
        return feedCost;
    }

    public void setFeedCost(Integer feedCost) {
        this.feedCost = feedCost;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
}
