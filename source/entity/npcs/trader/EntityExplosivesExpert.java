package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.FireworkUtil;

import javax.annotation.Nullable;

public class EntityExplosivesExpert extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityExplosivesExpert(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityExplosivesExpert;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == ItemRegister.BLANK_REALMSTONE && heldStack.getItem().itemInteractionForEntity(heldStack, player, this, hand))
			return true;

		return super.processInteract(player, hand);
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_EXPLOSIVES_EXPERT;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.creeponia;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 5), new ItemStack(WeaponRegister.GRENADE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 2), new ItemStack(ItemRegister.DISCHARGE_CAPSULE, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.COPPER_COIN, 13), new ItemStack(Blocks.TNT)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.TNT), new ItemStack(ItemRegister.COPPER_COIN, 10)));
		newTradesList.add(new AoATraderRecipe(FireworkUtil.getExplosiveExpertFireworks(), ItemStack.EMPTY, new ItemStack(ItemRegister.LUNAVER_COIN, 50), 1, 1));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GEMENYTE, 3), new ItemStack(ItemRegister.UNSTABLE_GUNPOWDER, 2), new ItemStack(ArmourRegister.OMNI_HELMET)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GEMENYTE, 5), new ItemStack(ItemRegister.UNSTABLE_GUNPOWDER, 3), new ItemStack(ArmourRegister.OMNI_CHESTPLATE)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GEMENYTE, 4), new ItemStack(ItemRegister.UNSTABLE_GUNPOWDER, 2), new ItemStack(ArmourRegister.OMNI_LEGS)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GEMENYTE, 3), new ItemStack(ItemRegister.UNSTABLE_GUNPOWDER, 2), new ItemStack(ArmourRegister.OMNI_BOOTS)));
	}
}
