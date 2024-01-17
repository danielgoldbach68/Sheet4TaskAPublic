/*
 * Copyright (c) 2023, KASTEL. All rights reserved.
 */

package edu.kit.kastel;

/**
 * Interface for classes that can be copied.
 * @param <T> the type of the class that is copied
 * @author Programmieren-Team
 */
public interface Copyable<T> {

    /**
     * Creates a copy of the object.
     * @return the copy
     */
    T copy();
}
