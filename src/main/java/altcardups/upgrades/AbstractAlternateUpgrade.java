package altcardups.upgrades;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import static altcardups.AltUpsMod.makeID;

public abstract class AbstractAlternateUpgrade {
    public Class<? extends AbstractCard> cardClass;
    public String ID;
    private CardStrings cardStrings;

    public AbstractAlternateUpgrade(Class<? extends AbstractCard> cardClass) {
        this.cardClass = cardClass;
        ID = makeID(getClass().getSimpleName());
    }

    public abstract void up(AbstractCard c);

    protected void uDamage(AbstractCard c, int by) {
        ReflectionHacks.privateMethod(AbstractCard.class, "upgradeDamage", int.class).invoke(c, by);
    }

    protected void uBlock(AbstractCard c, int by) {
        ReflectionHacks.privateMethod(AbstractCard.class, "upgradeBlock", int.class).invoke(c, by);
    }

    protected void uMagic(AbstractCard c, int by) {
        ReflectionHacks.privateMethod(AbstractCard.class, "upgradeMagicNumber", int.class).invoke(c, by);
    }

    protected void uDesc(AbstractCard c) {
        c.rawDescription = loadStrings().DESCRIPTION;
        c.initializeDescription();
    }
    
    protected void atb(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    protected CardStrings loadStrings() {
        if (cardStrings == null)
            cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        return cardStrings;
    }
}