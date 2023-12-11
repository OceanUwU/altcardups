package altcardups.upgrades.Defect;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_Blue extends AbstractAlternateUpgrade {
    public Strike_Blue() {
        super(com.megacrit.cardcrawl.cards.blue.Strike_Blue.class);
    }
    
    public void up(AbstractCard c) {
        setCost(c, 0);
    }
}