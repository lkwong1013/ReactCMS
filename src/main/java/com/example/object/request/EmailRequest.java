package com.example.object.request;

import lombok.Data;

import java.util.List;

/**
 * Created by LKW on 2017/5/21.
 */

@Data
public class EmailRequest {

    private String sendAddress;

    private List<String> receiverAddressList;

    private String receiverAddress;

    private String subject;

    private String emailContent;


}
