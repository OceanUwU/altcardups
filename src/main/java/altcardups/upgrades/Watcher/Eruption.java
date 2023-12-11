package altcardups.upgrades.Watcher;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class Eruption extends AbstractAlternateUpgrade {
    public Eruption() {
        super(com.megacrit.cardcrawl.cards.purple.Eruption.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, 4);
    }
}