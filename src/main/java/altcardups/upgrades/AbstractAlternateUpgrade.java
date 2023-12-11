package altcardups.upgrades;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static altcardups.AltUpsMod.makeID;

public abstract class AbstractAlternateUpgrade {
    public static boolean ignorePatches;

    public Class<? extends AbstractCard> cardClass;
    public String ID;
    private CardStrings cardStrings;
    private CardStrings originalCardStrings;

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

    protected void setDamage(AbstractCard c, int to) {
        c.baseDamage = to;
    }

    protected void setBlock(AbstractCard c, int to) {
        c.baseBlock = to;
    }

    protected void setMagic(AbstractCard c, int to) {
        c.baseMagicNumber = c.magicNumber = to;
    }

    protected void uDesc(AbstractCard c) {
        setDesc(c, loadStrings().DESCRIPTION);
    }

    protected void setDesc(AbstractCard c, String desc) {
        c.rawDescription = desc;
        c.initializeDescription();
    }

    protected void setCost(AbstractCard c, int to) {
        ReflectionHacks.privateMethod(AbstractCard.class, "upgradeBaseCost", int.class).invoke(c, to);
    }
    
    protected void atb(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }
    protected void dmg(AbstractCard c, AbstractMonster m, AbstractGameAction.AttackEffect e){
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, c.damage, DamageInfo.DamageType.NORMAL), e));
    }
    protected void blck(AbstractCard c){
        atb(new GainBlockAction(AbstractDungeon.player, c.block));
    }

    protected void ignorePatch() {
        ignorePatches = true;
    }

    protected CardStrings loadStrings() {
        if (cardStrings == null)
            cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        return cardStrings;
    }

    protected CardStrings origStrings() {
        if (originalCardStrings == null)
            originalCardStrings = ReflectionHacks.getPrivateStatic(cardClass, "cardStrings");
        return originalCardStrings;
    }
}