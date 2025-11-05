package smd.morebaubles.network.wormhole;

import smd.morebaubles.capability.CapabilityWormholePins;
import smd.morebaubles.network.NBTPacket;
import smd.morebaubles.wormhole.ContainerWormhole;
import smd.morebaubles.wormhole.IWormholeTarget;
import smd.morebaubles.wormhole.PlayerTarget;
import smd.morebaubles.wormhole.TeleportRequest;
import smd.morebaubles.wormhole.WormholeUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HandlerWormhole {
	public static void handleWormhole(NBTPacket message, MessageContext ctx) {
		NBTTagCompound tag = message.getTag();
		if (!tag.hasKey("target"))
			return;
		EntityPlayer player = ctx.getServerHandler().player;
		IWormholeTarget target = CapabilityWormholePins.targetFromNBT(tag.getCompoundTag("target"));
		if (target==null)
			return;
		if (target instanceof PlayerTarget) {
			EntityPlayer playerTarget = ((PlayerTarget) target).getPlayer(player.world);
			if (playerTarget == null) return;
			TeleportRequest.makeReq(player.world, player, playerTarget);
			return;
		}
		if (!target.teleportPlayerTo(player))
			return;
		WormholeUtil.consumeItem(player);
	}

	public static void handlePin(NBTPacket message, MessageContext ctx) {
//		morebaubles.logger.info("handlePin");
		NBTTagCompound tag = message.getTag();
//		morebaubles.logger.info(tag);
		int index = tag.getInteger("index");
		Container container = ctx.getServerHandler().player.openContainer;
//		morebaubles.logger.info(container);
		if (container instanceof ContainerWormhole) {
			((ContainerWormhole) container).pin(index);
		}
	}
}
