package altcardups.upgrades;

import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_Red extends AbstractAlternateUpgrade implements UseOverride {
    public Strike_Red() {
        super(com.megacrit.cardcrawl.cards.red.Strike_Red.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, -2);
        uDesc(c);
    }

    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; i++)
            atb(new DamageAction(m, new DamageInfo((AbstractCreature)p, c.damage, c.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}