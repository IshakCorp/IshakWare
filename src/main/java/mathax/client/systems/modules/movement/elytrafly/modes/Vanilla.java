package mathax.client.systems.modules.movement.elytrafly.modes;

import mathax.client.events.world.TickEvent;
import mathax.client.systems.modules.movement.elytrafly.ElytraFlightMode;
import mathax.client.systems.modules.movement.elytrafly.ElytraFlightModes;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import mathax.client.MatHax;
import mathax.client.events.packets.PacketEvent;
import mathax.client.systems.modules.movement.elytrafly.ElytraFlightMode;
import mathax.client.systems.modules.movement.elytrafly.ElytraFlightModes;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Vec3d;


public class Vanilla extends ElytraFlightMode {
    public Vanilla() {
        super(ElytraFlightModes.Vanilla);
    }

    @Override
    public void onPacketSend(PacketEvent.Send event) {
        if (event.packet instanceof PlayerMoveC2SPacket) {
            MatHax.mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(MatHax.mc.player, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            //event.cancel();
        }
    }

}

