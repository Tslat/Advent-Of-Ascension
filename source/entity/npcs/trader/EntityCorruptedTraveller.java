package net.tslat.aoa3.entity.npcs.trader;

import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityCorruptedTraveller extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityCorruptedTraveller(World world) {
		super(world, entityWidth, 2.0f);

		setGlowing(ConfigurationUtil.MainConfig.easyCorruptedTravellers);
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityCorruptedTraveller;
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_CORRUPTED_TRAVELLER;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Override
	protected boolean canDespawn() {
		return world.provider.getDimension() != 0;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {}
}
