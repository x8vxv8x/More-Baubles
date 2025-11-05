package smd.morebaubles.item.throwable;

import smd.morebaubles.morebaubles;
import smd.morebaubles.entity.EntityGrenade;
import smd.morebaubles.entity.EntityTerrariaThrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemGrenade extends ItemTerrariaThrowable {

	public ItemGrenade(String name) {
		super(name, morebaubles.TAB, 15);
	}

	@Override
	public EntityTerrariaThrowable getThrownEntity(World world, EntityPlayer player) {
		return new EntityGrenade(world, player);
	}
}
