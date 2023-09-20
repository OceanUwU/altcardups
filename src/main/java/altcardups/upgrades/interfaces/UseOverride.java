package altcardups.upgrades.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface UseOverride {
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m);
}