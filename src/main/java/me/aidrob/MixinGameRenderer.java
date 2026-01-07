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
    private void onGetFov(CallbackInfoReturnable<Double> cir) {
        // Lấy giá trị FOV hiện tại từ thanh trượt (vốn bị Mio giới hạn ở 110)
        double originalFov = cir.getReturnValue();

        // Giả sử bạn muốn kéo thanh trượt từ 30-110 nhưng thực tế game hiểu là 0-200
        // Công thức tính toán để ánh xạ lại tầm nhìn:
        // Nếu thanh trượt ở 110 (Max mặc định), ta ép nó lên 200.
        // Nếu thanh trượt ở 30 (Min mặc định), ta ép nó về 0.
        
        double customFov = (originalFov - 30.0) * (200.0 / (110.0 - 30.0));
        
        if (customFov < 0) customFov = 0;
        if (customFov > 200) customFov = 200;

        cir.setReturnValue(customFov);
    }
}
