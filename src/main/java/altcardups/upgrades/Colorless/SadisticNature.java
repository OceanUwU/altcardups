package altcardups.upgrades.Colorless;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SadisticNature extends AbstractAlternateUpgrade {
    public SadisticNature() {
        super(com.megacrit.cardcrawl.cards.colorless.SadisticNature.class);
    }
    
    public void up(AbstractCard c) {
        c.isInnate = true;
        uDesc(c);
    }
}