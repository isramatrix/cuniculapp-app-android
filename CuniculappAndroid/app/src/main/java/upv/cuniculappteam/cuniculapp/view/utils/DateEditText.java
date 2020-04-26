package upv.cuniculappteam.cuniculapp.view.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateEditText extends AppCompatEditText
{
    private static final String format = "dd/MM/yyyy";

    private static final Calendar myCalendar = Calendar.getInstance();

    private SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());

    private OnClickListener listener;

    private boolean cleared = true;

    private DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        setDate(myCalendar.getTime());
    };

    public DateEditText(Context context) {
        super(context);
    }

    public DateEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        super.setOnFocusChangeListener(this::openCalendarDialog);
        super.setOnClickListener((v) -> openCalendarDialog(v, true));
    }

    private void openCalendarDialog(View view, boolean focused)
    {
        if (!focused) return;

        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();

        if (listener != null) this.listener.onClick(view);
    }

    public void setDate(Date date)
    {
        cleared = false;
        myCalendar.setTime(date);
        setText(dateFormat.format(date));
    }

    public void clear()
    {
        cleared = true;
        myCalendar.setTime(new Date(System.currentTimeMillis()));
        setText("");
    }

    public Date getDate() { return cleared ? null : myCalendar.getTime(); }

    public void setFormat(SimpleDateFormat format) { this.dateFormat = format; }

    public SimpleDateFormat getFormat() { return dateFormat; }

    @Override
    public void setOnClickListener(@Nullable OnClickListener listener)
    {
        this.listener = listener;
    }
}
