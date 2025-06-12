package com.example.account_service.dto;

import com.example.account_service.constant.ACCOUNT_TYPE;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class AccountResponse{
    private ACCOUNT_TYPE accountType;
    private String branch_Code;
    private LocalDate account_Opening_Date;
}
