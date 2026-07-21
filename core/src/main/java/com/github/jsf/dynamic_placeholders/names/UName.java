package com.github.jsf.dynamic_placeholders.names;

import com.github.utilities.validators.Preconditions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * An UName (Unique Name) is a unique identifier for any components inside
 * the entire library, including any third-party components.
 * <p>
 * The UName is used to identify components in the library and to avoid
 * conflicts between different components.
 * The {@code id} and {@code aliases} are case-sensitive and the {@code id}
 * must be unique, instead the {@code aliases} can be null or empty.
 * If two components have the same {@code id}, a {@link DuplicateNameException} is thrown.
 * <p>
 * The convention is to use the own application name as a prefix for own components
 * inside the {@code id} to prevent conflicts with others third-party components.
 * For example, if the application name is "MyApp", a component can be named "MyApp_ComponentName".
 */
public class UName {
    private final String id;
    private final String[] aliases;
    private final Set<String> aliasesSet;

    /**
     * Create a new Unique Identifier instance.
     * <p>
     * If {@code aliases} are null, an empty array is used.
     *
     * @param id      The name to assign
     * @param aliases The aliases of the id; {@code They can be null or empty}.
     * @throws IllegalArgumentException if the {@code name} is null or empty.
     */
    public UName(String id, String... aliases) {
        Preconditions.parameterNotNull(id, "name");
        this.id = id;
        this.aliases = Preconditions.simpleNotNull(aliases, new String[0]);
        this.aliasesSet = new HashSet<>(Arrays.asList(aliases));
    }

    public String getID() {
        return id;
    }

    public String[] getAliases() {
        return aliases;
    }

    public Set<String> getAliasesAsSet() {
        return aliasesSet;
    }

    /**
     * Checks if the {@code str} is assignable to this {@link UName}
     * in any way. If the {@code str} is equal to the {@link #id} or
     * one of the {@link #aliases}, the method returns {@code true}.
     *
     * @param str The string to check.
     * @return If the {@code str} is equal to the {@link #id} or
     * one of the {@link #aliases}, the method returns {@code true}.
     */
    public boolean is(String str) {
        return getID().equals(str) || getAliasesAsSet().contains(str);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof UName UName) {
            return getID().equals(UName.getID())
                    && Arrays.equals(getAliases(), UName.getAliases());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), Arrays.hashCode(getAliases()));
    }

    @Override
    public String toString() {
        return "UID[Name: \"" + id + "\" | Aliases: " + String.join(", ", aliases) + "]";
    }
}
