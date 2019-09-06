cordova-plugin-imei
===================

Get The Real IMEI Number on Android Device

Install:

    cordova plugin add https://github.com/BlueMessaging/cordova-plugin-imei

Example Usage:

    window.plugins.imeiplugin.getImei(callback);

    function callback(imei) {
        console.log("My Android IMEI :" + imei);
    }
