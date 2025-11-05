package smd.morebaubles.item;

import baubles.api.BaubleType;
import smd.morebaubles.morebaubles;
import smd.morebaubles.baubleeffect.IJumpBoost;
import smd.morebaubles.item.base.AGenericItemBauble;
import net.minecraft.item.ItemStack;

//TODO add item model like terraria's balloon?
public class ItemTrinketBalloon extends AGenericItemBauble implements IJumpBoost {
	public ItemTrinketBalloon() {
		super("trinketBalloon", morebaubles.TAB);
		morebaubles.registryHelper.addItemModel(this);
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.TRINKET;
	}

	@Override
	public float getJumpBoost() {
		return 0.3F;
	}

	@Override
	public float getFallResist() {
		return 5F;
	}
}
