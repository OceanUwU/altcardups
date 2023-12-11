package altcardups.upgrades.Watcher;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class Strike_Purple extends AbstractAlternateUpgrade implements UseOverride {
    public Strike_Purple() {
        super(com.megacrit.cardcrawl.cards.purple.Strike_Purple.class);
    }
    
    public void up(AbstractCard c) {
        setMagic(c, 3);
        uDesc(c);
    }

    @Override
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        dmg(c, m,  AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new ApplyPowerAction(p, p, new VigorPower(p, c.magicNumber)));
    }
}