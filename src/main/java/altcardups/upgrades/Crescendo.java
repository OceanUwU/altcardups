package altcardups.upgrades;

import com.megacrit.cardcrawl.cards.AbstractCard;

public class Crescendo extends AbstractAlternateUpgrade {
    public Crescendo() {
        super(com.megacrit.cardcrawl.cards.purple.Crescendo.class);
    }

    public void up(AbstractCard c) {
        c.exhaust = false;
        uDesc(c);
    }
}