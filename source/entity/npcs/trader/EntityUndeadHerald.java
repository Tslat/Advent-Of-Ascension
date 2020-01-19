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
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_UNDEAD_HERALD;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		Enums.Dimensions dimension = WorldUtil.getDimensionFromId(world.provider.getDimension());

		if (dimension != null) {
			switch (dimension) {
				case ABYSS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensAbyss, 32), new ItemStack(BlockRegister.bannerShadow, 1)));
					break;
				case BARATHOS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensBaron, 32), new ItemStack(BlockRegister.bannerBaron, 1)));
					break;
				case CANDYLAND:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCandyland, 32), new ItemStack(BlockRegister.bannerCandy, 1)));
					break;
				case CELEVE:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCeleve, 32), new ItemStack(BlockRegister.bannerClown, 1)));
					break;
				case CREEPONIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCreeponia, 32), new ItemStack(BlockRegister.bannerCreepy, 1)));
					break;
				case CRYSTEVIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensCrystevia, 32), new ItemStack(BlockRegister.bannerCrystal, 1)));
					break;
				case DEEPLANDS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDeeplands, 32), new ItemStack(BlockRegister.bannerDeep, 1)));
					break;
				case DUSTOPIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDustopia, 32), new ItemStack(BlockRegister.bannerDustopian, 1)));
					break;
				case GARDENCIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensGardencia, 32), new ItemStack(BlockRegister.bannerRosidian, 1)));
					break;
				case GRECKON:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensGreckon, 32), new ItemStack(BlockRegister.bannerHaunted, 1)));
					break;
				case HAVEN:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensHaven, 32), new ItemStack(BlockRegister.bannerUtopian, 1)));
					break;
				case IROMINE:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensIromine, 32), new ItemStack(BlockRegister.bannerMecha, 1)));
					break;
				case LBOREAN:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensBorean, 32), new ItemStack(BlockRegister.bannerBoreic, 1)));
					break;
				case LELYETIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensLelyetia, 32), new ItemStack(BlockRegister.bannerLelyetian, 1)));
					break;
				case LUNALUS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensLunar, 32), new ItemStack(BlockRegister.bannerLunar, 1)));
					break;
				case MYSTERIUM:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensMysterium, 32), new ItemStack(BlockRegister.bannerFungal, 1)));
					break;
				case NETHER:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensNether, 32), new ItemStack(BlockRegister.bannerNether, 1)));
					break;
				case OVERWORLD:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 2), new ItemStack(BlockRegister.bannerVoid, 1)));
					break;
				case PRECASIA:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensPrecasian, 32), new ItemStack(BlockRegister.bannerAncient, 1)));
					break;
				case RUNANDOR:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensRunandor, 32), new ItemStack(BlockRegister.bannerRunic, 1)));
					break;
				case SHYRELANDS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensShyrelands, 32), new ItemStack(BlockRegister.bannerShyre, 1)));
					break;
				case VOX_PONDS:
					newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensVoxPonds, 32), new ItemStack(BlockRegister.bannerVox, 1)));
					break;
				case ANCIENT_CAVERN:
				case IMMORTALLIS:
				case THE_END:
					break;
			}
		}

		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(BlockRegister.bannerCreation, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(BlockRegister.bannerEnergy, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(BlockRegister.bannerSoul, 1)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(BlockRegister.bannerBlood, 1)));
	}
}
