package me.emmy.alley.match.impl;

import lombok.Getter;
import me.emmy.alley.arena.Arena;
import me.emmy.alley.kit.Kit;
import me.emmy.alley.match.AbstractMatch;
import me.emmy.alley.match.player.GameParticipant;
import me.emmy.alley.match.player.impl.MatchGamePlayerImpl;
import me.emmy.alley.queue.Queue;

import java.util.Arrays;
import java.util.List;

@Getter
public class RegularMatchImpl extends AbstractMatch {

    private final GameParticipant<MatchGamePlayerImpl> participantA;
    private final GameParticipant<MatchGamePlayerImpl> participantB;

    /**
     * Constructor for the RegularMatchImpl class.
     *
     * @param kit          The kit of the match.
     * @param arena        The arena of the match.
     * @param participantA The first participant.
     * @param participantB The second participant.
     */
    public RegularMatchImpl(Queue queue, Kit kit, Arena arena, GameParticipant<MatchGamePlayerImpl> participantA, GameParticipant<MatchGamePlayerImpl> participantB) {
        super(queue, kit, arena);
        this.participantA = participantA;
        this.participantB = participantB;
    }

    @Override
    public List<GameParticipant<MatchGamePlayerImpl>> getParticipants() {
        return Arrays.asList(getParticipantA(), getParticipantB());
    }

    @Override
    public boolean canEndRound() {
        return participantA.getPlayer().isDead() || participantB.getPlayer().isDead();
    }

    @Override
    public boolean canEndMatch() {
        return true;
    }
}