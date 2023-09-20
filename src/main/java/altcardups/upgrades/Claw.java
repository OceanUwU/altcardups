package altcardups.upgrades;

import com.megacrit.cardcrawl.cards.AbstractCard;

public class Claw extends AbstractAlternateUpgrade {
    public Claw() {
        super(com.megacrit.cardcrawl.cards.blue.Claw.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, 1);
        uMagic(c, 1);
    }
}