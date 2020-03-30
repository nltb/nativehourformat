package ltd.vblago.nativehourformat;

import android.content.Context;
import android.os.Build;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * NativehourformatPlugin
 */
public class NativehourformatPlugin implements FlutterPlugin, MethodCallHandler {
    private Context context;

    private static String _12HourFormat = "12_hour_format";
    private static String _24HourFormat = "24_hour_format";

    private NativehourformatPlugin(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "nativehourformat");
        channel.setMethodCallHandler(new NativehourformatPlugin(flutterPluginBinding.getApplicationContext()));
    }

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "nativehourformat");
        channel.setMethodCallHandler(new NativehourformatPlugin(registrar.context()));
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("getHourFormat")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                if (DateFormat.is24HourFormat(context)) {
                    result.success(_24HourFormat);
                } else {
                    result.success(_12HourFormat);
                }
            } else {
                result.notImplemented();
            }
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }
}
