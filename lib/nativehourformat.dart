import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class NativeHourFormat {
  static const String _12HourFormat = "12_hour_format";
  static const String _24HourFormat = "24_hour_format";
  static const MethodChannel _channel = const MethodChannel('nativehourformat');

  static Future<HourFormat> get getHourFormat async {
    final String stringHourFormat =
        await _channel.invokeMethod('getHourFormat');
    if (stringHourFormat == _12HourFormat) {
      return HourFormat.h;
    } else if (stringHourFormat == _24HourFormat) {
      return HourFormat.HH;
    } else {
      throw ArgumentError("Incorrect hour format type");
    }
  }
}
