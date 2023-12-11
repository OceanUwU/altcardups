package altcardups.upgrades.Watcher;

import altcardups.upgrades.AbstractAlternateUpgrade;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Tranquility extends AbstractAlternateUpgrade {
    public Tranquility() {
        super(com.megacrit.cardcrawl.cards.purple.Tranquility.class);
    }

    public void up(AbstractCard c) {
        c.exhaust = false;
        uDesc(c);
    }
}