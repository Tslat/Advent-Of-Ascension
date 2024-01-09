package net.tslat.aoa3.common.registration;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.block.functional.misc.CarvedRuneOfPower;
import net.tslat.aoa3.content.entity.projectile.thrown.*;
import net.tslat.aoa3.content.item.misc.BlankRealmstone;
import net.tslat.aoa3.content.item.misc.Realmstone;

public final class AoADispensables {
	public static void lateInit() {
		registerFluidDispensables();
		registerProjectileDispensables();
		registerMiscDispensables();
	}

	private static void registerProjectileDispensables() {
		DispenserBlock.registerBehavior(AoAWeapons.HELLFIRE.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new HellfireEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.GRENADE.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new GrenadeEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.CHAKRAM.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new ChakramEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.GOO_BALL.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new GooBallEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.RUNIC_BOMB.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new RunicBombEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.VULKRAM.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new VulkramEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.SLICE_STAR.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new SliceStarEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});

		DispenserBlock.registerBehavior(AoAWeapons.HARDENED_PARAPIRANHA.get(), new AbstractProjectileDispenseBehavior() {
			@Override
			protected Projectile getProjectile(Level world, Position dispenserPos, ItemStack stack) {
				return new HardenedParapiranhaEntity(world, dispenserPos.x(), dispenserPos.y(), dispenserPos.z());
			}
		});
	}

	private static void registerMiscDispensables() {
		DispenseItemBehavior realmstoneBehaviour = (source, stack) -> {
			Direction direction = source.state().getValue(DispenserBlock.FACING);
			BlockPos pos = source.pos().offset(direction.getStepX(), direction.getStepY(), direction.getStepZ());

			if (source.level().getBlockState(pos).getBlock() instanceof CarvedRuneOfPower) {
				if (stack.getItem() instanceof Realmstone) {
					CarvedRuneOfPower.fillPortal(source.level(), pos, direction, stack, null);
				}
				else if (stack.getItem() instanceof BlankRealmstone) {
					CarvedRuneOfPower.clearPortal(source.level(), pos, direction, stack, null);
				}

				return stack;
			}

			Position position = DispenserBlock.getDispensePosition(source);
			ItemStack itemstack = stack.split(1);

			DefaultDispenseItemBehavior.spawnItem(source.level(), itemstack, 6, direction, position);
			source.level().levelEvent(1000, source.pos(), 0);
			source.level().levelEvent(2000, source.pos(), direction.get3DDataValue());

			return stack;
		};

		DispenserBlock.registerBehavior(AoAItems.NETHER_REALMSTONE.get(), realmstoneBehaviour);
		DispenserBlock.registerBehavior(AoAItems.BLANK_REALMSTONE.get(), realmstoneBehaviour);
	}

	private static void registerFluidDispensables() {
		DefaultDispenseItemBehavior fluidDispenser = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultBehaviour = new DefaultDispenseItemBehavior();

			@Override
			protected ItemStack execute(BlockSource source, ItemStack stack) {
				BucketItem bucket = (BucketItem)stack.getItem();
				BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
				Level world = source.level();

				if (bucket.emptyContents(null, world, pos, null)) {
					bucket.checkExtraContent(null, world, stack, pos);

					return new ItemStack(Items.BUCKET);
				}
				else {
					return this.defaultBehaviour.dispense(source, stack);
				}
			}
		};

		DispenserBlock.registerBehavior(AoARegistries.ITEMS.getEntry(new ResourceLocation(AdventOfAscension.MOD_ID, "candied_water_bucket")), fluidDispenser);
		DispenserBlock.registerBehavior(AoARegistries.ITEMS.getEntry(new ResourceLocation(AdventOfAscension.MOD_ID, "toxic_waste_bucket")), fluidDispenser);
		DispenserBlock.registerBehavior(AoARegistries.ITEMS.getEntry(new ResourceLocation(AdventOfAscension.MOD_ID, "clear_water_bucket")), fluidDispenser);
	}
}
