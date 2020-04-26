package upv.cuniculappteam.cuniculapp.logic.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Sorts
{
    public static <T extends Comparable<T>> List<T> sort(List<T> list)
    {
        List<T> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return sortedList;
    }
}
