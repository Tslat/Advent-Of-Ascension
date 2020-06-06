package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityUndeadHerald extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityUndeadHerald(World world) {
		super(world, entityWidth, 2.0f);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityUndeadHerald;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 100;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_UNDEAD_HERALD;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected boolean canDespawn() {
		return ticksExisted > 72000;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		Enums.Dimensions dimension = WorldUtil.getDimensionFromId(world.provider.getDimension());

		if (dimension != null) {
			switch (dimension) {
				case ABYSS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ABYSS_TOKENS, 10), new ItemStack(BlockRegister.SHADOW_BANNER, 1)));
					break;
				case BARATHOS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.BARON_TOKENS, 10), new ItemStack(BlockRegister.BARON_BANNER, 1)));
					break;
				case CANDYLAND:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CANDYLAND_TOKENS, 10), new ItemStack(BlockRegister.CANDY_BANNER, 1)));
					break;
				case CELEVE:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CELEVE_TOKENS, 10), new ItemStack(BlockRegister.CLOWN_BANNER, 1)));
					break;
				case CREEPONIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CREEPONIA_TOKENS, 10), new ItemStack(BlockRegister.CREEPY_BANNER, 1)));
					break;
				case CRYSTEVIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.CRYSTEVIA_TOKENS, 10), new ItemStack(BlockRegister.CRYSTAL_BANNER, 1)));
					break;
				case DEEPLANDS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DEEPLANDS_TOKENS, 10), new ItemStack(BlockRegister.DEEP_BANNER, 1)));
					break;
				case DUSTOPIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.DUSTOPIA_TOKENS, 10), new ItemStack(BlockRegister.DUSTOPIAN_BANNER, 1)));
					break;
				case GARDENCIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GARDENCIA_TOKENS, 10), new ItemStack(BlockRegister.ROSIDIAN_BANNER, 1)));
					break;
				case GRECKON:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.GRECKON_TOKENS, 10), new ItemStack(BlockRegister.HAUNTED_BANNER, 1)));
					break;
				case HAVEN:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.HAVEN_TOKENS, 10), new ItemStack(BlockRegister.UTOPIAN_BANNER, 1)));
					break;
				case IROMINE:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.IROMINE_TOKENS, 10), new ItemStack(BlockRegister.MECHA_BANNER, 1)));
					break;
				case LBOREAN:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.BOREAN_TOKENS, 10), new ItemStack(BlockRegister.BOREIC_BANNER, 1)));
					break;
				case LELYETIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LELYETIA_TOKENS, 10), new ItemStack(BlockRegister.LELYETIAN_BANNER, 1)));
					break;
				case LUNALUS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.LUNAR_TOKENS, 10), new ItemStack(BlockRegister.LUNAR_BANNER, 1)));
					break;
				case MYSTERIUM:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.MYSTERIUM_TOKENS, 10), new ItemStack(BlockRegister.FUNGAL_BANNER, 1)));
					break;
				case NETHER:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.NETHER_TOKENS, 10), new ItemStack(BlockRegister.NETHER_BANNER, 1)));
					break;
				case OVERWORLD:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 10), new ItemStack(BlockRegister.VOID_BANNER, 1)));
					break;
				case PRECASIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.PRECASIAN_TOKENS, 10), new ItemStack(BlockRegister.ANCIENT_BANNER, 1)));
					break;
				case RUNANDOR:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.RUNANDOR_TOKENS, 10), new ItemStack(BlockRegister.RUNIC_BANNER, 1)));
					break;
				case SHYRELANDS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SHYRELANDS_TOKENS, 10), new ItemStack(BlockRegister.SHYRE_BANNER, 1)));
					break;
				case VOX_PONDS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.VOX_PONDS_TOKENS, 10), new ItemStack(BlockRegister.VOX_BANNER, 1)));
					break;
				case ANCIENT_CAVERN:
				case IMMORTALLIS:
				case THE_END:
					break;
			}
		}

		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 10), new ItemStack(BlockRegister.CREATION_BANNER, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 10), new ItemStack(BlockRegister.ENERGY_BANNER, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 10), new ItemStack(BlockRegister.SOUL_BANNER, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.SILVER_COIN, 10), new ItemStack(BlockRegister.BLOOD_BANNER, 1)));
	}
}
