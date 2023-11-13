package altcardups.upgrades.interfaces;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;

public interface ApplyPowersOverride {
    public void applyPowers(AbstractCard c);

    default void applyPowersToBlock(AbstractCard c) {
        ReflectionHacks.privateMethod(AbstractCard.class, "applyPowersToBlock").invoke(c);
    }
}