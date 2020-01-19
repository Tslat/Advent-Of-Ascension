package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.PotionUtil;

import javax.annotation.Nullable;

public class EntityStoreKeeper extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityStoreKeeper(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityStoreKeeper;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_STORE_KEEPER;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.voxPonds;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.toxicLump, 1), new ItemStack(ItemRegister.tokensVoxPonds, 10)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(ItemRegister.coinCopper, 16)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 1), new ItemStack(BlockRegister.glassVox, 32)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 4), poisonPotionStack()));
	}

	private ItemStack poisonPotionStack() {
		return new PotionUtil.PotionBuilder(Items.LINGERING_POTION).addEffect(new PotionEffect(MobEffects.POISON, 2500, 0, true, true)).build();
	}
}
