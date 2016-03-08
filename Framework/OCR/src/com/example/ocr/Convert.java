/*
 * Copyright (C) 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.ocr;

import android.util.Log;

/**
 * Image bit-depth conversion methods.
 * 
 * @author alanv@google.com (Alan Viverette)
 */
public class Convert {
    static {
    	try{
    		System.loadLibrary("lept");
    	}catch (UnsatisfiedLinkError e){
    		Log.d("TessBaseAPI", "TessBaseAPI UnsatisfiedLinkError.But this doesn't mean the lib was not loaded before");
    	}
    }
    
    /**
     * Converts an image of any bit depth to 8-bit grayscale.
     *
     * @param pixs Source pix of any bit-depth.
     * @return a new Pix image or <code>null</code> on error
     */
    public static Pix convertTo8(Pix pixs) {
        if (pixs == null)
            throw new IllegalArgumentException("Source pix must be non-null");

        int nativePix = nativeConvertTo8(pixs.mNativePix);

        if (nativePix == 0)
            throw new RuntimeException("Failed to natively convert pix");

        return new Pix(nativePix);
    }

    // ***************
    // * NATIVE CODE *
    // ***************

    private static native int nativeConvertTo8(int nativePix);
}