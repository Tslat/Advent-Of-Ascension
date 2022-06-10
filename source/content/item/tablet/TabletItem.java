package net.tslat.aoa3.content.item.tablet;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.content.entity.tablet.SoulTabletEntity;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class TabletItem extends Item {
	private final float initialSoulCost;
	private final float perTickSoulCost;
	private final int animaLevelReq;
	private final int effectRadius;

	public TabletItem(float placementCost, float tickSoulDrain, int levelReq, int effectRadius) {
		super(new Item.Properties().tab(AoAItemGroups.TABLETS).stacksTo(1));

		this.initialSoulCost = placementCost;
		this.perTickSoulCost = tickSoulDrain;
		this.animaLevelReq = levelReq;
		this.effectRadius = effectRadius;
	}

	public final float getSoulDrain() {
		return perTickSoulCost;
	}

	public int getEffectRadius() {
		return effectRadius;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos pos = context.getClickedPos();
		Level world = context.getLevel();

		BlockState targetBlockState = context.getLevel().getBlockState(pos);

		if (context.getClickedFace() != Direction.UP || !targetBlockState.isFaceSturdy(world, pos, Direction.UP))
			return InteractionResult.FAIL;

		if (context.getPlayer() instanceof ServerPlayer) {
			ServerPlayer player = (ServerPlayer)context.getPlayer();
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			/*if (player.isCreative() || plData.stats().getLevel(Skills.ANIMA) >= animaLevelReq) {
				float soulCost = initialSoulCost * (1 - ((plData.stats().getLevel(Skills.ANIMA) - 1) / 200f)) * (PlayerUtil.isWearingFullSet(player, AdventArmour.Type.ANIMA) ? 0.5f : 1f);
				SoulTabletEntity tabletEntity = getTabletEntity(world, player);
				VoxelShape blockBoundingBox = targetBlockState.getCollisionShape(world, pos);

				tabletEntity.absMoveTo(context.getClickLocation().x(), pos.getY() + (blockBoundingBox.isEmpty() ? 0 : blockBoundingBox.max(Direction.Axis.Y)), context.getClickLocation().z(), player.getYRot(), 0);

				if (world.isUnobstructed(tabletEntity, Shapes.create(tabletEntity.getBoundingBox()))) {
					if (plData.stats().consumeResource(AoAResource.SOUL, soulCost, false)) {
						world.addFreshEntity(tabletEntity);

						if (!player.isCreative())
							player.getItemInHand(context.getHand()).shrink(1);

						return InteractionResult.SUCCESS;
					}
				}
			}
			else {
				PlayerUtil.notifyPlayerOfInsufficientLevel(player, Skills.ANIMA, animaLevelReq);
			}*/ // TODO
		}

		return InteractionResult.PASS;
	}

	protected abstract SoulTabletEntity getTabletEntity(Level world, ServerPlayer placer);

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		//tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.ANIMA, animaLevelReq));
		//tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.placementCost", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, Component.literal(String.valueOf(initialSoulCost * (1 - ((Math.min(100, AdventGuiTabPlayer.getSkillLevel(Skills.ANIMA)) - 1) / 200f))))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.usageCost", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, Component.literal(String.valueOf(((int)(perTickSoulCost * 2000f)) / 100f))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.radius", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, Component.literal(String.valueOf(effectRadius))));
	}
}
