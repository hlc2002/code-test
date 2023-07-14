package com.runjing.learn_runjing.erp.general;

import java.io.Serializable;

/**
 * @author forestSpringH
 * @date 2023/6/22
 * @project learn_runjing
 */
public class BaseModel implements Serializable {
    final static long serialVersionUID = 1L;
    public  static final int SUCCESS = 200;
    public  static final int FAILURE = 400;
    public static final int ERROR = 403;
    public  static final int REFUSE = 404;
}
