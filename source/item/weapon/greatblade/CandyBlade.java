package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CandyBlade extends BaseGreatblade {
	public CandyBlade() {
		super(23.0f, AttackSpeed.GREATBLADE, 1450);
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.EAT;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (player.canEat(false)) {
			player.startUsingItem(hand);

			return ActionResult.success(stack);
		}

		return ActionResult.fail(stack);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity pl = (PlayerEntity)entity;
			int foodHealAmount = Math.min(20 - pl.getFoodData().getFoodLevel(), stack.getMaxDamage() - stack.getDamageValue());

			pl.getFoodData().eat(foodHealAmount, 20f);
			ItemUtil.damageItem(stack, entity, foodHealAmount * 4, pl.getUsedItemHand() == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND);
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
