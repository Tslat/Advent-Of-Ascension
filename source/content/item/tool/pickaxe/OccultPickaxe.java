package net.tslat.aoa3.content.item.tool.pickaxe;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.client.render.entity.misc.OccultBlockRenderer;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.util.LocaleUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OccultPickaxe extends BasePickaxe {
	public OccultPickaxe() {
		super(AoATiers.OCCULT, -2, AttackSpeed.PICKAXE, new Item.Properties().durability(AoATiers.OCCULT.getUses()).rarity(Rarity.RARE));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (world.isClientSide()) {
			List<OccultBlockRenderer.OccultBlock> blocks = new ObjectArrayList<>();

			for (int i = (int)(player.getX() - 4.0); i < (int)(player.getX() + 4.0); ++i) {
				for (int j = (int)(player.getY() - 4.0); j < (int)(player.getY() + 4.0); ++j) {
					for (int k = (int)(player.getZ() - 4.0); k < (int)(player.getZ() + 4.0); ++k) {
						BlockPos pos = new BlockPos(i, j, k);
						BlockState state = world.getBlockState(pos);

						if (state.is(Tags.Blocks.ORES))
							blocks.add(new OccultBlockRenderer.OccultBlock(pos, state));
					}
				}
			}

			ClientOperations.addOccultBlocks(GlobalEvents.tick + 150, blocks);
		}

		InteractionResultHolder<ItemStack> result = super.use(world, player, hand);

		if (result.getResult() == InteractionResult.FAIL)
			return InteractionResultHolder.pass(result.getObject());

		return result;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
