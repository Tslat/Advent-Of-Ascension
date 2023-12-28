package net.tslat.aoa3.library.builder;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.RegistryUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;

// Because Mojang hides a bunch of stuff behind sidedness -.-
public final class ParticleBuilder {
	private final ParticleOptions particle;
	private final Supplier<Vec3> posGenerator;

	private boolean isSimple = true;
	private Consumer<?> particleConsumer = null;

	private double cutoffDistance = 32;
	private boolean force = false;
	private boolean ambient = false;

	private int countPerPosition = 1;
	private int count = 1;
	private Vec3 velocity = Vec3.ZERO;
	private ColourUtil.Colour colourOverride = null;
	private int lifespan = 0;
	private float gravity = Float.MAX_VALUE;
	private float inertia = 0;
	private float scale = 1;

	private ParticleBuilder(ParticleOptions particle, Supplier<Vec3> posGenerator) {
		this.particle = particle;
		this.posGenerator = posGenerator;
	}

	public static ParticleBuilder forPos(ParticleOptions particle, Supplier<Vec3> positionGenerator) {
		return new ParticleBuilder(particle, positionGenerator);
	}

	public static ParticleBuilder forPos(ParticleOptions particle, double x, double y, double z) {
		return new ParticleBuilder(particle, () -> new Vec3(x, y, z));
	}

	public static ParticleBuilder forPos(ParticleOptions particle, Vec3 pos) {
		return forPos(particle, pos.x, pos.y, pos.z);
	}

	public static ParticleBuilder forRandomPosInBounds(ParticleOptions particle, AABB bounds) {
		Random rand = ThreadLocalRandom.current();

		return new ParticleBuilder(particle, () -> new Vec3(
				rand.nextFloat((float)bounds.minX, (float)bounds.maxX),
				rand.nextFloat((float)bounds.minY, (float)bounds.maxY),
				rand.nextFloat((float)bounds.minZ, (float)bounds.maxZ)));
	}

	public static ParticleBuilder forRandomPosInEntity(ParticleOptions particle, Entity entity) {
		if (!entity.isMultipartEntity())
			return forRandomPosInBounds(particle, entity.getBoundingBox());

		PartEntity<?>[] parts = entity.getParts();

		return forPos(particle, () -> {
			AABB bounds = RandomUtil.oneInNChance(parts.length + 1) ? entity.getBoundingBox() : RandomUtil.getRandomSelection(parts).getBoundingBox();

			return new Vec3(
					RandomUtil.randomValueBetween((float)bounds.minX, (float)bounds.maxX),
					RandomUtil.randomValueBetween((float)bounds.minY, (float)bounds.maxY),
					RandomUtil.randomValueBetween((float)bounds.minZ, (float)bounds.maxZ));
		});
	}

	public ParticleBuilder spawnNTimes(int amount) {
		this.count = amount;

		return this;
	}

	public ParticleBuilder particlesPerPosition(int amount) {
		this.countPerPosition = amount;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder velocity(Vec3 velocity) {
		this.velocity = velocity;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder velocity(double x, double y, double z) {
		return velocity(new Vec3(x, y, z));
	}

	public ParticleBuilder ignoreDistanceAndLimits() {
		this.force = true;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder cutoffDistance(double distance) {
		this.cutoffDistance = distance;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder isAmbient() {
		this.ambient = true;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder colourOverride(ColourUtil.Colour colour) {
		this.colourOverride = colour;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder colourOverride(float red, float green, float blue, float alpha) {
		return colourOverride(new ColourUtil.Colour(red, green, blue, alpha));
	}

	public ParticleBuilder colourOverride(int red, int green, int blue, int alpha) {
		return colourOverride(red / 255f, green / 255f, blue / 255f, alpha / 255f);
	}

	public ParticleBuilder colourOverride(int packedInt) {
		ColourUtil.Colour colour = new ColourUtil.Colour(packedInt);

		return colourOverride(colour.red(), colour.green(), colour.blue(), colour.alpha() == 0 ? 1f : colour.alpha());
	}

	public ParticleBuilder lifespan(int ticks) {
		this.lifespan = ticks;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder gravityOverride(float gravity) {
		this.gravity = gravity;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder inertiaOverride(float inertia) {
		this.inertia = inertia;
		this.isSimple = false;

		return this;
	}

	public ParticleBuilder scaleMod(float modifier) {
		this.scale = modifier;
		this.isSimple = false;

		return this;
	}

	// Clientside only
	public ParticleBuilder particleConsumer(Consumer<?> consumer) {
		this.particleConsumer = consumer;
		this.isSimple = false;

		return this;
	}

	public ParticleOptions getParticle() {
		return this.particle;
	}

	public Supplier<Vec3> getPositionGenerator() {
		return this.posGenerator;
	}

	public int getCountPerPosition() {
		return this.countPerPosition;
	}

	public int getCount() {
		return this.count;
	}

	public Vec3 getVelocity() {
		return this.velocity;
	}

	public double getCutoffDistance() {
		return this.cutoffDistance;
	}

	public boolean getShouldForce() {
		return this.force;
	}

	public boolean getIsAmbient() {
		return this.ambient;
	}

	@Nullable
	public ColourUtil.Colour getColourOverride() {
		return this.colourOverride;
	}

	public int getLifespan() {
		return this.lifespan;
	}

	public float getGravity() {
		return this.gravity;
	}

	public float getInertia() {
		return this.inertia;
	}

	public float getScaleMod() {
		return this.scale;
	}

	public void spawnParticles() {
		if (this.particle != null)
			ClientOperations.addParticle(this, this.particleConsumer);
	}

	public void toNetwork(final FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(RegistryUtil.getId(this.particle.getType()));
		this.particle.writeToNetwork(buffer);
		buffer.writeVarInt(this.count);

		for (int i = 0; i < this.count; i++) {
			Vec3 pos = this.posGenerator.get();

			buffer.writeDouble(pos.x);
			buffer.writeDouble(pos.y);
			buffer.writeDouble(pos.z);
		}

		buffer.writeBoolean(this.isSimple);

		if (this.isSimple)
			return;

		buffer.writeVarInt(this.countPerPosition);
		buffer.writeDouble(this.velocity.x);
		buffer.writeDouble(this.velocity.y);
		buffer.writeDouble(this.velocity.z);

		buffer.writeDouble(this.cutoffDistance);
		buffer.writeBoolean(this.force);
		buffer.writeBoolean(this.ambient);
		buffer.writeBoolean(this.colourOverride != null);

		if (this.colourOverride != null)
			this.colourOverride.toNetwork(buffer);

		buffer.writeVarInt(this.lifespan);
		buffer.writeFloat(this.gravity);
		buffer.writeFloat(this.inertia);
		buffer.writeFloat(this.scale);
	}

	public static ParticleBuilder fromNetwork(final FriendlyByteBuf buffer) {
		ParticleType<? extends ParticleOptions> particleType = ForgeRegistries.PARTICLE_TYPES.getValue(buffer.readResourceLocation());

		if (particleType == null)
			return new ParticleBuilder(null, null);

		ParticleOptions particle = deserializeParticle(buffer, particleType);
		int positionCount = buffer.readVarInt();
		Vec3[] positions = new Vec3[positionCount];

		for (int i = 0; i < positionCount; i++) {
			positions[i] = new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
		}

		ParticleBuilder builder = new ParticleBuilder(particle, new Supplier<>() {
			private final Vec3[] spawnPositions = positions;
			private int index = 0;

			@Override
			public Vec3 get() {
				return this.spawnPositions[index++];
			}
		});

		builder.count = positionCount;

		if (buffer.readBoolean())
			return builder;

		builder.countPerPosition = buffer.readVarInt();
		builder.velocity = new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
		builder.cutoffDistance = buffer.readDouble();
		builder.force = buffer.readBoolean();
		builder.ambient = buffer.readBoolean();

		if (buffer.readBoolean())
			builder.colourOverride = ColourUtil.Colour.fromNetwork(buffer);

		builder.lifespan = buffer.readVarInt();
		builder.gravity = buffer.readFloat();
		builder.inertia = buffer.readFloat();
		builder.scale = buffer.readFloat();

		return builder;
	}

	private static <T extends ParticleOptions> T deserializeParticle(FriendlyByteBuf buffer, ParticleType<T> particleType) {
		return particleType.getDeserializer().fromNetwork(particleType, buffer);
	}
}
