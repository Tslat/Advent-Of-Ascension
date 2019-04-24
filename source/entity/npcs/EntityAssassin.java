package net.tslat.aoa3.entity.npcs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

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

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) && WorldUtil.isNaturalOverworldBlock(block);
	}

	@Override
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_ASSASSIN;
	}

	@Override
	protected boolean isOverworldNPC() {
		return true;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.assassin." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableSliceStar, 8)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableGooBall, 8)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableChakram, 8)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableHellfire, 8)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(WeaponRegister.throwableVulkram, 4)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.thornyPetals, 4), new ItemStack(WeaponRegister.throwableSliceStar, 12)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.thornyPetals, 4), new ItemStack(WeaponRegister.throwableChakram, 12)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.thornyPetals, 4), new ItemStack(WeaponRegister.throwableVulkram, 6)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.metalSlug, 16)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinCopper, 2), new ItemStack(ItemRegister.bulletLimonite, 16)));

		return newList;
	}
}
