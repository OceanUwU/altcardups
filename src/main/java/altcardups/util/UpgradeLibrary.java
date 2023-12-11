package altcardups.util;

import altcardups.AltUpsMod;
import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.upgrades.Colorless.*;
import altcardups.upgrades.Ironclad.*;
import altcardups.upgrades.Silent.*;
import altcardups.upgrades.Defect.*;
import altcardups.upgrades.Watcher.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.cards.AbstractCard;
import java.util.LinkedHashMap;
import java.util.Properties;

public class UpgradeLibrary {
    public static LinkedHashMap<Class<? extends AbstractCard>, AbstractAlternateUpgrade> altUpgrades = new LinkedHashMap<>();
    public static LinkedHashMap<Class<? extends AbstractCard>, Boolean> enabled = new LinkedHashMap<>();
    public static boolean forceOff = false;
    public static boolean forceOn = false;
    public static SpireConfig config;

    public static boolean usesAlt(AbstractCard c) {
        if (UpgradeLibrary.forceOff) return false;
        if (UpgradeLibrary.forceOn) return true;
        return UpgradeLibrary.enabled.get(c.getClass());
    }

    public static boolean isAltUpgraded(AbstractCard c) {
        return c.upgraded && usesAlt(c);
    }

    public static boolean hasAlt(AbstractCard c) {
        return UpgradeLibrary.altUpgrades.containsKey(c.getClass());
    }

    public static AbstractAlternateUpgrade getAlt(AbstractCard c) {
        return UpgradeLibrary.altUpgrades.get(c.getClass());
    }

    public static void initialize() {
        altUpgrades.clear();
        enabled.clear();
        add(new Strike_Red());
        add(new Defend_Red());
        add(new Bash());
        add(new Strike_Green());
        add(new Defend_Green());
        add(new Neutralize());
        add(new Survivor());
        add(new Strike_Blue());
        add(new Defend_Blue());
        add(new Zap());
        add(new Dualcast());
        add(new Strike_Purple());
        add(new Defend_Watcher());
        add(new Eruption());
        add(new Vigilance());
        add(new Seek());
        add(new Claw());
        add(new Distraction());
        add(new Armaments());
        add(new BodySlam());
        add(new Crescendo());
        add(new Tranquility());
        add(new SadisticNature());
        add(new Flex());
        add(new PommelStrike());
        add(new ShrugItOff());
        add(new SearingBlow());
    }

    public static void initializeConfig() {
        Properties defaults = new Properties(); 
        for (Class<? extends AbstractCard> c : altUpgrades.keySet())
            defaults.put(c.getSimpleName(), "false");
        try {
            config = new SpireConfig(AltUpsMod.ID, "config", defaults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Class<? extends AbstractCard> c : altUpgrades.keySet())
            enabled.put(c, config.getBool(c.getSimpleName()));
    }

    private static void add(AbstractAlternateUpgrade up) {
        altUpgrades.put(up.cardClass, up);
    }
}