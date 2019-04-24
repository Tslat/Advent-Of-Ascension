package net.tslat.aoa3.entity.npcs;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoATrader;
import net.tslat.aoa3.entity.base.AoATraderRecipe;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityTokenCollector extends AoATrader {
	public static final float entityWidth = 0.5625f;

	public EntityTokenCollector(World world) {
		super(world, entityWidth, 2.0f);

		this.setEntityInvulnerable(true);
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
		return Enums.ModGuis.TRADER_TOKEN_COLLECTOR;
	}

	@Override
	protected boolean isFixedTradesList() {
		return true;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.token_collector." + rand.nextInt(5));
	}

	@Override
	protected ArrayList<AoATraderRecipe> getNewTrades(final ArrayList<AoATraderRecipe> newList) {
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 3), new ItemStack(ItemRegister.vulcaneAugmentFire)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 6), new ItemStack(ItemRegister.vulcaneAugmentImpairment)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 10), new ItemStack(ItemRegister.vulcaneAugmentPoison)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 14), new ItemStack(ItemRegister.vulcaneAugmentPower)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 20), new ItemStack(ItemRegister.vulcaneAugmentWither)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 30), new ItemStack(ItemRegister.vulcaneAugmentEquality)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 60), new ItemStack(ItemRegister.vulcaneAugmentBattle)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 10), new ItemStack(WeaponRegister.swordVulcammer)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 30), new ItemStack(WeaponRegister.staffEverfight)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 30), new ItemStack(WeaponRegister.staffEvermight)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 40), new ItemStack(WeaponRegister.blasterOdious)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon, 50), new ItemStack(WeaponRegister.sniperDeadlock)));
		newList.add(new AoATraderRecipe(new ItemStack(ItemRegister.tokensDungeon), new ItemStack(BlockRegister.bannerImmortal, 3)));

		return newList;
	}
}
