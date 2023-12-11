package altcardups.upgrades.Defect;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.defect.AnimateOrbAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeWithoutRemovingOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;

public class Dualcast extends AbstractAlternateUpgrade implements UseOverride {
    public Dualcast() {
        super(com.megacrit.cardcrawl.cards.blue.Dualcast.class);
    }
    
    public void up(AbstractCard c) {
        setMagic(c, 3);
        uDesc(c);
    }

    @Override
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        for(int i = 1; i < c.magicNumber; i++) {
            atb(new AnimateOrbAction(1));
            atb(new EvokeWithoutRemovingOrbAction(1));
        }
        atb(new AnimateOrbAction(1));
        atb(new EvokeOrbAction(1));
    }
}