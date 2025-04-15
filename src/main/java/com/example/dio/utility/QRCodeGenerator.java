package com.example.dio.utility;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QRCodeGenerator {
    public static byte[] generateQR(String url, int height, int width) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width,height);
        try(ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()){
            MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutputStream);
            return pngOutputStream.toByteArray();
        }
    }
}
