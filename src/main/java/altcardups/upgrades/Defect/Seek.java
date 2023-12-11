package altcardups.upgrades.Defect;

import altcardups.upgrades.AbstractAlternateUpgrade;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Seek extends AbstractAlternateUpgrade {
    public Seek() {
        super(com.megacrit.cardcrawl.cards.blue.Seek.class);
    }

    public void up(AbstractCard c) {
        c.exhaust = false;
        uDesc(c);
    }
}