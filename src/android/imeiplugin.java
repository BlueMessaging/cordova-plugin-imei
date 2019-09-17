package com.joandilee;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.telephony.TelephonyManager;
import android.content.Context;
import android.util.Log;
import android.os.Build;

/**
 * A esta clase solo se modifico la forma de obtener el imei, 
 * las convenciones del codigo son propias del plugin {@link} https://github.com/zho/phonegap-imeiplugin 
 */
public class imeiplugin extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getImei")) {
            this.DeviceImeiNumber(callbackContext);
            return true;
        }
        return false;
    }

    /**
     * Este método retorna en el callback el imei del dispositivo usando un metodo diferente dependiento la version de android
     */
    public void DeviceImeiNumber(CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();

        TelephonyManager tManager = (TelephonyManager) cordova.getActivity()
                .getSystemService(context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            callbackContext.success(tManager.getImei()); // Nuevo método para versiones posteriores a android oreo
        } else {
            callbackContext.success(tManager.getDeviceId()); // Método deprecado para versiones anteriores a android oreo
        }
    }

    private void getImei(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
