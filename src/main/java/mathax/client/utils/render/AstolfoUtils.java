package mathax.client.utils.render;

import java.awt.Color;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.math.MathHelper;

public class AstolfoUtils
{
    public static int setColor(int colorHex) {
        float alpha = (float)(colorHex >> 24 & 255) / 255.0F;
        float red = (float)(colorHex >> 16 & 255) / 255.0F;
        float green = (float)(colorHex >> 8 & 255) / 255.0F;
        float blue = (float)(colorHex & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha == 0.0F ? 1.0F : alpha);
        return colorHex;
    }
    public static Color setAlpha(Color color, int alpha) {
        alpha = (int)MathHelper.clamp((double)alpha, 0.0D, 255.0D);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    public static Color Rainbow1337(float yDist, float yTotal, float saturation, float speedt) {
        float speed = 3600.0F;

        float hue;
        for(hue = (float)(System.currentTimeMillis() % (long)((int)speed)) + (yTotal - yDist) * speedt; hue > speed; hue -= speed) {
        }

        hue /= speed;
        if ((double)hue > 1.5D) {
            hue = 1.5F - (hue - 1.5F);
        }

        ++hue;
        return Color.getHSBColor(hue, saturation, 1.0F);
    }
}
