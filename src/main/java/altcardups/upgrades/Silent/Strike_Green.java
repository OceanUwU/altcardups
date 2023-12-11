package altcardups.upgrades.Silent;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_Green extends AbstractAlternateUpgrade implements UseOverride {
    public Strike_Green() {
        super(com.megacrit.cardcrawl.cards.green.Strike_Green.class);
    }
    
    public void up(AbstractCard c) {
        uDamage(c, -1);
        setBlock(c, 4);
        uDesc(c);
    }

    @Override
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        dmg(c, m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        blck(c);
    }
}