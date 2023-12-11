package altcardups.upgrades.Defect;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;

public class Defend_Blue extends AbstractAlternateUpgrade implements UseOverride {
    public Defend_Blue() {
        super(com.megacrit.cardcrawl.cards.blue.Defend_Blue.class);
    }
    
    public void up(AbstractCard c) {
        uBlock(c, -1);
        setMagic(c, 1);
        uDesc(c);
    }

    @Override
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        blck(c);
        atb(new ChannelAction(new Frost()));
    }
}