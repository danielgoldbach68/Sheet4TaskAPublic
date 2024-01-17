package edu.kit.kastel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AdventCalendar<T extends Copyable<T>> implements CountdownCalendar<T> {

    private static final int DOORS_PER_LINE = 4;
    private static final String DOOR_REPRESENTATION_FORMAT = "[%s]";
    private static final String CANDY_REPRESENTATION_FORMAT = "%dx%s";
    private static final String EMPTY_DOOR_CONTENT = "   ";

    private int currentDay;
    private final List<T> backup;
    private List<T> candies;
    private final int maxDays;


    /**
     * {@code false} represents a closed door, {@code true} an opened door.
     * All doors are closed by default.
     */
    private boolean[] doors;
    
    public AdventCalendar(List<T> candies) {
        this.backup = new ArrayList<>(candies);
        reset();
        this.maxDays = candies.size();
        this.doors = new boolean[this.maxDays];
        // the initial day is the day before december 1st
        this.currentDay = 0;

    }
    
    /**
     * Returns the current day.
     * 
     * @return the current day
     */
    @Override
    public int getDay() {
        return this.currentDay;
    }

    @Override
    public void nextDay() {
        if (this.currentDay >= this.maxDays) {
            throw new IllegalStateException("");
        } else {
            this.currentDay++;
        }
    }

    @Override
    public void nextDays(int days) {
       if (days < 1 || this.currentDay + days > maxDays) {
        throw new IllegalArgumentException("");
       } else {
        this.currentDay += days;
       }
    }

    @Override
    public int size() {
        return this.maxDays;
    }

    @Override
    public boolean isDoorOpen(int number) {
        if (number < 1 || number > this.maxDays) {
            throw new IllegalArgumentException("");
        }
        return this.doors[number - 1];
    }

    @Override
    public T openDoor(int number) {
        if (number < 1 || number > this.currentDay) {
            throw new IllegalArgumentException("");
        } else if (isDoorOpen(number)) {
            throw new IllegalStateException("");
        }
        this.doors[number - 1] = true;
        return this.candies.get(number - 1);
    }

    @Override
    public List<T> openDoors(List<Integer> numbers) {
        List<T> openedCandies = new ArrayList<>();
        for (Integer number : numbers) {

            //try {
        openedCandies.add(openDoor(number)); //TODO wenn artemis fehler uncomment line below and oabeove and fix formatting
            //} catch (IllegalArgumentException | IllegalStateException e) {
            //    continue;
            //}
            
        }

        return openedCandies;
    }

    @Override
    public int numberOfUnopenedDoors() {
       int count = 0;
       for (int i = 0; i < this.currentDay; i++) {
        if (!this.doors[i]) {
            count++;
        }
    }
    return count;
    }

    @Override
    public void reset() {
        this.currentDay = 0;
        this.doors = new boolean[maxDays];
        this.candies = copyCandyList(this.backup);
    }

    private List<T> copyCandyList(List<T> toCopy) {
        List<T> copy = new ArrayList<>();
        for (T candy: toCopy) {
            copy.add(candy.copy());
        }
        return copy;
    }

    @Override
    public String toString() {
        StringJoiner lines = new StringJoiner(System.lineSeparator());
        StringBuilder currentLine = new StringBuilder();
        for (int i = 0; i < maxDays; i++) {
            if (this.doors[i]) {
                currentLine.append(DOOR_REPRESENTATION_FORMAT.formatted(EMPTY_DOOR_CONTENT));
            } else {
                currentLine.append(DOOR_REPRESENTATION_FORMAT.formatted(CANDY_REPRESENTATION_FORMAT
                        .formatted(this.candies.get(i).toString())));
            }
            
            if (i % DOORS_PER_LINE == DOORS_PER_LINE - 1) {
                lines.add(currentLine.toString());
                currentLine.setLength(0);
            }
        }
        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString());
        }
        return lines.toString();
    }

}
