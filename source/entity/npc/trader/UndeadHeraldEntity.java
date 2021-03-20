package net.tslat.aoa3.entity.npc.trader;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.npc.AoATraderRecipe;
import net.tslat.aoa3.util.WorldUtil;

public class UndeadHeraldEntity extends AoATrader {
	public UndeadHeraldEntity(EntityType<? extends CreatureEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 100;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return WorldUtil.isWorld(level, AoADimensions.LBOREAN.key);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return tickCount > 72000;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		switch (AoADimensions.getDim(level.dimension())) {
			case ABYSS:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.ABYSS_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.SHADOW_BANNER.get(), 1), 0, 9999));
				break;
			case BARATHOS:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.BARON_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.BARON_BANNER.get(), 1), 0, 9999));
				break;
			case CANDYLAND:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CANDYLAND_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.CANDY_BANNER.get(), 1), 0, 9999));
				break;
			case CELEVE:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CELEVE_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.CLOWN_BANNER.get(), 1), 0, 9999));
				break;
			case CREEPONIA:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CREEPONIA_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.CREEPY_BANNER.get(), 1), 0, 9999));
				break;
			case CRYSTEVIA:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.CRYSTEVIA_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.CRYSTAL_BANNER.get(), 1), 0, 9999));
				break;
			case DEEPLANDS:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DEEPLANDS_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.DEEP_BANNER.get(), 1), 0, 9999));
				break;
			case DUSTOPIA:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.DUSTOPIA_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.DUSTOPIAN_BANNER.get(), 1), 0, 9999));
				break;
			case GARDENCIA:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GARDENCIA_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.ROSIDIAN_BANNER.get(), 1), 0, 9999));
				break;
			case GRECKON:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.GRECKON_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.HAUNTED_BANNER.get(), 1), 0, 9999));
				break;
			case HAVEN:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.HAVEN_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.UTOPIAN_BANNER.get(), 1), 0, 9999));
				break;
			case IROMINE:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.IROMINE_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.MECHA_BANNER.get(), 1), 0, 9999));
				break;
			case LELYETIA:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LELYETIA_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.LELYETIAN_BANNER.get(), 1), 0, 9999));
				break;
			case LUNALUS:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.LUNAR_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.LUNAR_BANNER.get(), 1), 0, 9999));
				break;
			case MYSTERIUM:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.MYSTERIUM_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.FUNGAL_BANNER.get(), 1), 0, 9999));
				break;
			case NETHER:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.NETHER_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.NETHER_BANNER.get(), 1), 0, 9999));
				break;
			case OVERWORLD:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.VOID_BANNER.get(), 1), 0, 9999));
				break;
			case PRECASIA:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.PRECASIAN_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.ANCIENT_BANNER.get(), 1), 0, 9999));
				break;
			case RUNANDOR:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.RUNANDOR_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.RUNIC_BANNER.get(), 1), 0, 9999));
				break;
			case SHYRELANDS:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SHYRELANDS_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.SHYRE_BANNER.get(), 1), 0, 9999));
				break;
			case VOX_PONDS:
				newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.VOX_PONDS_TOKENS.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.VOX_BANNER.get(), 1), 0, 9999));
				break;
			case ANCIENT_CAVERN:
			case IMMORTALLIS:
			default:
				break;
		}

		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.CREATION_BANNER.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.ENERGY_BANNER.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.SOUL_BANNER.get(), 1), 0, 9999));
		newTradesList.add(new AoATraderRecipe(new ItemStack(AoAItems.SILVER_COIN.get(), 10), ItemStack.EMPTY, new ItemStack(AoABlocks.BLOOD_BANNER.get(), 1), 0, 9999));
	}
}
