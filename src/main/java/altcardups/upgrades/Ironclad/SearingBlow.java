package altcardups.upgrades.Ironclad;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SearingBlow extends AbstractAlternateUpgrade implements UseOverride {
    public SearingBlow() {
        super(com.megacrit.cardcrawl.cards.red.SearingBlow.class);
    }
    
    public void up(AbstractCard c) {
        if(c.timesUpgraded == 1){
            setMagic(c, 4);
            uDamage(c, -8);
        } else if (c.timesUpgraded % 2 == 0) {
            uDamage(c, 1);
        } else {
            uMagic(c, 1);
        }
        uDesc(c);
        c.name = CardCrawlGame.languagePack.getCardStrings("Searing Blow").NAME + "+" + c.timesUpgraded;
        ReflectionHacks.privateMethod(AbstractCard.class, "initializeTitle").invoke(c);
    }
    
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < c.magicNumber; i++)
            dmg(c, m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }
}