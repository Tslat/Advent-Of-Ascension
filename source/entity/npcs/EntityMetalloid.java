package net.tslat.aoa3.entity.npcs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityMetalloid extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityMetalloid(World world) {
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

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_METALLOID;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.metalloid." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 4), new ItemStack(ItemRegister.amethyst, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 8), new ItemStack(ItemRegister.jade, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinSilver, 10), new ItemStack(ItemRegister.ingotRosite, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinGold, 5), new ItemStack(ItemRegister.sapphire, 1)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.amethyst, 1), new ItemStack(ItemRegister.coinSilver, 2)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.jade, 1), new ItemStack(ItemRegister.coinSilver, 4)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.ingotRosite, 1), new ItemStack(ItemRegister.coinSilver, 5)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.sapphire, 1), new ItemStack(ItemRegister.coinGold, 3)));

		return newList;
	}
}
