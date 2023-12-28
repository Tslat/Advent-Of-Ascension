package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HolySword extends BaseSword {
	public HolySword() {
		super(AoATiers.HOLY, 0, AttackSpeed.TRIPLE, new Item.Properties().durability(AoATiers.HOLY.getUses()).rarity(Rarity.RARE));
	}


	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (!world.isClientSide)
			WorldUtil.spawnLightning((ServerLevel)world, (ServerPlayer)player, player.getX(), player.getY(), player.getZ(), false, false);

		player.getCooldowns().addCooldown(this, 100);

		return InteractionResultHolder.success(player.getItemInHand(hand));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
