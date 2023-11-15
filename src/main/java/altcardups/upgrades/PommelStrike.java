package altcardups.upgrades;

import com.megacrit.cardcrawl.cards.AbstractCard;

public class PommelStrike extends AbstractAlternateUpgrade {
    public PommelStrike() {
        super(com.megacrit.cardcrawl.cards.red.PommelStrike.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, 4);
    }
}