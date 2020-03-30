package upv.cuniculappteam.cuniculapp.logic.firebase;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

public class Database
{
    private FirebaseFirestore firestore;

    Database(Context context)
    {
        firestore = FirebaseFirestore.getInstance();
    }

    public <T, S extends T> Task<S> fetch(String id, final Class<? extends T> type)
    {
        return firestore.collection("users").document(id).get().continueWith(
                (result) -> {
                    if (result.isSuccessful() && result.getResult() != null) {
                        return (S) result.getResult().toObject(type);
                    } else if (result.getException() != null)
                        throw result.getException();

                    else throw new NullPointerException();
                }
        );
    }

    public <T extends Serializable> Task<Void> add(T obj)
    {
        return firestore.collection("users").add(obj).continueWith(task -> null);
    }

    public <T extends Serializable> Task<Void> add(String id, T obj)
    {
        return firestore.collection("users").document(id).set(obj);
    }

    public Task<Void> delete(String id)
    {
        return firestore.collection("users").document(id).delete();
    }
}
