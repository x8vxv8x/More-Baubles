package smd.morebaubles.item;

import java.util.List;

import baubles.api.BaubleType;
import smd.morebaubles.morebaubles;
import smd.morebaubles.baubleeffect.PotionNegation;
import smd.morebaubles.baubleeffect.PotionNegation.IPotionNegateItem;
import smd.morebaubles.item.base.AGenericItemBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ItemTrinketPotionCharm extends AGenericItemBauble implements IPotionNegateItem {
	public List<String> potions;

	public ItemTrinketPotionCharm(String id, List<String> potionsIn) {
		super(id, morebaubles.TAB);
		morebaubles.registryHelper.addItemModel(this);
		potions = potionsIn;
	}

	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.TRINKET;
	}

	@Override
	public void onWornTick(ItemStack stack, EntityLivingBase player) {
		PotionNegation.negatePotion(player, potions);
	}

	@Override
	public List<String> getCureEffects() {
		return potions;
	}
}
