package altcardups.upgrades.Ironclad;

import altcardups.upgrades.AbstractAlternateUpgrade;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class Bash extends AbstractAlternateUpgrade {
    public Bash() {
        super(com.megacrit.cardcrawl.cards.red.Bash.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, 4);
    }
}