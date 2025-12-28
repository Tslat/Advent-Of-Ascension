package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;
import java.util.Optional;

public class RunicStaff extends AoAStaff<PlayerEnderChestContainer> {
	public RunicStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<PlayerEnderChestContainer> checkPreconditions(LivingEntity caster, ItemStack staff) {
		return Optional.ofNullable(caster instanceof Player pl ? pl.getEnderChestInventory() : null);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, PlayerEnderChestContainer args) {
		((Player)caster).openMenu(new SimpleMenuProvider((id, player, inventory) -> ChestMenu.threeRows(id, player, args), Component.translatable("container.enderchest")));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
