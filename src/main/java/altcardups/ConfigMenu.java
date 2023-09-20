package altcardups;

import altcardups.upgrades.AbstractAlternateUpgrade;
import altcardups.util.UpgradeLibrary;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
import java.util.ArrayList;

import static altcardups.AltUpsMod.makeID;
import static altcardups.AltUpsMod.resourcePath;

public class ConfigMenu {
    private static float scroll = 0.0f;
    private static float target_scroll = 0.0f;
    private static final Texture pixel = new Texture(resourcePath("images/pixel.png"));
    private static String[] TEXT;
    private static final ArrayList<ArrayList<AbstractCard>> cards = new ArrayList<>();
    private static final float OFF_TRANSPARENCY = 0.3f;
    private static final float UNHOVERED_DRAWSCALE = 0.5f;
    private static final float HOVERED_DRAWSCALE = 0.9f;
    private static final float SPACE_X = 200f;
    private static final float SPACE_Y = 300f;
    private static float MAX_SCROLL = SPACE_Y * Settings.yScale;
    private static ScrollBar scrollBar;
    private static float HB_W = 300f * Settings.scale * UNHOVERED_DRAWSCALE;
    private static float HB_H = 420f * Settings.scale * UNHOVERED_DRAWSCALE;
    private static final Color colourBehind = new Color(0.05f, 0.1f, 0.05f, 1f);

    private static void initialize() {
        try {
            TEXT = CardCrawlGame.languagePack.getUIString(makeID("configMenu")).TEXT;
            for (AbstractAlternateUpgrade up : UpgradeLibrary.altUpgrades.values()) {
                ArrayList<AbstractCard> row = new ArrayList<>();
                for (int i = 0; i < 3; i++)
                    row.add(up.cardClass.newInstance());
                cards.add(row);
            }
            UpgradeLibrary.forceOff = true;
            for (ArrayList<AbstractCard> row : cards) row.get(1).upgrade();
            UpgradeLibrary.forceOff = false;
            UpgradeLibrary.forceOn = true;
            for (ArrayList<AbstractCard> row : cards) row.get(2).upgrade();
            UpgradeLibrary.forceOn = false;
            for (ArrayList<AbstractCard> row : cards) {
                if (UpgradeLibrary.usesAlt(row.get(0)))
                    row.get(1).transparency = OFF_TRANSPARENCY;
                else 
                    row.get(2).transparency = OFF_TRANSPARENCY;
                for (int i = 0; i < 3; i++) {
                    AbstractCard c = row.get(i);
                    c.current_x = Settings.WIDTH / 2f + SPACE_X * Settings.xScale * (i - 1);
                    c.current_y = Settings.HEIGHT + SPACE_Y * Settings.yScale * cards.indexOf(row);
                    c.target_x = c.current_x;
                    c.target_y = c.current_y;
                    c.drawScale = UNHOVERED_DRAWSCALE;
                    c.targetDrawScale = c.drawScale;
                    c.targetTransparency = c.transparency;
                    c.hb.move(c.current_x, c.current_y);
                }
            }
            MAX_SCROLL *=  cards.size() - 1;
            scrollBar = new ScrollBar(new ScrollBarListener() {
                public void scrolledUsingBar(float percent) {
                    target_scroll = MAX_SCROLL * percent;
                }
            }, Settings.WIDTH / 2f + SPACE_X * Settings.xScale * 2, Settings.HEIGHT / 2f - 70f * Settings.yScale, Settings.HEIGHT - 250f * Settings.yScale);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void enableAlt(AbstractCard c, boolean enabled) {
        UpgradeLibrary.enabled.put(c.getClass(), enabled);
        UpgradeLibrary.config.setBool(c.getClass().getSimpleName(), enabled);
        try {UpgradeLibrary.config.save();} catch (Exception e) {}
    }

    public static void open() {
        if (TEXT == null) initialize();
        CardCrawlGame.cancelButton.show(TEXT[4]);
    }

    public static void update() {
        if (TEXT == null) return;
        try {
            for (int i = 0; i < cards.size(); i++) {
                ArrayList<AbstractCard> row = cards.get(i);
                for (int j = 0; j < 3; j++) {
                    AbstractCard c = row.get(j);
                    c.current_y = Settings.HEIGHT / 2f - SPACE_Y * Settings.yScale * i + scroll;
                    c.target_y = c.current_y;
                    c.hb.update();
                    if (c.targetDrawScale == HOVERED_DRAWSCALE && !c.hb.hovered) {
                        c.targetDrawScale = UNHOVERED_DRAWSCALE;
                        if (j == 1) c.targetTransparency = UpgradeLibrary.usesAlt(c) ? OFF_TRANSPARENCY : 1f;
                        else if (j == 2) c.targetTransparency = UpgradeLibrary.usesAlt(c) ? 1f : OFF_TRANSPARENCY;
                    }
                    if (c.hb.hovered) {
                        c.targetDrawScale = HOVERED_DRAWSCALE;
                        c.targetTransparency = 1f;
                        if (InputHelper.justReleasedClickLeft) {
                            if (j == 1) enableAlt(c, false);
                            else if (j == 2) enableAlt(c, true);
                            row.get(1).targetTransparency = UpgradeLibrary.usesAlt(c) ? OFF_TRANSPARENCY : 1f;
                            row.get(2).targetTransparency = UpgradeLibrary.usesAlt(c) ? 1f : OFF_TRANSPARENCY;
                            c.targetTransparency = 1f;
                        }
                    } else
                        c.targetDrawScale = UNHOVERED_DRAWSCALE;
                    c.update();
                    c.hb.resize(HB_W, HB_H);
                }
            }
            if (InputHelper.scrolledDown) {
                target_scroll += Settings.SCROLL_SPEED;
                target_scroll = Math.min(MAX_SCROLL, target_scroll);
            } else if (InputHelper.scrolledUp) {
                target_scroll -= Settings.SCROLL_SPEED;
                target_scroll = Math.max(0f, target_scroll);
            }
            scrollBar.update();
            scroll = MathHelper.scrollSnapLerpSpeed(scroll, target_scroll);
            scrollBar.parentScrolledToPercent(scroll / MAX_SCROLL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void render(SpriteBatch sb) {
        if (TEXT == null) return;
        sb.setColor(colourBehind);
        sb.draw(pixel, 0, 0, Settings.WIDTH, Settings.HEIGHT);
        sb.setColor(Color.WHITE);
        for (int i = 0; i < cards.size(); i++) {
            ArrayList<AbstractCard> row = cards.get(i);
            for (int j = 0; j < 3; j++) {
                AbstractCard c = row.get(j);
                c.render(sb);
            }
        }
        sb.setColor(Color.TEAL);
        sb.draw(pixel, 0, Settings.HEIGHT - 80f * Settings.yScale, Settings.WIDTH, 80f * Settings.yScale);
        sb.setColor(Color.WHITE);
        FontHelper.renderFontCenteredHeight(sb, FontHelper.buttonLabelFont, TEXT[0], 20f * Settings.xScale, Settings.HEIGHT - 40f * Settings.yScale, Settings.CREAM_COLOR);
        for (int i = 0; i < 3; i++)
            FontHelper.renderFontCenteredTopAligned(sb, FontHelper.buttonLabelFont, TEXT[i+1], Settings.WIDTH / 2f + 200f * (i - 1) * Settings.xScale, Settings.HEIGHT - 40f * Settings.yScale, Settings.CREAM_COLOR);
        scrollBar.render(sb);
        CardCrawlGame.cancelButton.render(sb);
    }
}