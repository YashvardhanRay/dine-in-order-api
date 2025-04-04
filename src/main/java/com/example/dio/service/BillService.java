package com.example.dio.service;

import com.example.dio.dto.response.BillResponse;

import java.io.IOException;

public interface BillService {

    BillResponse createBill(long tableId);

    BillResponse findById(long billId);

    byte[] pdfGenerator(long billId) throws IOException;
}
