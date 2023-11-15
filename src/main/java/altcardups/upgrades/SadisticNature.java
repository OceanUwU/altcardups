package altcardups.upgrades;

import com.megacrit.cardcrawl.cards.AbstractCard;

public class SadisticNature extends AbstractAlternateUpgrade {
    public SadisticNature() {
        super(com.megacrit.cardcrawl.cards.colorless.SadisticNature.class);
    }
    
    public void up(AbstractCard c) {
        c.isInnate = true;
        uDesc(c);
    }
}