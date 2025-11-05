package smd.morebaubles.item.base;

import java.util.Map;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IItemAttributeModifier {
	Map<IAttribute, AttributeModifier> getModifiers(ItemStack stack,
                                                    EntityPlayer player);
}
