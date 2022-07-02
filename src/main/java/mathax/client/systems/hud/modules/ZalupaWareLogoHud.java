package mathax.client.systems.hud.modules;

import mathax.client.renderer.GL;
import mathax.client.renderer.Renderer2D;
import mathax.client.settings.DoubleSetting;
import mathax.client.settings.Setting;
import mathax.client.settings.SettingGroup;
import mathax.client.utils.render.color.Color;
import mathax.client.systems.hud.HUD;
import mathax.client.systems.hud.HudElement;
import mathax.client.systems.hud.HudRenderer;
import net.minecraft.util.Identifier;

public class ZalupaWareLogoHud extends HudElement {
    private static final Identifier ZALUPA_LOGO = new Identifier("mathax", "textures/zalupaware.png");
    private final Color TEXTURE_COLOR = new Color(255, 255, 255, 255);

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    // General

    /*
    private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
        .name("scale")
        .description("The scale of big rat.")
        .defaultValue(0.25)
        .min(0.1)
        .sliderRange(0.1, 2)
        .build()
    );
    */

    private final Setting<Double> imgWidth = sgGeneral.add(new DoubleSetting.Builder()
        .name("width")
        .description("The width of Zalupa logo.")
        .defaultValue(100)
        .min(10)
        .sliderRange(70, 1000)
        .build()
    );

    private final Setting<Double> imgHeight = sgGeneral.add(new DoubleSetting.Builder()
        .name("height")
        .description("The height of Zalupa logo.")
        .defaultValue(100)
        .min(10)
        .sliderRange(70, 1000)
        .build()
    );

    public ZalupaWareLogoHud(HUD hud) {
        super(hud, "Zalupa-logo", "Displays a Zalupa logo.", false);
    }

    @Override
    public void update(HudRenderer renderer) {
        box.setSize(imgWidth.get(), imgHeight.get());
    }

    @Override
    public void render(HudRenderer renderer) {
        GL.bindTexture(ZALUPA_LOGO);
        Renderer2D.TEXTURE.begin();
        Renderer2D.TEXTURE.texQuad(box.getX(), box.getY(), box.width, box.height, TEXTURE_COLOR);
        Renderer2D.TEXTURE.render(null);
    }
}
