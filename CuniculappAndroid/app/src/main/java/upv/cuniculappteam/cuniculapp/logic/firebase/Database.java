package upv.cuniculappteam.cuniculapp.logic.firebase;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class Database
{
    private FirebaseFirestore firestore;

    Database(Context context)
    {
        firestore = FirebaseFirestore.getInstance();
    }

    /// EJEMPLO DE USO: NO USAR ///

    public Task<DocumentSnapshot> fetch(String id)
    {
        return firestore.collection("users").document(id).get();
    }

    public <T extends Serializable> Task<DocumentReference> add(T obj)
    {
        return firestore.collection("users").add(obj);
    }

    public <T extends Serializable> Task<Void> add(T obj, String id)
    {
        return firestore.collection("users").document(id).set(obj);
    }

    public Task<Void> delete(String id)
    {
        return firestore.collection("users").document(id).delete();
    }
}
