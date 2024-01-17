/*
 * Copyright (c) 2023, KASTEL. All rights reserved.
 */

package edu.kit.kastel;

import java.util.List;

/**
 * Represents a calendar for a specific event or occasion. Helps to count down a set number of days with a very special
 * treat per day.
 * @param <T> the type of treats contained in the calendar
 * @author Programmieren-Team
 */
public interface CountdownCalendar<T extends Copyable<T>> {

    /**
     * Returns the current day.
     * @return the current day
     */
    int getDay();

    /**
     * Attempts to increase the current day by one day. The day can only be increased if the current day is lower than
     * the total number of doors.
     * @throws IllegalStateException if the current day is greater than or equal to the total number of doors
     */
    void nextDay();

    /**
     * Attempts to increase the current day by the given days. The day can only be increased if the resulting day will be
     * lower than or equal to the total number of doors.
     * @param days the number of days
     * @throws IllegalArgumentException if days is less than or equal to 0, or the resulting day will be greater than the total 
     *     number of doors
     */
    void nextDays(int days);

    /**
     * Returns the total number of doors in this calendar.
     * @return the number of doors.
     */
    int size();

    /**
     * Determines if the door of a given day is open.
     * @param number the number/day of the corresponding door
     * @return {@code true} if the door is open, {@code false} otherwise
     * @throws IllegalArgumentException if the given number is less than 1 or greater than the total number of doors
     */
    boolean isDoorOpen(int number);

    /**
     * Attempts to open the door with the given number. The door can only be opened if the given number is less than or
     * equal to the current day and if the door has not yet been opened before.
     * @param number the number of the corresponding door
     * @return the {@link T treat} behind the door
     * @throws IllegalArgumentException if the given number is less than 1 or greater than the current day
     * @throws IllegalStateException if the door has already been opened
     */
    T openDoor(int number);

    /**
     * Attempts to open the doors with the given numbers. The door can only be opened if the given numbers are less than or
     * equal to the current day and if the doors has not yet been opened before.
     * @param numbers the numbers of the corresponding doors
     * @return the {@link T treat} behind the doors that could be opened
     * @throws IllegalArgumentException if any of the given numbers is less than 1 or greater than the current day
     * @throws IllegalStateException if any of the given doors has already been opened
     */
    List<T> openDoors(List<Integer> numbers);

    /**
     * Returns the number of unopened doors that may be opened at the current day.
     * @return the number of unopened doors that may be opened at the current day
     */
    int numberOfUnopenedDoors();

    /**
     * Resets this calendar. After the reset all sweets will be restored, all doors will be closed and the current day will
     * be the day before any door can be opened.
     */
    void reset();

    /**
     * Returns a string representation of this calendar.
     * @return a string representation of this calendar
     */
    String toString();

}
