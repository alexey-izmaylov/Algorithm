package io.example.generics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

public class VarianceJavaTest {

  <T extends Comparable<? super T>> T useSuper(Collection<T> collection) {
    return collection.iterator().next();
  }

  <T extends Comparable<? extends T>> T useExtends(Collection<? extends T> collection) {
    return collection.iterator().next();
  }

  @Test
  public void contravariance() {
    var calendars = new ArrayList<Calendar>();
    calendars.add(new GregorianCalendar());
    useSuper(calendars);

    var gregorianCalendars = new ArrayList<GregorianCalendar>();
    gregorianCalendars.add(new GregorianCalendar());
    useSuper(gregorianCalendars);
  }

  @Test
  public void covariance() {
    var myCalendars = new ArrayList<MyCalendar<Calendar>>();
    myCalendars.add(new MyCalendar<>(new GregorianCalendar()));
    useExtends(myCalendars);

    var myGregorianCalendars = new ArrayList<MyGregorianCalendar>();
    myGregorianCalendars.add(new MyGregorianCalendar(new GregorianCalendar()));
    useExtends(myGregorianCalendars);

    var calendars = new ArrayList<Calendar>();
    calendars.add(new GregorianCalendar());
    useSuper(calendars);
    useExtends(calendars);
  }

  static class MyCalendar<T extends Calendar> implements Comparable<T> {

    private final T calendar;

    MyCalendar(T calendar) {
      this.calendar = calendar;
    }

    @Override
    public int compareTo(@NotNull T o) {
      return calendar.compareTo(o);
    }
  }

  static class MyGregorianCalendar extends MyCalendar<GregorianCalendar> {

    MyGregorianCalendar(GregorianCalendar calendar) {
      super(calendar);
    }
  }
}