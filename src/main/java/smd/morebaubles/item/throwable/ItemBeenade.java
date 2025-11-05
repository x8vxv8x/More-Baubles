package smd.morebaubles.item.throwable;

import smd.morebaubles.morebaubles;
import smd.morebaubles.entity.EntityBeenade;
import smd.morebaubles.entity.EntityTerrariaThrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemBeenade extends ItemTerrariaThrowable {

	public ItemBeenade(String name) {
		super(name, morebaubles.TAB, 15);
	}

	@Override
	public EntityTerrariaThrowable getThrownEntity(World world, EntityPlayer player) {
		return new EntityBeenade(world, player);
	}
}
