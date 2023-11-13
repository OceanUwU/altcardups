package altcardups;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.interfaces.*;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.evacipated.cardcrawl.modthespire.patcher.PrefixPatchInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;

import static altcardups.util.UpgradeLibrary.*;

@SpirePatch(clz=CardCrawlGame.class, method=SpirePatch.CONSTRUCTOR)
public class Patches {
    private static CtClass patchesClass;

    public static void Raw(CtBehavior ctBehavior) throws PatchingException, NotFoundException {
        System.out.println("");
        AltUpsMod.logger.info("Patching...");
        initialize();
        ClassPool pool = ctBehavior.getDeclaringClass().getClassPool();
        patchesClass = pool.getCtClass(Patches.class.getName());
        for (AbstractAlternateUpgrade up : altUpgrades.values()) {
            CtClass cardClass = pool.getCtClass(up.cardClass.getName());
            patch(cardClass, "upgrade");
            patch(cardClass, "use", UseOverride.class, up);
            patch(cardClass, "applyPowers", ApplyPowersOverride.class, up);
            patch(cardClass, "calculateCardDamage", CalculateCardDamageOverride.class, up);
            patch(cardClass, "onMoveToDiscard", OnMoveToDiscardOverride.class, up);
        }
        AltUpsMod.logger.info("Done!");
    }

    private static void patch(CtClass cardClass, String methodName, Class<?> interfaceCheck, AbstractAlternateUpgrade up) throws PatchingException, NotFoundException {
        if (interfaceCheck.isInstance(up))
            patch(cardClass, methodName);
    }

    private static void patch(CtClass cardClass, String methodName) throws PatchingException, NotFoundException {
        new PrefixPatchInfo(cardClass.getDeclaredMethod(methodName), patchesClass.getDeclaredMethod(methodName)).doPatch();
    }

    public static SpireReturn<Void> upgrade(AbstractCard c) {
        if (!c.upgraded && usesAlt(c)) {
            ReflectionHacks.privateMethod(AbstractCard.class, "upgradeName").invoke(c);
            getAlt(c).up(c);
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }

    public static SpireReturn<Void> use(AbstractCard c, AbstractPlayer p, AbstractMonster m) {
        return ifAlt(c, () -> ((UseOverride)getAlt(c)).use(c, p, m));
    }

    public static SpireReturn<Void> applyPowers(AbstractCard c) {
        return ifAlt(c, () -> ((ApplyPowersOverride)getAlt(c)).applyPowers(c));
    }

    public static SpireReturn<Void> calculateCardDamage(AbstractCard c, AbstractMonster m) {
        return ifAlt(c, () -> ((CalculateCardDamageOverride)getAlt(c)).calculateCardDamage(c, m));
    }

    public static SpireReturn<Void> onMoveToDiscard(AbstractCard c) {
        return ifAlt(c, () -> ((OnMoveToDiscardOverride)getAlt(c)).onMoveToDiscard(c));
    }

    private static SpireReturn<Void> ifAlt(AbstractCard c, Runnable func) {
        if (AbstractAlternateUpgrade.ignorePatches) {
            AbstractAlternateUpgrade.ignorePatches = false;
            return SpireReturn.Continue();
        }
        if (isAltUpgraded(c)) {
            func.run();
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}