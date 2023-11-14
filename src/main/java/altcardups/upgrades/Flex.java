package altcardups.upgrades;

import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Flex extends AbstractAlternateUpgrade implements UseOverride {
    public Flex() {
        super(com.megacrit.cardcrawl.cards.red.Flex.class);
    }

    public void up(AbstractCard c) {
        uDesc(c);
    }

    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new StrengthPower(p, c.magicNumber)));
        atb(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 1)));
    }
}