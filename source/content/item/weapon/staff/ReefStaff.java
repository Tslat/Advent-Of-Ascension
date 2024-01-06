package net.tslat.aoa3.content.item.weapon.staff;

import com.google.common.base.Suppliers;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoANetworking;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.TagUtil;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ReefStaff extends BaseStaff<Boolean> {
	private static final Supplier<Map<Block, Block>> CORALS = Suppliers.memoize(() -> Util.make(new IdentityHashMap<>(), map -> {
		map.put(Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK);
		map.put(Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK);
		map.put(Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK);
		map.put(Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK);
		map.put(Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK);
		map.put(Blocks.DEAD_TUBE_CORAL, Blocks.TUBE_CORAL);
		map.put(Blocks.DEAD_BRAIN_CORAL, Blocks.BRAIN_CORAL);
		map.put(Blocks.DEAD_BUBBLE_CORAL, Blocks.BUBBLE_CORAL);
		map.put(Blocks.DEAD_FIRE_CORAL, Blocks.FIRE_CORAL);
		map.put(Blocks.DEAD_HORN_CORAL, Blocks.HORN_CORAL);
		map.put(Blocks.DEAD_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_FAN);
		map.put(Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_FAN);
		map.put(Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN);
		map.put(Blocks.DEAD_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_FAN);
		map.put(Blocks.DEAD_HORN_CORAL_FAN, Blocks.HORN_CORAL_FAN);
		map.put(Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN);
		map.put(Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN);
		map.put(Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN);
		map.put(Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN);
		map.put(Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN);
	}));

	public ReefStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_REEF_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 2);
		runes.put(AoAItems.WATER_RUNE.get(), 1);
	}

	@Nullable
	@Override
	public Boolean checkPreconditions(LivingEntity caster, ItemStack staff) {
		return caster.isInWater() ? true : null;
	}

	@Override
	public void cast(Level level, ItemStack staff, LivingEntity caster, Boolean args) {
		Vec3 lookAngle = caster.getLookAngle();

		if (lookAngle.horizontalDistanceSqr() > 0.0000001d || lookAngle.y != -1) {
			caster.setDeltaMovement(lookAngle.scale(3));
			caster.hurtMarked = true;

			for (int i = 0; i < 10; i++) {
				AoAScheduler.scheduleSyncronisedTask(() -> AoANetworking.sendToAllPlayersTrackingEntity(new ServerParticlePacket(ParticleBuilder.forRandomPosInEntity(ParticleTypes.BUBBLE_COLUMN_UP, caster).spawnNTimes(3)), caster), i + 1);
			}
		}

		if (level instanceof ServerLevel serverLevel)
			doPlantGrowth(serverLevel, caster);
	}

	private static void doPlantGrowth(ServerLevel level, LivingEntity caster) {
		final Map<Block, Block> coralMap = CORALS.get();
		final ServerParticlePacket packet = new ServerParticlePacket();

		for (int i = 0; i < 50; i++) {
			BlockPos pos = BlockPos.containing(caster.position().add(caster.getRandom().nextGaussian() * 3, caster.getRandom().nextGaussian() * 3, caster.getRandom().nextGaussian() * 3));
			BlockState state = level.getBlockState(pos);

			if (coralMap.containsKey(state.getBlock())) {
				level.setBlockAndUpdate(pos, coralMap.get(state.getBlock()).defaultBlockState());
			}
			else if (state.is(Blocks.KELP) || state.is(Blocks.SEAGRASS) || state.is(Blocks.KELP_PLANT)) {
				((BonemealableBlock)state.getBlock()).performBonemeal((ServerLevel)level, level.random, pos, state);
			}
			else if (state.getBlock() == Blocks.WATER && level.getFluidState(pos).getAmount() == FluidState.AMOUNT_FULL) {
				if (level.random.nextInt(10) == 0) {

					BlockState pickles = Blocks.SEA_PICKLE.defaultBlockState();

					if (!pickles.canSurvive(level, pos))
						continue;

					BlockState existingState = level.getBlockState(pos);

					if (existingState.getBlock() == Blocks.SEA_PICKLE)
						pickles.setValue(SeaPickleBlock.PICKLES, Math.min(SeaPickleBlock.MAX_PICKLES, existingState.getValue(SeaPickleBlock.PICKLES) + 1));

					level.setBlockAndUpdate(pos, pickles);
				}
				else {
					BlockState growthState = level.random.nextBoolean() ? Blocks.SEAGRASS.defaultBlockState() : Blocks.KELP_PLANT.defaultBlockState();

					if (level.random.nextBoolean()) {
						TagKey<Block> tag = switch (level.random.nextInt(4)) {
							case 0 -> BlockTags.UNDERWATER_BONEMEALS;
							case 1 -> BlockTags.CORAL_PLANTS;
							case 2 -> BlockTags.WALL_CORALS;
							default -> BlockTags.CORALS;
						};

						growthState = TagUtil.getTagContents(tag, level)
								.flatMap(set -> set.getRandomElement(level.random))
								.map(holder -> holder.value().defaultBlockState())
								.orElse(growthState);
					}

					if (growthState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
						Vec3 angle = caster.getEyePosition().vectorTo(Vec3.atCenterOf(pos));
						growthState = growthState.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.getNearest(angle.x, angle.y, angle.z));

						for (int j = 0; j < 4 && !growthState.canSurvive(level, pos); j++) {
							growthState = growthState.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.Plane.HORIZONTAL.getRandomDirection(level.random));
						}
					}

					if (!growthState.canSurvive(level, pos))
						continue;

					level.setBlockAndUpdate(pos, growthState);
				}
			}
			else {
				continue;
			}

			packet.particle(ParticleBuilder.forRandomPosInBounds(ParticleTypes.HAPPY_VILLAGER, new AABB(pos))
					.spawnNTimes(10));
			packet.particle(ParticleBuilder.forRandomPosInBounds(ParticleTypes.BUBBLE_COLUMN_UP, new AABB(pos))
					.spawnNTimes(10));
		}

		if (!packet.isEmpty())
			AoANetworking.sendToAllPlayersTrackingEntity(packet, caster);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
