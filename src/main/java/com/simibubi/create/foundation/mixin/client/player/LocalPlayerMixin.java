package com.simibubi.create.foundation.mixin.client.player;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
	@Inject(method = "tick()V", at = @At("RETURN"), cancellable = true)
	public void Create$tick(CallbackInfo ci){
		LocalPlayer self = (LocalPlayer) (Object) this;
		self.yRotO=self.getYRot();
	}
	@Inject(method = "getViewYRot(F)F", at = @At("HEAD"), cancellable = true)
	public void Create$getViewYRot(float p_108753_,CallbackInfoReturnable<Float> cir) {
		LocalPlayer self = (LocalPlayer) (Object) this;
		cir.setReturnValue(p_108753_ == 1.0F ? self.getYRot() : Mth.lerp(p_108753_, self.yRotO, self.getYRot()));
	}
}
