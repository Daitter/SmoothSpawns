package elchinolnb.ss.client.mixin;

import elchinolnb.ss.client.SpawnAnimate;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements SpawnAnimate {
	@Unique private float smoothspawns$scale = 1f;

	@Override public float smoothspawns$getScale() { return smoothspawns$scale; }
	@Override public void smoothspawns$setScale(float s) { smoothspawns$scale = s; }
}