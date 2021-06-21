package com.fedexu.mailer;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmailData {
    String name;
    List<String> eMail;
    String body;
    Integer result;
}

