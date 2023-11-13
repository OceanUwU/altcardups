package altcardups.upgrades.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface CalculateCardDamageOverride {
    public void calculateCardDamage(AbstractCard c, AbstractMonster m);
}