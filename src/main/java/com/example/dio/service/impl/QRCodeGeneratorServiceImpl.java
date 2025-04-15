package com.example.dio.service.impl;

import com.example.dio.service.QRCodeGeneratorService;
import com.example.dio.utility.QRCodeGenerator;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QRCodeGeneratorServiceImpl implements QRCodeGeneratorService {
    @Override
    public byte[] generateQR(String url) throws IOException, WriterException {
        return QRCodeGenerator.generateQR(url,250,250);
    }
}
