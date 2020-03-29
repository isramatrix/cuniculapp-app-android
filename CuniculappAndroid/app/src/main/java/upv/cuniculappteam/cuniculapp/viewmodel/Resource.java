package upv.cuniculappteam.cuniculapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

class Resource<T> extends MutableLiveData<T>
{
    private DataObserver<? super T> observer;

    private boolean alreadyFailed = false;

    private Exception exception;

    void setError(@NonNull Exception exception)
    {
        if (observer != null)
            observer.onFailed(this.exception = exception);

        alreadyFailed = true;
    }

    public void observe(@NonNull LifecycleOwner owner, @NonNull DataObserver<? super T> observer) {
        super.observe(owner, this.observer = observer);
        if (alreadyFailed && exception != null) observer.onFailed(exception);
    }

    interface DataObserver<S> extends Observer<S> {
        void onChanged(S data);
        void onFailed(Exception e);
    }
}
