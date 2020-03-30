import Flutter
import UIKit

public class SwiftNativehourformatPlugin: NSObject, FlutterPlugin {
    
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "nativehourformat", binaryMessenger: registrar.messenger())
    let instance = SwiftNativehourformatPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch call.method {
    case "getHourFormat":
        let locale = NSLocale.current
        let formatter : String = DateFormatter.dateFormat(fromTemplate: "j", options:0, locale:locale)!
        if (!formatter.contains("a")){
            result("24_hour_format")
        } else {
            result("12_hour_format")
        }
    default:
        print("Not implemented \(call.method)")
    }
  }
}
