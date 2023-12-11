package altcardups.upgrades.Silent;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend_Green extends AbstractAlternateUpgrade implements UseOverride {
    public Defend_Green() {
        super(com.megacrit.cardcrawl.cards.green.Defend_Green.class);
    }
    
    public void up(AbstractCard c) {
        uBlock(c, -1);
        c.cardsToPreview = new Shiv();
        uDesc(c);
    }

    @Override
    public void use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        blck(c);
        atb(new MakeTempCardInHandAction(c.cardsToPreview));
    }
}