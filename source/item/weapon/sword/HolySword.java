package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.misc.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class HolySword extends BaseSword {
	public HolySword() {
		super(ItemUtil.customItemTier(1000, AttackSpeed.TRIPLE, 0.0f, 4, 10, null),
				new Item.Properties().durability(1000).tab(AoAItemGroups.SWORDS).addToolType(ToolType.get("sword"), 4).rarity(Rarity.RARE));
	}


	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if (!world.isClientSide)
			WorldUtil.spawnLightning((ServerWorld)world, (ServerPlayerEntity)player, player.getX(), player.getY(), player.getZ(), false);

		player.getCooldowns().addCooldown(this, 100);

		return ActionResult.success(player.getItemInHand(hand));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
	}
}
