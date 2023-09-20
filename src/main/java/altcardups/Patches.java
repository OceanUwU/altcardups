package altcardups;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.UseOverride;
import altcardups.util.UpgradeLibrary;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.evacipated.cardcrawl.modthespire.patcher.PrefixPatchInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;

@SpirePatch(clz=CardCrawlGame.class, method=SpirePatch.CONSTRUCTOR)
public class Patches {
    public static void Raw(CtBehavior ctBehavior) throws Exception {
        System.out.println("");
        AltUpsMod.logger.info("Patching...");
        UpgradeLibrary.initialize();
        ClassPool pool = ctBehavior.getDeclaringClass().getClassPool();
        CtClass patchesClass = pool.getCtClass(Patches.class.getName());
        CtMethod upgradeMethod = patchesClass.getDeclaredMethod("upgradeCard");
        CtMethod useMethod = patchesClass.getDeclaredMethod("useCard");
        for (AbstractAlternateUpgrade up : UpgradeLibrary.altUpgrades.values()) {
            CtClass cardClass = pool.getCtClass(up.cardClass.getName());
            new PrefixPatchInfo(cardClass.getDeclaredMethod("upgrade"), upgradeMethod).doPatch();
            if (up instanceof UseOverride)
                new PrefixPatchInfo(cardClass.getDeclaredMethod("use"), useMethod).doPatch();
        }
        AltUpsMod.logger.info("Done!");
    }

    public static SpireReturn<Void> upgradeCard(AbstractCard c) {
        if (!c.upgraded && UpgradeLibrary.usesAlt(c)) {
            ReflectionHacks.privateMethod(AbstractCard.class, "upgradeName").invoke(c);
            UpgradeLibrary.getAlt(c).up(c);
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }

    public static SpireReturn<Void> useCard(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        if (UpgradeLibrary.isAltUpgraded(c)) {
            ((UseOverride)UpgradeLibrary.getAlt(c)).use(c, p, m);
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}