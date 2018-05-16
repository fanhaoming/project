package com.project.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ListUtil
 * @Description TODO
 * @Author fanhaoming
 * @Date 2018/5/14  15:04
 * @Version 1.0
 **/
public class ListUtil {

    public static <T> T find(List<T> lists, ListPredicate<T> predicate) {
        T result = null;
        if (lists == null || lists.size() == 0)
            return result;

        for (T t : lists) {
            if (predicate.check(t)) {
                result = t;
                break;
            }
        }

        return result;
    }

    public static <T> Boolean isExists(List<T> lists, ListPredicate<T> predicate) {

        Boolean result = false;
        if (lists == null || lists.size() == 0)
            return result;

        for (T t : lists) {
            if (predicate.check(t)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public static <T> List<T> findAll(List<T> lists, ListPredicate<T> predicate) {
        List<T> results = new ArrayList<T>();
        if (lists == null || lists.size() == 0)
            return results;

        for (T t : lists) {
            if (predicate.check(t)) {
                results.add(t);
            }
        }

        return results;
    }

    public static <SourceT, DestinationT> List<DestinationT> select(List<SourceT> lists, ListSelectAction<SourceT, DestinationT> action) {
        List<DestinationT> results = new ArrayList<DestinationT>();
        for (SourceT t : lists) {
            results.add(action.get(t));
        }
        return results;
    }

    public static <SourceT, DestinationT> List<DestinationT> group(List<SourceT> lists, ListSelectAction<SourceT, DestinationT> action) {
        List<DestinationT> results = new ArrayList<DestinationT>();
        for (SourceT t : lists) {
            DestinationT r = action.get(t);
            if (!results.contains(r)) {
                results.add(action.get(t));
            }
        }
        return results;
    }

    public static <T> List<T> page(List<T> lists, int page, int pageSize) {
        List<T> results = new ArrayList<T>();

        int startIndex = (page - 1) * pageSize;
        int count = page * pageSize;

        if (lists.size() < count) {
            if (lists.size() >= startIndex)
                results = lists.subList(startIndex, startIndex + lists.size() - startIndex);
        } else
            results = lists.subList(startIndex, startIndex + pageSize);

        return results;
    }
}
