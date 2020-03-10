package net.tslat.aoa3.entity.npcs.skillmaster;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoATraderRecipe;

import javax.annotation.Nullable;

public class EntityHaulingMaster extends EntitySkillMaster {
	public EntityHaulingMaster(World world) {
		super(world);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityHaulingMaster;
	}

	@Override
	protected void getTradesList(final NonNullList<AoATraderRecipe> newTradesList) {
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver), new ItemStack(ArmourRegister.haulingHelmet)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver), new ItemStack(ArmourRegister.haulingBody)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver), new ItemStack(ArmourRegister.haulingLegs)));
		newTradesList.add(new AoATraderRecipe(new ItemStack(ItemRegister.coinLunaver), new ItemStack(ArmourRegister.haulingBoots)));
	}
}
