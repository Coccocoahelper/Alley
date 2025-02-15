package dev.revere.alley.profile.cosmetic.impl.soundeffect;

import lombok.Getter;
import dev.revere.alley.profile.cosmetic.impl.soundeffect.impl.NoneSoundEffect;
import dev.revere.alley.profile.cosmetic.impl.soundeffect.impl.StepSoundEffect;
import dev.revere.alley.profile.cosmetic.interfaces.ICosmeticRepository;
import dev.revere.alley.profile.cosmetic.repository.BaseCosmeticRepository;

/**
 * @author Remi
 * @project Alley
 * @date 01/06/2024
 */
@Getter
public class SoundEffectRepository extends BaseCosmeticRepository<AbstractSoundEffect> implements ICosmeticRepository<AbstractSoundEffect> {
    /**
     * Constructor to register all kill effects
     */
    public SoundEffectRepository() {
        registerCosmetic(NoneSoundEffect.class);
        registerCosmetic(StepSoundEffect.class);
    }
}