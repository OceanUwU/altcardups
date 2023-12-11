package altcardups.upgrades.Ironclad;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend_Red extends AbstractAlternateUpgrade {
    public Defend_Red() {
        super(com.megacrit.cardcrawl.cards.red.Defend_Red.class);
    }
    
    public void up(AbstractCard c) {
        uBlock(c, 4);
        c.exhaust = true;
        uDesc(c);
    }
}