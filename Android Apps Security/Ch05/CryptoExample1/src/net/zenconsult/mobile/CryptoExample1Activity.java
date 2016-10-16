package net.zenconsult.mobile;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class CryptoExample1Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        KeyGenerator kg = null;
        String stringKey = "60380131061660211660380426804995";
        String message = "This is a secret message";
        try {
        	SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        	SecretKeySpec sks = new SecretKeySpec(stringKey.getBytes(),"AES");
        	Cipher c = Cipher.getInstance("AES/CBC/ZeroBytePadding");
        	c.init(Cipher.ENCRYPT_MODE, sks);
        	c.update(message.getBytes());
        	byte[] ciphertext = c.doFinal();
        	Log.i("CE", new String(ciphertext));

			
		} catch (NoSuchAlgorithmException e) {
			Log.e("CE",e.getMessage());
		} catch (NoSuchPaddingException e) {
			Log.e("CE",e.getMessage());
		} catch (InvalidKeyException e) {
			Log.e("CE",e.getMessage());
		} catch (IllegalBlockSizeException e) {
			Log.e("CE",e.getMessage());
		} catch (BadPaddingException e) {
			Log.e("CE",e.getMessage());
		}
        
    }
}