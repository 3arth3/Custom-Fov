package me.aidrob.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GameRenderer.class, priority = 1000)
public class MixinGameRenderer {

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void onGetFov(CallbackInfoReturnable<Double> cir) 
        double originalFov = cir.getReturnValue();
        double customFov = (originalFov - 30.0) * (200.0 / (110.0 - 30.0));
        
        if (customFov < 0) customFov = 0;
        if (customFov > 200) customFov = 200;

        cir.setReturnValue(customFov);
    }
}
