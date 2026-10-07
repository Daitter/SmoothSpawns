package elchinolnb.ss.client.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import elchinolnb.ss.client.SpawnAnimate;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
	private static final float ANIM_TICKS = 15f; // 0.75 s

	@Inject(method = "extractRenderState", at = @At("TAIL"))
	private void smoothspawns$extract(LivingEntity entity, LivingEntityRenderState state,
									  float partialTick, CallbackInfo ci) {
		float scale = 1f;
		if (entity instanceof Mob && entity.tickCount < ANIM_TICKS) {
			float t = Math.min((entity.tickCount + partialTick) / ANIM_TICKS, 1f);
			float c1 = 1.70158f, c3 = c1 + 1f;
			scale = 1f + c3 * (float) Math.pow(t - 1, 3) + c1 * (float) Math.pow(t - 1, 2);
		}
		((SpawnAnimate) state).smoothspawns$setScale(scale);
	}

	@Inject(method = "scale", at = @At("TAIL"))
	private void smoothspawns$scale(LivingEntityRenderState state, PoseStack poseStack, CallbackInfo ci) {
		float s = ((SpawnAnimate) state).smoothspawns$getScale();
		if (s != 1f) poseStack.scale(s, s, s);
	}
}