package altcardups.upgrades.Ironclad;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Armaments extends AbstractAlternateUpgrade implements UseOverride {
    public Armaments() {
        super(com.megacrit.cardcrawl.cards.red.Armaments.class);
    }
    
    public void up(AbstractCard c) {
        uBlock(c, 2);
        setMagic(c, 2);
        uDesc(c);
    }
    
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, p, c.block));
        for (int i = 0; i < c.magicNumber; i++)
            atb(new ArmamentsAction(false));
    }
}