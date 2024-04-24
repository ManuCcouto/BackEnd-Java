package com.mca.infrastructure.model;
import lombok.Data;

import java.io.Serializable;

@Data
public class RestErrorDTO implements Serializable {
        private static final long serialVersionUID = 1L;


        private Integer status;


        private String title;


        private String detail;


        private String timestamp;

    }
