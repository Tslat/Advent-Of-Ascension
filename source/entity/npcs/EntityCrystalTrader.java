package net.tslat.aoa3.entity.npcs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityCrystalTrader extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityCrystalTrader(World world) {
		super(world, entityWidth, 2.0f);
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
	protected Enums.ModGuis getTraderGui() {
		return Enums.ModGuis.TRADER_CRYSTAL_TRADER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.crystal_trader." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalBlue, 5), new ItemStack(ItemRegister.tokensCrystevia, 32)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalGreen, 5), new ItemStack(ItemRegister.tokensCrystevia, 32)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalPurple, 5), new ItemStack(ItemRegister.tokensCrystevia, 32)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalRed, 5), new ItemStack(ItemRegister.tokensCrystevia, 32)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalWhite, 5), new ItemStack(ItemRegister.tokensCrystevia, 32)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalYellow, 5), new ItemStack(ItemRegister.tokensCrystevia, 32)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalYellow, 60), new ItemStack(ItemRegister.crystalGreen, 60), new ItemStack(WeaponRegister.gunConstruct)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalBlue, 60), new ItemStack(ItemRegister.crystalPurple, 60), new ItemStack(WeaponRegister.gunCrystalCarver)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalBlue, 60), new ItemStack(ItemRegister.crystalWhite, 60), new ItemStack(WeaponRegister.shotgunBlastBarrel)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalPurple, 60), new ItemStack(ItemRegister.crystalYellow, 60), new ItemStack(WeaponRegister.greatbladeCrystal)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalPurple, 40), new ItemStack(ItemRegister.crystalRed, 40), new ItemStack(ItemRegister.giantCrystal)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalGreen, 40), new ItemStack(ItemRegister.crystalRed, 40), new ItemStack(ArmourRegister.CrystallisBody)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalBlue, 40), new ItemStack(ItemRegister.crystalPurple, 40), new ItemStack(ArmourRegister.CrystallisHelmet)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalYellow, 40), new ItemStack(ItemRegister.crystalWhite, 40), new ItemStack(ArmourRegister.CrystallisBoots)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.crystalWhite, 40), new ItemStack(ItemRegister.crystalRed, 40), new ItemStack(ArmourRegister.CrystallisLegs)));

		return newList;
	}
}
