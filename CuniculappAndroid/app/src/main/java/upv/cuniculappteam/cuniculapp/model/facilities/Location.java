package upv.cuniculappteam.cuniculapp.model.facilities;

import upv.cuniculappteam.cuniculapp.model.utils.Traceable;
import upv.cuniculappteam.cuniculapp.model.utils.TraceableCreator;

public class Location extends Traceable
{
    public static final Creator<Location> CREATOR = new TraceableCreator<>(Location.class);

    private Double latitude;

    private Double longitude;

    public Location() { }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
