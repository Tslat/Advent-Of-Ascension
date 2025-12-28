package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;
import java.util.Optional;

public class CrystalStaff extends AoAStaff<List<Player>> {
	public CrystalStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<List<Player>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<Player> players = EntityRetrievalUtil.getPlayers(caster, 20, entity -> entity.getHealth() < entity.getMaxHealth() && PlayerUtil.shouldPlayerBeAffected(entity));

		return Optional.ofNullable(players.isEmpty() ? null : players);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, List<Player> args) {
		float currentTotalHealth = 0;
		float currentMaxHealth = 0;

		for (Player pl : args) {
			currentMaxHealth += pl.getMaxHealth();
			currentTotalHealth += pl.getHealth();
		}

		float healthPerPlayer = (currentMaxHealth * (currentTotalHealth / currentMaxHealth * 1.25f)) / (float)((List<Player>)args).size();

		for (Player pl : args) {
			pl.setHealth(healthPerPlayer);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
