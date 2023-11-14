package altcardups.upgrades;

import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PommelStrike extends AbstractAlternateUpgrade implements UseOverride {
    public PommelStrike() {
        super(com.megacrit.cardcrawl.cards.red.PommelStrike.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, 3);
        uDesc(c);
    }

    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(m, new DamageInfo(p, c.damage, c.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        atb(new DrawCardAction(1));
    }
}