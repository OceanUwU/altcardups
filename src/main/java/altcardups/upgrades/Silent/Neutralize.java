package altcardups.upgrades.Silent;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Neutralize extends AbstractAlternateUpgrade {
    public Neutralize() {
        super(com.megacrit.cardcrawl.cards.green.Neutralize.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, 3);
    }
}