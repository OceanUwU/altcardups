package altcardups.upgrades.Silent;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Distraction extends AbstractAlternateUpgrade implements UseOverride {
    public Distraction() {
        super(com.megacrit.cardcrawl.cards.green.Distraction.class);
    }
    
    public void up(AbstractCard c) {
        setMagic(c, 2);
        uDesc(c);
    }
    
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < c.magicNumber; i++) {
            AbstractCard c2 = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.SKILL).makeCopy();
            c2.setCostForTurn(-99);
            atb(new MakeTempCardInHandAction(c2, true));
        }
    }
}