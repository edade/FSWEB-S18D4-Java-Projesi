package com.workintech.demo.dto;

public record AccountResp(long id, String accountName, double monetAmount, CustomerResp customerResp ) {
}
