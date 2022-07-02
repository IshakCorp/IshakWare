package mathax.client.events.entity;

import net.minecraft.entity.passive.HorseEntity;
// useless shit

public class HorseMoveEvent {

    private static final HorseMoveEvent INSTANCE = new HorseMoveEvent();

    public HorseEntity horse;

    public static HorseMoveEvent get(HorseEntity entity) {
        INSTANCE.horse = entity;
        return INSTANCE;
    }
}
