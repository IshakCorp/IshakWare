package mathax.client.utils.player;

import static mathax.client.MatHax.mc;

public class Interactions {
    public static void setHVelocity(double x, double z) {
        mc.player.setVelocity(x, mc.player.getVelocity().getY(), z);
    }
}
