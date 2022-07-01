package mathax.client.systems.modules.player;

import mathax.client.eventbus.EventHandler;
import mathax.client.events.packets.PacketEvent;
import mathax.client.systems.modules.Categories;
import mathax.client.systems.modules.Module;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;

public class PortalGodMode extends Module{
    public PortalGodMode() {
        super(Categories.Player, Items.DIAMOND_HOE, "PoRtAlGoDmOdE", "PoRtAlGoDmOdE ReAl!!!!!");
    }

    @EventHandler
    private void POPS(PacketEvent.Send event) {
        if (event.packet instanceof TeleportConfirmC2SPacket) event.cancel();
        toggle();
    }
}
