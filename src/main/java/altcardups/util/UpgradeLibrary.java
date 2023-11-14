package altcardups.util;

import altcardups.AltUpsMod;
import altcardups.upgrades.*;
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
        add(new Seek());
        add(new Claw());
        add(new Strike_Red());
        add(new Distraction());
        add(new Bash());
        add(new Armaments());
        add(new BodySlam());
        add(new Crescendo());
        add(new Tranquility());
        add(new SadisticNature());
        add(new Flex());
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