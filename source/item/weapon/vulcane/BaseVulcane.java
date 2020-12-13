package net.tslat.aoa3.item.weapon.vulcane;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseVulcane extends Item {
	protected double baseDmg;

	public BaseVulcane(double dmg, int durability) {
		super(new Item.Properties().group(AoAItemGroups.VULCANES).maxDamage(durability));

		this.baseDmg = dmg;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	public double getDamage() {
		return baseDmg;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (!(player instanceof ServerPlayerEntity))
			return ActionResult.resultFail(stack);

		PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);

		if (!plData.isRevengeActive())
			return ActionResult.resultFail(stack);

		return activate(plData, stack, hand);
	}

	public ActionResult<ItemStack> activate(PlayerDataManager plData, ItemStack vulcane, Hand hand) {
		PlayerEntity pl = plData.player();

		if (DamageUtil.dealVulcaneDamage(plData.getRevengeTarget(), pl, (float)baseDmg)) {
			doAdditionalEffect(plData.getRevengeTarget(), pl);
			pl.world.playSound(null, pl.getPosX(), pl.getPosY(), pl.getPosZ(), AoASounds.ITEM_VULCANE_USE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			ItemUtil.damageItem(vulcane, pl, hand);
			plData.disableRevenge();

			return ActionResult.resultSuccess(vulcane);
		}

		return ActionResult.resultFail(vulcane);
	}

	public void doAdditionalEffect(LivingEntity target, PlayerEntity player) {}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.true", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Double.toString(baseDmg)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.vulcane.use", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
