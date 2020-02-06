package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityAssassin extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityAssassin(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityAssassin;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_ASSASSIN;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == ItemRegister.rockBones) {
			if (!world.isRemote)
				player.setHeldItem(hand, ItemRegister.millenniumUpgrader.newValidStack());

			return true;
		}

		return super.processInteract(player, hand);
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableSliceStar, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableGooBall, 3)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(WeaponRegister.throwableChakram, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableHellfire, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 3), new ItemStack(WeaponRegister.throwableVulkram, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.metalSlug, 2)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.bulletLimonite, 3)));
	}
}
