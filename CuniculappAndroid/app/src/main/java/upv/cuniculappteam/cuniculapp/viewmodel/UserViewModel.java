package upv.cuniculappteam.cuniculapp.viewmodel;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import upv.cuniculappteam.cuniculapp.logic.firebase.Firebase;
import upv.cuniculappteam.cuniculapp.model.User;

public class UserViewModel {

    public Task<List<User>> getUsers()
    {
        return Firebase.Database.fetchAll(User.class);
    }

    public Task<List<User>> getUsersByName(String userName)
    {
        return Firebase.Database.fetchWhere("name", userName,  User.class);
    }

    public Task<String> addFarm(User user)
    {
        return Firebase.Database.add(User.class, user).continueWith((t) -> null);
    }

    public Task<Void> updateFarm(User user)
    {
        return Firebase.Database.update(user, User.class);
    }

    public Task<Void> deleteFarms(Collection<User> users)
    {
        List<Task<?>> tasks = new ArrayList<>();
        for (User user : users) tasks.add(Firebase.Database.delete(User.class, user));
        return Tasks.whenAll(tasks);
    }
}
