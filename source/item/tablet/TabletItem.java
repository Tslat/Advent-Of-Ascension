package net.tslat.aoa3.item.tablet;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.entity.tablet.SoulTabletEntity;
import net.tslat.aoa3.item.armour.AdventArmour;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class TabletItem extends Item {
	private final float initialSoulCost;
	private final float perTickSoulCost;
	private final int animaLevelReq;
	private final int effectRadius;

	public TabletItem(float placementCost, float tickSoulDrain, int levelReq, int effectRadius) {
		super(new Item.Properties().group(AoAItemGroups.TABLETS).maxStackSize(1));

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
	public ActionResultType onItemUse(ItemUseContext context) {
		BlockPos pos = context.getPos();
		World world = context.getWorld();

		BlockState targetBlockState = context.getWorld().getBlockState(pos);

		if (context.getFace() != Direction.UP || !targetBlockState.isSolidSide(world, pos, Direction.UP))
			return ActionResultType.FAIL;

		if (context.getPlayer() instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity)context.getPlayer();
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);

			if (player.isCreative() || plData.stats().getLevel(Skills.ANIMA) >= animaLevelReq) {
				float soulCost = initialSoulCost * (1 - ((plData.stats().getLevel(Skills.ANIMA) - 1) / 200f)) * (PlayerUtil.isWearingFullSet(player, AdventArmour.Type.ANIMA) ? 0.5f : 1f);
				SoulTabletEntity tabletEntity = getTabletEntity(world, player);
				VoxelShape blockBoundingBox = targetBlockState.getCollisionShape(world, pos);

				tabletEntity.setPositionAndRotation(context.getHitVec().getX(), pos.getY() + (blockBoundingBox.isEmpty() ? 0 : blockBoundingBox.getEnd(Direction.Axis.Y)), context.getHitVec().getZ(), player.rotationYaw, 0);

				if (world.checkNoEntityCollision(tabletEntity, VoxelShapes.create(tabletEntity.getBoundingBox()))) {
					if (plData.stats().consumeResource(Resources.SOUL, soulCost, false)) {
						world.addEntity(tabletEntity);

						if (!player.isCreative())
							player.getHeldItem(context.getHand()).shrink(1);

						return ActionResultType.SUCCESS;
					}
				}
			}
			else {
				PlayerUtil.notifyPlayerOfInsufficientLevel(player, Skills.ANIMA, animaLevelReq);
			}
		}

		return ActionResultType.PASS;
	}

	protected abstract SoulTabletEntity getTabletEntity(World world, ServerPlayerEntity placer);

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedLevelRestrictedDescriptionText(Skills.ANIMA, animaLevelReq));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.placementCost", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, String.valueOf(initialSoulCost * (1 - ((Math.min(100, AdventGuiTabPlayer.getSkillLevel(Skills.ANIMA)) - 1) / 200f)))));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.usageCost", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, String.valueOf(((int)(perTickSoulCost * 2000f)) / 100f)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.tablet.radius", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO, String.valueOf(effectRadius)));
	}
}
