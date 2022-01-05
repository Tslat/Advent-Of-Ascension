package net.tslat.aoa3.object.item.tablet;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.object.entity.tablet.SoulTabletEntity;
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
	public ActionResultType useOn(ItemUseContext context) {
		BlockPos pos = context.getClickedPos();
		World world = context.getLevel();

		BlockState targetBlockState = context.getLevel().getBlockState(pos);

		if (context.getClickedFace() != Direction.UP || !targetBlockState.isFaceSturdy(world, pos, Direction.UP))
			return ActionResultType.FAIL;

		if (context.getPlayer() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity)context.getPlayer();
			ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			/*if (player.isCreative() || plData.stats().getLevel(Skills.ANIMA) >= animaLevelReq) {
				float soulCost = initialSoulCost * (1 - ((plData.stats().getLevel(Skills.ANIMA) - 1) / 200f)) * (PlayerUtil.isWearingFullSet(player, AdventArmour.Type.ANIMA) ? 0.5f : 1f);
				SoulTabletEntity tabletEntity = getTabletEntity(world, player);
				VoxelShape blockBoundingBox = targetBlockState.getCollisionShape(world, pos);

				tabletEntity.absMoveTo(context.getClickLocation().x(), pos.getY() + (blockBoundingBox.isEmpty() ? 0 : blockBoundingBox.max(Direction.Axis.Y)), context.getClickLocation().z(), player.yRot, 0);

				if (world.isUnobstructed(tabletEntity, VoxelShapes.create(tabletEntity.getBoundingBox()))) {
					if (plData.stats().consumeResource(AoAResource.SOUL, soulCost, false)) {
						world.addFreshEntity(tabletEntity);

						if (!player.isCreative())
							player.getItemInHand(context.getHand()).shrink(1);

						return ActionResultType.SUCCESS;
					}
				}
			}
			else {
				PlayerUtil.notifyPlayerOfInsufficientLevel(player, Skills.ANIMA, animaLevelReq);
			}*/ // TODO
		}

		return ActionResultType.PASS;
	}

	protected abstract SoulTabletEntity getTabletEntity(World world, ServerPlayerEntity placer);

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		//tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.ANIMA, animaLevelReq));
		//tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.placementCost", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, new StringTextComponent(String.valueOf(initialSoulCost * (1 - ((Math.min(100, AdventGuiTabPlayer.getSkillLevel(Skills.ANIMA)) - 1) / 200f))))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.usageCost", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, new StringTextComponent(String.valueOf(((int)(perTickSoulCost * 2000f)) / 100f))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.radius", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, new StringTextComponent(String.valueOf(effectRadius))));
	}
}
