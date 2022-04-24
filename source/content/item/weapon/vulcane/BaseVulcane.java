package net.tslat.aoa3.content.item.weapon.vulcane;

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
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseVulcane extends Item {
	protected double baseDmg;

	public BaseVulcane(double dmg, int durability) {
		super(new Item.Properties().tab(AoAItemGroups.VULCANES).durability(durability));

		this.baseDmg = dmg;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.BOW;
	}

	public double getDamage() {
		return baseDmg;
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (!(player instanceof ServerPlayerEntity))
			return ActionResult.fail(stack);

		AoAResource.Instance rage = PlayerUtil.getResource((ServerPlayerEntity)player, AoAResources.RAGE.get());

		if (!rage.hasAmount(50) || player.getLastHurtByMob() == null)
			return ActionResult.fail(stack);

		return activate(rage, stack, hand);
	}

	public ActionResult<ItemStack> activate(AoAResource.Instance rage, ItemStack vulcane, Hand hand) {
		PlayerEntity pl = rage.getPlayerDataManager().player();
		float damage = (float)getDamage() * (1 + ((rage.getCurrentValue() - 50) / 100));
		float targetHealth = pl.getLastHurtByMob().getHealth();

		if (DamageUtil.dealVulcaneDamage(pl.getLastHurtByMob(), pl, damage)) {
			doAdditionalEffect(pl.getLastHurtByMob(), pl, Math.min(damage, targetHealth));
			pl.level.playSound(null, pl.getX(), pl.getY(), pl.getZ(), AoASounds.ITEM_VULCANE_USE.get(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			ItemUtil.damageItem(vulcane, pl, hand);
			rage.consume(rage.getCurrentValue(), true);

			return ActionResult.success(vulcane);
		}

		return ActionResult.fail(vulcane);
	}

	public void doAdditionalEffect(LivingEntity target, PlayerEntity player, float damageDealt) {}

	@Override
	public int getEnchantmentValue() {
		return 8;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(1, LocaleUtil.getFormattedItemDescriptionText("items.description.damage.true", LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, LocaleUtil.numToComponent(baseDmg)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.vulcane.use", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.vulcane.target", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
