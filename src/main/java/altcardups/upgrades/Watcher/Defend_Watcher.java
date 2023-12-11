package altcardups.upgrades.Watcher;

import altcardups.upgrades.AbstractAlternateUpgrade;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Defend_Watcher extends AbstractAlternateUpgrade {
    public Defend_Watcher() {
        super(com.megacrit.cardcrawl.cards.purple.Defend_Watcher.class);
    }
    
    public void up(AbstractCard c) {
        uBlock(c, 1);
        c.retain = true;
        uDesc(c);
    }
}