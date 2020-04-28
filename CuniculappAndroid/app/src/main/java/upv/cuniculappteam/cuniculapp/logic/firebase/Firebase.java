package upv.cuniculappteam.cuniculapp.logic.firebase;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;

public abstract class Firebase
{
    public static Auth Auth;

    public static Database Database;

    public static Services Services;

    /**
     * Inicializa los servicios y las funcionalides de Firebase.
     *
     * @param context El contexto de inicialización.
     */
    public static void initialize(Context context)
    {
        Auth = new Auth(context);
        Database = new Database(context);
        Services = new Services(context);
    }

    /**
     * Comprueba si los servicios y funcionalidades de Firebase
     * han sido inicializadas o no.
     *
     * @return <code>true</code> si han sido inicializadas, en caso contrario <code>false</code>.
     */
    public static boolean isInitialized()
    {
        return Auth != null && Database != null && Services != null;
    }

}
