package com.example.dio.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeGeneratorService {
    byte[] generateQR(String url) throws IOException, WriterException;
}
