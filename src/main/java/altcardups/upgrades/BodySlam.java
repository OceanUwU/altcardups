package altcardups.upgrades;

import altcardups.upgrades.interfaces.ApplyPowersOverride;
import altcardups.upgrades.interfaces.CalculateCardDamageOverride;
import altcardups.upgrades.interfaces.OnMoveToDiscardOverride;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BodySlam extends AbstractAlternateUpgrade implements UseOverride, ApplyPowersOverride, CalculateCardDamageOverride, OnMoveToDiscardOverride {
    public BodySlam() {
        super(com.megacrit.cardcrawl.cards.red.BodySlam.class);
    }
    
    public void up(AbstractCard c) {
        setBlock(c, 2);
        uDesc(c);
    }
    
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, p, c.block));
        ignorePatch();
        c.calculateCardDamage(m);
        atb(new DamageAction(m, new DamageInfo(p, c.damage, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
        setDesc(c, loadStrings().DESCRIPTION);
    }

    public void applyPowers(AbstractCard c) {
        applyPowersToBlock(c);
        AbstractDungeon.player.currentBlock += c.block;
        ignorePatch();
        c.applyPowers();
        AbstractDungeon.player.currentBlock -= c.block;
        setDesc(c, loadStrings().DESCRIPTION + origStrings().UPGRADE_DESCRIPTION);
    }

    public void calculateCardDamage(AbstractCard c, AbstractMonster m) {
        ignorePatch();
        c.calculateCardDamage(m);
        setDesc(c, loadStrings().DESCRIPTION + origStrings().UPGRADE_DESCRIPTION);
    }

    public void onMoveToDiscard(AbstractCard c) {
        setDesc(c, loadStrings().DESCRIPTION);
    }
}