package mathax.client.systems.modules.movement;

import mathax.client.systems.modules.render.FreeLook;
import mathax.client.systems.modules.world.Timer;
import mathax.client.systems.modules.Categories;
import mathax.client.systems.modules.Modules;
import mathax.client.events.world.TickEvent;
import mathax.client.settings.DoubleSetting;
import mathax.client.systems.modules.Module;
import mathax.client.eventbus.EventHandler;
import mathax.client.settings.SettingGroup;
import mathax.client.utils.misc.input.Input;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.math.MathHelper;
import mathax.client.settings.BoolSetting;
import mathax.client.settings.Setting;
import net.minecraft.item.Items;

import static net.minecraft.client.option.Perspective.THIRD_PERSON_BACK;


public class BoostFly extends Module {

    public BoostFly() {
        super(Categories.Movement, Items.FEATHER, "boost-fly", "Pasted from bebrap$$$");
    }

    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    // General

    private void setPressed(KeyBinding key, boolean pressed) {
        key.setPressed(pressed);
        Input.setKeyState(key, pressed);
    }

    private final Setting<Double> speed = sgGeneral.add(new DoubleSetting.Builder()
        .name("motion-speed")
        .description("How much velocity being add")
        .defaultValue(0.1)
        .range(0, 10)
        .build()
    );
    private final Setting<Double> upSpeed = sgGeneral.add(new DoubleSetting.Builder()
        .name("up-speed")
        .description("How much Y velocity being add")
        .defaultValue(0.002)
        .range(0, 10)
        .build()
    );
    private final Setting<Double> yawStep = sgGeneral.add(new DoubleSetting.Builder()
        .name("yaw-speed")
        .description("yaw step for wasd mode!!!!")
        .defaultValue(2.5)
        .range(0, 10)
        .build()
    );
    public final Setting<Boolean> useTimer = sgGeneral.add(new BoolSetting.Builder()
        .name("use-timer")
        .description("5% Speed increase")
        .defaultValue(false)
        .build()
    );
    public final Setting<Boolean> WASD = sgGeneral.add(new BoolSetting.Builder()
        .name("WASD-mode")
        .description("Control elytra by WASD keys!!!!!!")
        .defaultValue(false)
        .build()
    );
    public final Setting<Boolean> freeLook = sgGeneral.add(new BoolSetting.Builder()
        .name("FreeLook")
        .description("Use FreeLook for WASD-mode")
        .defaultValue(false)
        .build()
    );
    public final Setting<Boolean> pressForward = sgGeneral.add(new BoolSetting.Builder()
        .name("auto-pilot")
        .description("Auto press W key...")
        .defaultValue(false)
        .build()
    );

    @Override
    public void onDeactivate() {
        Modules.get().get(Timer.class).setOverride(Timer.OFF);
        if (Modules.get().get(FreeLook.class).isActive()){
            Modules.get().get(FreeLook.class).forceToggle(false);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.player.getAbilities().flying) mc.player.getAbilities().flying = false;
        if (!useTimer.get()) Modules.get().get(Timer.class).setOverride(Timer.OFF);
        if (useTimer.get()) Modules.get().get(Timer.class).setOverride(1.05);

        float yaw = (float) Math.toRadians(mc.player.getYaw());
        float pitch = (float) Math.toRadians(mc.player.getPitch());

        if(WASD.get() && freeLook.get()){
            if (!mc.player.isFallFlying()){
                if (Modules.get().get(FreeLook.class).isActive()){
                    Modules.get().get(FreeLook.class).forceToggle(false);
                }
            } else if (mc.player.isFallFlying()) {
                if (!Modules.get().get(FreeLook.class).isActive()){
                    Modules.get().get(FreeLook.class).forceToggle(true);
                    Modules.get().get(FreeLook.class).mode.set(FreeLook.Mode.Camera);
                if(mc.options.getPerspective() != THIRD_PERSON_BACK) mc.options.setPerspective(THIRD_PERSON_BACK); //Жесточайший фикс поломки фрилука через f5
            }
        } else if ((!WASD.get() || !freeLook.get()) && Modules.get().get(FreeLook.class).isActive()) Modules.get().get(FreeLook.class).forceToggle(false);

        if (mc.player.isFallFlying()) {
            if (pressForward.get()) {
                mc.player.addVelocity(-MathHelper.sin(yaw) * speed.get() / 10, MathHelper.cos(pitch) * upSpeed.get() / 10, MathHelper.cos(yaw) * speed.get() / 10);
                if (WASD.get()){
                    mc.player.setPitch((float) -7.4);
                } //Ты ишак ебливый потом нормально сделай
            } else {
                if (mc.options.forwardKey.isPressed()) {
                    mc.player.addVelocity(-MathHelper.sin(yaw) * speed.get() / 10, MathHelper.cos(pitch) * upSpeed.get() / 10, MathHelper.cos(yaw) * speed.get() / 10);
                    if (WASD.get()){
                        mc.player.setPitch((float) -7.4);
                    }
                }
            }
        }
            if (WASD.get()) {
                if (mc.options.sneakKey.isPressed()) {
                    mc.player.setPitch(10);
                }
                if (mc.options.jumpKey.isPressed()) {
                    mc.player.setPitch(-10);
                }
                if (mc.options.leftKey.isPressed()) {
                    mc.player.setYaw((float) (mc.player.getYaw() - yawStep.get()));
                }
                if (mc.options.rightKey.isPressed()) {
                    mc.player.setYaw((float) (mc.player.getYaw() + yawStep.get()));
                }
                if (mc.options.backKey.isPressed()){
                    mc.player.addVelocity(MathHelper.sin(yaw) / 35, MathHelper.cos(pitch) * upSpeed.get() / 10, -MathHelper.cos(yaw) / 35); //TODO: NoGlide
                } //Мистер флатк бля!!! Нормально на пакетах ротации сделай
            }
        }

    }
}
