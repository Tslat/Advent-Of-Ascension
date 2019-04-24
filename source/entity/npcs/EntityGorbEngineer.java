package net.tslat.aoa3.entity.npcs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityGorbEngineer extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityGorbEngineer(World world) {
		super(world, entityWidth, 1.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
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
		return Enums.ModGuis.TRADER_GORB_ENGINEER;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.gorb_engineer." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.nightmareFlakes, 10), new ItemStack(ItemRegister.ghostlyPowder, 10), new ItemStack(ArmourRegister.NightmareBody)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.nightmareFlakes, 10), new ItemStack(ItemRegister.ghostlyPowder, 10), new ItemStack(ArmourRegister.NightmareLegs)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.nightmareFlakes, 10), new ItemStack(ItemRegister.ghostlyPowder, 10), new ItemStack(ArmourRegister.NightmareBoots)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.nightmareFlakes, 10), new ItemStack(ItemRegister.ghostlyPowder, 10), new ItemStack(ArmourRegister.NightmareHelmet)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 10), new ItemStack(ItemRegister.trollSkull, 10), new ItemStack(ArmourRegister.PhantasmBody)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 10), new ItemStack(ItemRegister.trollSkull, 10), new ItemStack(ArmourRegister.PhantasmBoots)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 10), new ItemStack(ItemRegister.trollSkull, 10), new ItemStack(ArmourRegister.PhantasmHelmet)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.phantasm, 10), new ItemStack(ItemRegister.trollSkull, 10), new ItemStack(ArmourRegister.PhantasmLegs)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.runeStone, 10), new ItemStack(ArmourRegister.RunicBody)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.runeStone, 10), new ItemStack(ArmourRegister.RunicLegs)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.runeStone, 10), new ItemStack(ArmourRegister.RunicBoots)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.runeStone, 10), new ItemStack(ArmourRegister.RunicHelmet)));

		return newList;
	}
}
