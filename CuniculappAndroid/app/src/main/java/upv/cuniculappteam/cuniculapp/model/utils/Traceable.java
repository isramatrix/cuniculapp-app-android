package upv.cuniculappteam.cuniculapp.model.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.collect.TreeRangeSet;
import com.google.gson.Gson;

import java.io.Serializable;

public abstract class Traceable extends Identifiable implements Serializable, Parcelable
{
    @Override
    public final int describeContents() { return 0; }

    @Override
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(new Gson().toJson(this));
    }
}
