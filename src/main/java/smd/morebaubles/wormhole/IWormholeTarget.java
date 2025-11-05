package smd.morebaubles.wormhole;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IWormholeTarget {
	String getName();

	boolean teleportPlayerTo(EntityPlayer player);

	NBTTagCompound toNBT();

	void fromNBT(NBTTagCompound tag);

	boolean isEqual(IWormholeTarget other);

	boolean isEnabled();

	void setEnabled(boolean enabled);
}
