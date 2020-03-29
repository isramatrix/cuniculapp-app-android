package upv.cuniculappteam.cuniculapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.Arrays;

public class TraceableCreator<T extends Traceable> implements Parcelable.Creator<T>
{
    private Class<T> type;

    TraceableCreator(Class<T> type)
    {
        this.type = type;
    }

    @Override
    public T createFromParcel(Parcel parcel) {
        return parseParcel(parcel, type);
    }

    @Override
    public T[] newArray(int size) {
        return (T[]) new Object[size];
    }

    private static <T extends Traceable> T parseParcel(Parcel parcel, Class<T> type)
    {
        return new Gson().fromJson(parcel.readString(), type);
    }
}
