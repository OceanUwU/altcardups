package altcardups.upgrades;

import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShrugItOff extends AbstractAlternateUpgrade implements UseOverride {
    public ShrugItOff() {
        super(com.megacrit.cardcrawl.cards.red.ShrugItOff.class);
    }
    
    public void up(AbstractCard c) {
        setMagic(c, 2);
        uDesc(c);
    }

    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, c.block));
        atb(new DrawCardAction(c.magicNumber));
    }
}