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

		if (heldStack.getItem() == ItemRegister.realmstoneBlank && heldStack.getItem().itemInteractionForEntity(heldStack, player, this, hand))
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
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 5), new ItemStack(WeaponRegister.throwableGrenade)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.dischargeCapsule, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 13), new ItemStack(Blocks.TNT)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Blocks.TNT), new ItemStack(ItemRegister.coinCopper, 10)));
		newTradesList.add(new AoATraderRecipe(FireworkUtil.getExplosiveExpertFireworks(), new ItemStack(ItemRegister.coinLunaver, 50)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemGemenyte, 3), new ItemStack(ItemRegister.unstableGunpowder, 2), new ItemStack(ArmourRegister.omniBoots)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemGemenyte, 4), new ItemStack(ItemRegister.unstableGunpowder, 2), new ItemStack(ArmourRegister.omniLegs)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemGemenyte, 5), new ItemStack(ItemRegister.unstableGunpowder, 3), new ItemStack(ArmourRegister.omniBody)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.gemGemenyte, 3), new ItemStack(ItemRegister.unstableGunpowder, 2), new ItemStack(ArmourRegister.omniHelmet)));
	}
}
