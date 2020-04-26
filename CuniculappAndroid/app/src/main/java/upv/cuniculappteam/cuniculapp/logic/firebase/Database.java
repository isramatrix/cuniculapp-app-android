package upv.cuniculappteam.cuniculapp.logic.firebase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Lists;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import upv.cuniculappteam.cuniculapp.model.utils.Identifiable;
import upv.cuniculappteam.cuniculapp.model.utils.Traceable;

public class Database
{
    private FirebaseFirestore firestore;

    Database(Context context)
    {
        firestore = FirebaseFirestore.getInstance();
    }

    public <T extends Serializable> Task<? extends T> fetch(String id, final Class<? extends T> type)
    {
        return firestore.collection(type.getSimpleName()).document(id).get().continueWith(
                (result) -> {
                    if (result.isSuccessful() && result.getResult() != null) {
                        return result.getResult().toObject(type);
                    } else if (result.getException() != null)
                        throw result.getException();

                    else throw new NullPointerException();
                }
        );
    }

    public <T extends Serializable> Task<List<T>> fetchAll(final Class<T> type)
    {
        return firestore.collection(type.getSimpleName()).get().continueWith(
                (result) -> {
                    if (result.isSuccessful() && result.getResult() != null) {
                        return Lists.transform(result.getResult().getDocuments(), (doc) -> doc.toObject(type));
                    } else if (result.getException() != null)
                        throw result.getException();

                    else throw new NullPointerException();
                }
        );
    }

    public <T extends Serializable> Task<List<T>> fetchWhere(String field, Object value, final Class<T> type)
    {
        return firestore.collection(type.getSimpleName()).whereEqualTo(field, value).get().continueWith(
                (result) -> {
                    if (result.isSuccessful() && result.getResult() != null) {
                        return Lists.transform(result.getResult().getDocuments(), (doc) -> doc.toObject(type));
                    } else if (result.getException() != null)
                        throw result.getException();

                    else throw new NullPointerException();
                }
        );
    }

    public <T extends Identifiable & Serializable> Task<Void> update(final T value, final Class<T> type)
    {
        return firestore.collection(type.getSimpleName()).document(value.getId()).set(value);
    }

    public <T extends Identifiable & Serializable> Task<Void> add(Class<T> type, T obj)
    {
        CollectionReference collection = firestore.collection(type.getSimpleName());
        return collection.add(obj).continueWithTask(
                (result) -> {
                    if (result.isSuccessful() && result.getResult() != null) {
                        obj.setId(result.getResult().getId());
                        return collection.document(obj.getId()).set(obj);
                    } else if (result.getException() != null)
                        throw result.getException();

                    else throw new NullPointerException();
                }
        );
    }

    public <T extends Identifiable & Serializable> Task<Void> delete(Class<T> type, T obj)
    {
        return firestore.collection(type.getSimpleName()).document(obj.getId()).delete();
    }
}
