package altcardups.upgrades.Defect;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Zap extends AbstractAlternateUpgrade {
    public Zap() {
        super(com.megacrit.cardcrawl.cards.blue.Zap.class);
    }
    
    public void up(AbstractCard c) {
        uMagic(c, 1);
    }
}