package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class LuxonScythe extends BaseGreatblade {
	public LuxonScythe() {
		super(AoATiers.LUXON_SCYTHE, AttackSpeed.forAttacksPerSecond(1.2f));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level.isClientSide) {
			AoAResource.Instance spirit = target instanceof ServerPlayer ? PlayerUtil.getResource((ServerPlayer)target, AoAResources.SPIRIT.get()) : null;
			float consumeAmount = (spirit != null ? Math.min(40, spirit.getCurrentValue()) : 40) * attackCooldown;

			if (consumeAmount > 0) {
				if (spirit != null && !spirit.consume(consumeAmount, true))
					return;

				if (attacker instanceof ServerPlayer)
					PlayerUtil.addResourceToPlayer((ServerPlayer)attacker, AoAResources.SPIRIT.get(), consumeAmount);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.scythe", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
