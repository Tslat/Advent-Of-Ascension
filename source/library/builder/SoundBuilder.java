package net.tslat.aoa3.library.builder;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.AoASoundBuilderPacket;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SoundBuilder {
	private SoundEvent sound;
	private SoundCategory category = SoundCategory.MASTER;
	private World world = null;
	private Vector3d location = null;

	private Entity followingEntity = null;

	private float pitch = 1f;
	private float radius = 16f;

	private int scheduleDelay = 0;
	private boolean applyTimeDilation = false;

	private boolean inWorld = true;
	private boolean loop = false;
	private int loopDelay = 0;

	private Set<PlayerEntity> playTo = null;
	private Set<PlayerEntity> exclude = null;

	public SoundBuilder(Supplier<SoundEvent> sound) {
		this.sound = sound.get();
	}

	public SoundBuilder(SoundEvent sound) {
		this.sound = sound;
	}

	public SoundBuilder atEntity(Entity entity) {
		this.location = entity.position();
		this.world = entity.level;

		return this;
	}

	public SoundBuilder atBlock(World world, BlockPos pos) {
		return atPos(world, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d);
	}

	public SoundBuilder atPos(World world, double x, double y, double z) {
		this.location = new Vector3d(x, y, z);
		this.world = world;

		return this;
	}

	public SoundBuilder followEntity(Entity entity) {
		this.world = entity.level;
		this.followingEntity = entity;
		this.location = entity.position();

		return this;
	}

	public SoundBuilder radius(float radius) {
		this.radius = radius;

		return this;
	}

	public SoundBuilder pitch(float pitch) {
		this.pitch = pitch;

		return this;
	}

	public SoundBuilder varyPitch(float scale) {
		this.pitch = Math.max(0, this.pitch + (float)RandomUtil.randomScaledGaussianValue(scale));

		return this;
	}

	public SoundBuilder category(SoundCategory category) {
		this.category = category;

		return this;
	}

	public SoundBuilder isMusic() {
		return category(SoundCategory.MUSIC);
	}

	public SoundBuilder isRecord() {
		return category(SoundCategory.RECORDS);
	}

	public SoundBuilder isWeather() {
		return category(SoundCategory.WEATHER);
	}

	public SoundBuilder isBlocks() {
		return category(SoundCategory.BLOCKS);
	}

	public SoundBuilder isMonster() {
		return category(SoundCategory.HOSTILE);
	}

	public SoundBuilder isFriendlyMob() {
		return category(SoundCategory.NEUTRAL);
	}

	public SoundBuilder isPlayer() {
		return category(SoundCategory.PLAYERS);
	}

	public SoundBuilder isAmbience() {
		return category(SoundCategory.AMBIENT);
	}

	public SoundBuilder isNarration() {
		return category(SoundCategory.VOICE);
	}

	public SoundBuilder applyTimeDilation() {
		this.applyTimeDilation = true;

		return this;
	}

	public SoundBuilder schedule(int ticks) {
		this.scheduleDelay = ticks;

		return this;
	}

	public SoundBuilder notInWorld() {
		this.inWorld = false;

		return this;
	}

	public SoundBuilder loopSound() {
		return loopSound(0);
	}

	public SoundBuilder loopSound(int afterDelay) {
		this.loop = true;
		this.loopDelay = afterDelay;

		return this;
	}

	public SoundBuilder exclude(PlayerEntity... players) {
		if (exclude == null)
			exclude = new HashSet<PlayerEntity>();

		if (world == null)
			world = players[0].level;

		for (PlayerEntity player : players) {
			exclude.add(player);
		}

		return this;
	}

	public SoundBuilder include(PlayerEntity... players) {
		if (playTo == null)
			playTo = new HashSet<PlayerEntity>();

		if (world == null)
			world = players[0].level;

		for (PlayerEntity player : players) {
			playTo.add(player);
		}

		return this;
	}

	public SoundEvent getSound() {
		return this.sound;
	}

	public SoundCategory getCategory() {
		return this.category;
	}

	public Vector3d getLocation() {
		return this.location;
	}

	public Entity getFollowingEntity() {
		return this.followingEntity;
	}

	public float getPitch() {
		return this.pitch;
	}

	public float getRadius() {
		return this.radius;
	}

	public int getScheduledDelay() {
		return this.scheduleDelay;
	}

	public boolean getApplyTimeDilation() {
		return this.applyTimeDilation;
	}

	public boolean getIsInWorld() {
		return this.inWorld;
	}

	public boolean getIsLooping() {
		return this.loop;
	}

	public int getLoopDelay() {
		return this.loopDelay;
	}

	public void play() {
		if (inWorld) {
			PlaySoundAtEntityEvent event = ForgeEventFactory.onPlaySoundAtEntity(followingEntity, sound, category, radius / 16f, pitch);

			if (event.isCanceled() || (sound = event.getSound()) == null)
				return;
		}

		if (world == null || world.isClientSide()) {
			ClientOperations.playSoundFromBuilder(this);
		}
		else {
			AoASoundBuilderPacket packet = new AoASoundBuilderPacket(this);

			if (playTo != null) {
				for (PlayerEntity pl : playTo) {
					if (exclude == null || !exclude.contains(pl))
						AoAPackets.messagePlayer((ServerPlayerEntity)pl, packet);
				}
			}
			else {
				for (ServerPlayerEntity pl : world.getServer().getPlayerList().getPlayers()) {
					if (pl.level == world && pl.distanceToSqr(location) <= radius * radius && (exclude == null || !exclude.contains(pl)))
						AoAPackets.messagePlayer(pl, packet);
				}
			}
		}
	}

	public void toNetwork(PacketBuffer buffer) {
		buffer.writeResourceLocation(sound.getRegistryName());

		ArrayList<Section> sections = new ArrayList<Section>();

		for (Section section : Section.values()) {
			if (section.shouldWrite.test(this))
				sections.add(section);
		}

		buffer.writeVarInt(sections.size());

		for (Section section : sections) {
			buffer.writeEnum(section);
			section.writer.accept(this, buffer);
		}
	}

	public static SoundBuilder fromNetwork(PacketBuffer buffer) {
		SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(buffer.readResourceLocation());
		SoundBuilder builder = new SoundBuilder(sound);

		int sections = buffer.readVarInt();

		for (int i = 0; i < sections; i++) {
			buffer.readEnum(Section.class).reader.accept(builder, buffer);
		}

		return builder;
	}

	enum Section {
		CATEGORY(builder -> builder.category != SoundCategory.MASTER, (builder, buffer) -> {
			buffer.writeEnum(builder.category);
		}, (builder, buffer) -> {
			builder.category(buffer.readEnum(SoundCategory.class));
		}),
		LOCATION(builder -> builder.location != null, (builder, buffer) -> {
			buffer.writeDouble(builder.location.x());
			buffer.writeDouble(builder.location.y());
			buffer.writeDouble(builder.location.z());
		}, (builder, buffer) -> {
			builder.atPos(ClientOperations.getWorld(), buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
		}),
		FOLLOWING_ENTITY(builder -> builder.followingEntity != null, (builder, buffer) -> {
			buffer.writeVarInt(builder.followingEntity.getId());
		}, (builder, buffer) -> {
			builder.followEntity(ClientOperations.getWorld().getEntity(buffer.readVarInt()));
		}),
		PITCH(builder -> builder.pitch != 1f, (builder, buffer) -> {
			buffer.writeFloat(builder.pitch);
		}, (builder, buffer) -> {
			builder.pitch(buffer.readFloat());
		}),
		RADIUS(builder -> builder.radius != 16f, (builder, buffer) -> {
			buffer.writeFloat(builder.radius);
		}, (builder, buffer) -> {
			builder.radius(buffer.readFloat());
		}),
		SCHEDULE_DELAY(builder -> builder.scheduleDelay != 0, (builder, buffer) -> {
			buffer.writeVarInt(builder.scheduleDelay);
		}, (builder, buffer) -> {
			builder.schedule(buffer.readVarInt());
		}),
		TIME_DILATION(builder -> builder.applyTimeDilation, (builder, buffer) -> {}, (builder, buffer) -> {
			builder.applyTimeDilation();
		}),
		IN_WORLD(builder -> !builder.inWorld, (builder, buffer) -> {}, (builder, buffer) -> {
			builder.notInWorld();
		}),
		LOOP(builder -> builder.loop, (builder, buffer) -> {}, (builder, buffer) -> {
			builder.loopSound();
		}),
		LOOP_DELAY(builder -> builder.loopDelay != 0, (builder, buffer) -> {
			buffer.writeVarInt(builder.loopDelay);
		}, (builder, buffer) -> {
			builder.loopSound(buffer.readVarInt());
		});

		final Predicate<SoundBuilder> shouldWrite;
		final BiConsumer<SoundBuilder, PacketBuffer> writer;
		final BiConsumer<SoundBuilder, PacketBuffer> reader;

		Section(Predicate<SoundBuilder> shouldWrite, BiConsumer<SoundBuilder, PacketBuffer> writer, BiConsumer<SoundBuilder, PacketBuffer> reader) {
			this.shouldWrite = shouldWrite;
			this.writer = writer;
			this.reader = reader;
		}
	}
}
