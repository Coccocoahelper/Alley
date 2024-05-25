package me.emmy.alley.ffa.enums;

import lombok.Getter;

/**
 * Created by Emmy
 * Project: Alley
 * Date: 25/05/2024 - 14:25
 */

@Getter
public enum EnumFFAState {
    SPAWN("Spawn", "The player is in the safezone."),
    FIGHTING("Fighting", "The player is fighting outside safezone.")

    ;

    private final String name;
    private final String description;

    EnumFFAState(String name, String description) {
        this.name = name;
        this.description = description;
    }
}