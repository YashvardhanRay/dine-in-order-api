package com.example.dio.controller;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.service.TableService;
import com.example.dio.utility.ResponseBuilder;
import com.example.dio.utility.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class TableController {

    private final TableService tableService;

    @PostMapping("/tables/restaurant/{id}")
    public ResponseEntity<ResponseStructure<TableResponse>> createTable(@PathVariable long id , @RequestBody TableRequest tableRequest){

        TableResponse tableResponse =  tableService.createTable(id,tableRequest);

        return ResponseBuilder.created("Table created",tableResponse);
    }
}
