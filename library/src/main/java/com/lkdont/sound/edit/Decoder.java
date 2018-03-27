package com.lkdont.sound.edit;

import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 解码器
 * <p>
 * Created by kidonliang on 2018/3/4.
 */

public class Decoder {

    public static final int AV_CODEC_ID_MP3 = 1;
    public static final int AV_CODEC_ID_AAC = 2;

    private static Decoder mDecoder;

    private Decoder() {
    }

    @Nullable
    public static Decoder createDecoder(int codec) {
        if (mDecoder != null) {
            Log.e("Decoder", "创建失败，已有一个解码器在运行。");
            return null;
        }
        int ret = initDecoder(codec);
        if (ret != 0) {
            Log.e("Decoder", "初始化解码器失败, code=" + ret);
            return null;
        }
        mDecoder = new Decoder();
        return mDecoder;
    }

    @Nullable
    public static Decoder getRunningDecoder() {
        return mDecoder;
    }

    private static native int initDecoder(int codec);

    public native int feedData(byte[] data, int len);

    public native int getDecodedSize();

    public native int receiveDecodedData(byte[] data);

    private static native void closeDecoder();

    public void close() {
        closeDecoder();
        mDecoder = null;
    }

//    public static native int test();
}