package altcardups;

import altcardups.util.UpgradeLibrary;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostUpdateSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class AltUpsMod implements EditStringsSubscriber, PostInitializeSubscriber, RenderSubscriber, PostUpdateSubscriber {
    public static final String ID = "altcardups";
    private static Settings.GameLanguage[] SupportedLanguages = {Settings.GameLanguage.ENG};
    public static final Logger logger = LogManager.getLogger(ID);
    private static ModPanel settingsPanel = new ModPanel();
    private static boolean upLastFrame = false;

    public static void initialize() {
        BaseMod.subscribe(new AltUpsMod());
    }

    public static String makeID(String str) {
        return ID + ":" + str;
    }

    public static String resourcePath(String path) {
        return ID + "Resources/" + path;
    }

    @Override
    public void receiveEditStrings() {
        String lang = "eng";
        for (Settings.GameLanguage i : SupportedLanguages)
            if (i.equals(Settings.language))
                lang = Settings.language.name().toLowerCase();
        BaseMod.loadCustomStringsFile(CardStrings.class, resourcePath("localization/"+lang+"/CardStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, resourcePath("localization/"+lang+"/UIStrings.json"));
    }

    @Override
    public void receivePostInitialize() {
        String[] TEXT = CardCrawlGame.languagePack.getUIString(makeID("badge")).TEXT;
        settingsPanel = new ModPanel();
        BaseMod.registerModBadge(new Texture(resourcePath("images/badge.png")), TEXT[0], TEXT[1], TEXT[2], settingsPanel);
        UpgradeLibrary.initialize();
        UpgradeLibrary.initializeConfig();
    }

    @Override
    public void receivePostUpdate() {
        if (settingsPanel.isUp) {
            if (!upLastFrame) ConfigMenu.open();
            ConfigMenu.update();
        }
        upLastFrame = settingsPanel.isUp;
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (settingsPanel.isUp)
            ConfigMenu.render(sb);
    }
}