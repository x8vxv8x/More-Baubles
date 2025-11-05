package smd.morebaubles.util;

import net.minecraft.nbt.NBTTagCompound;

//TODO maybe can use forge's INBTSerializable<NBTTagCompound> instead?
public interface INBTSavable {
	NBTTagCompound writeToNBT();

	// should be static but apparently java doesn't let you have static methods
	// in interfaces
    Object readFromNBT(NBTTagCompound tag);
}
