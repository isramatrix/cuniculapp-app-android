package upv.cuniculappteam.cuniculapp.view.utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.time.LocalDate;

import upv.cuniculappteam.cuniculapp.R;

public class LoadingView extends ConstraintLayout
{
    private static LoadingView currentLoading;

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static LoadingView show(Activity activity)
    {
        if (currentLoading != null) return currentLoading;

        return currentLoading = activity.getLayoutInflater().inflate(R.layout.dialog_loading,
                (ViewGroup) activity.findViewById(android.R.id.content).getRootView()
        ).findViewById(R.id.dialog_loading);
    }

    public static void hide(Object... params)
    {
        if (currentLoading != null)
            ((ViewGroup) currentLoading.getParent()).removeView(currentLoading);

        currentLoading = null;
    }
}
