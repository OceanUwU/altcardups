package altcardups.upgrades.Ironclad;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShrugItOff extends AbstractAlternateUpgrade implements UseOverride {
    public ShrugItOff() {
        super(com.megacrit.cardcrawl.cards.red.ShrugItOff.class);
    }
    
    public void up(AbstractCard c) {
        uDesc(c);
    }

    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        blck(c);
        atb(new DrawCardAction(2));
    }
}