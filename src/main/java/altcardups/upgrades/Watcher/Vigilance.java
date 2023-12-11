package altcardups.upgrades.Watcher;

import altcardups.upgrades.AbstractAlternateUpgrade;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Vigilance extends AbstractAlternateUpgrade {
    public Vigilance() {
        super(com.megacrit.cardcrawl.cards.purple.Vigilance.class);
    }
    
    public void up(AbstractCard c) {
        setCost(c, 1);
    }
}