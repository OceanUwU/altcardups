package altcardups.upgrades.Silent;

import altcardups.actions.DiscardToDrawAction;
import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Survivor extends AbstractAlternateUpgrade implements UseOverride {
    public Survivor() {
        super(com.megacrit.cardcrawl.cards.green.Survivor.class);
    }
    
    public void up(AbstractCard c) {
        uDesc(c);
    }

    @Override
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        blck(c);
        atb(new DiscardToDrawAction(1, true, false));
    }
}