package dev.revere.alley.party.enums;

import lombok.Getter;

/**
 * @author Emmy
 * @project Alley
 * @date 24/05/2024 - 22:57
 */
@Getter
public enum EnumPartyState {
    PRIVATE("Private", "Only invited players can join"),
    PUBLIC("Public", "Everyone can join"),
    LOCKED("Locked", "No one can join until unlocked");

    private final String name;
    private final String description;

    /**
     * Constructor for the EnumPartyState
     *
     * @param name        The name of the party state
     * @param description The description of the party state
     */
    EnumPartyState(String name, String description) {
        this.name = name;
        this.description = description;
    }
}