package smd.morebaubles.event;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import smd.morebaubles.ModConfig;
import smd.morebaubles.baubleeffect.BaubleAttributeModifierHandler;
import smd.morebaubles.baubleeffect.IFireResistance;
import smd.morebaubles.baubleeffect.IJumpBoost;
import smd.morebaubles.baubleeffect.PotionNegation;
import smd.morebaubles.item.ItemAmuletCross;
import smd.morebaubles.item.ItemShieldObsidian;
import smd.morebaubles.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventHandler {
	@SubscribeEvent
	public static void onJump(LivingEvent.LivingJumpEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			Set<String> found = new HashSet<>();
			for (int i = 0; i<baubles.getSlots(); i++) {
				ItemStack stack = baubles.getStackInSlot(i);
				if (stack.getItem() instanceof IJumpBoost
						&&!found.contains(stack.getItem().getTranslationKey())) {
					found.add(stack.getItem().getTranslationKey());
					IJumpBoost jumpBoost = (IJumpBoost) (stack.getItem());
					player.motionY += jumpBoost.getJumpBoost();
					player.fallDistance -= jumpBoost.getFallResist();
				}
			}
		}
	}

	@SubscribeEvent
	public static void onDamage(LivingAttackEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (BaublesApi.isBaubleEquipped(player, ModItems.amuletCross)!=-1) {
				player.maxHurtResistantTime = ItemAmuletCross.RESIST_TIME;
			} else if (player.maxHurtResistantTime==ItemAmuletCross.RESIST_TIME) {
				player.maxHurtResistantTime = 20;
			}
			if (event.getSource().isFireDamage()) {
				float damageMulti = 1F;
				float damageMultiLava = 1F;
				float maxDamageNegate = 0F;
				IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
				Set<UUID> found = new HashSet<>();
				for (int i = -2; i<baubles.getSlots(); i++) {
					ItemStack stack = i>=0 ? baubles.getStackInSlot(i)
							: (i==-2 ? player.getHeldItemMainhand() : player.getHeldItemOffhand());
					if (stack.getItem() instanceof IFireResistance
							&&!found.contains(((IFireResistance) stack.getItem()).getFireResistID())
							&&(i>=0||stack.getItem() instanceof ItemShieldObsidian)) {
						IFireResistance fireResist = (IFireResistance) (stack.getItem());
						found.add(fireResist.getFireResistID());
						damageMulti *= 1-fireResist.getResistance();
						damageMultiLava *= 1-fireResist.getResistanceLava();
						maxDamageNegate = Math.max(maxDamageNegate, fireResist.getMaxNegate());
					}
				}

				if (event.getAmount()<=maxDamageNegate&&event.isCancelable())
					event.setCanceled(true);
				if (event.getSource().equals(DamageSource.LAVA)) {
					damageMulti = damageMultiLava;
				}
				if (damageMulti<0.999F) {
					if (damageMulti<0.001F&&event.isCancelable()) {
						event.setCanceled(true);
					}
				}
			} else if (event.getSource()==DamageSource.FALL) {
				if (BaublesApi.isBaubleEquipped(player, ModItems.trinketLuckyHorseshoe)!=-1) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onDamage(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (event.getSource().isFireDamage()) {
				float damageMulti = 1F;
				float damageMultiLava = 1F;
				float maxDamageNegate = 0F;
				IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
				Set<UUID> found = new HashSet<>();
				for (int i = -2; i<baubles.getSlots(); i++) {
					ItemStack stack = i>=0 ? baubles.getStackInSlot(i)
							: (i==-2 ? player.getHeldItemMainhand() : player.getHeldItemOffhand());
					if (stack.getItem() instanceof IFireResistance
							&&!found.contains(((IFireResistance) stack.getItem()).getFireResistID())
							&&(i>=0||stack.getItem() instanceof ItemShieldObsidian)) {
						IFireResistance fireResist = (IFireResistance) (stack.getItem());
						found.add(fireResist.getFireResistID());
						damageMulti *= 1-fireResist.getResistance();
						damageMultiLava *= 1-fireResist.getResistanceLava();
						maxDamageNegate = Math.max(maxDamageNegate, fireResist.getMaxNegate());
					}
				}

				if (event.getAmount()<=maxDamageNegate&&event.isCancelable())
					event.setCanceled(true);
				if (event.getSource().equals(DamageSource.LAVA)) {
					damageMulti = damageMultiLava;
				}
				if (damageMulti<0.999F) {
					if (damageMulti<0.001F&&event.isCancelable()) {
						event.setCanceled(true);
					}
					event.setAmount(event.getAmount()*damageMulti);
				}
			} else if (event.getSource()==DamageSource.FALL) {
				if (BaublesApi.isBaubleEquipped(player, ModItems.trinketLuckyHorseshoe)!=-1) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void playerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase==TickEvent.Phase.END) {
			EntityPlayer player = event.player;
			ItemStack main = player.getHeldItemMainhand();
			ItemStack off = player.getHeldItemOffhand();
			if (main.getItem() instanceof IItemHeldListener)
				((IItemHeldListener) main.getItem()).onHeldTick(main, player, EnumHand.MAIN_HAND);
			if (off.getItem() instanceof IItemHeldListener)
				((IItemHeldListener) off.getItem()).onHeldTick(off, player, EnumHand.OFF_HAND);

			if (player.world.getTotalWorldTime()%10==0) {
				IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
				BaubleAttributeModifierHandler.removeAllModifiers(player);
				for (int i = 0; i<baubles.getSlots(); i++) {
					ItemStack bauble = baubles.getStackInSlot(i);
					if (bauble!=null&&ModConfig.baubleModifiersEnabled.getBoolean(true)) {
						BaubleAttributeModifierHandler.baubleModified(bauble, player, true);
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void potionApply(PotionApplicableEvent event) {
		PotionNegation.potionApply(event);
	}

}
