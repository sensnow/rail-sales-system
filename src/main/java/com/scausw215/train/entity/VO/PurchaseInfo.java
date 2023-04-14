package com.scausw215.train.entity.VO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author sensnow
 */
@Data
public class PurchaseInfo implements Serializable {

    private Long firstSeatId;
    private Long secondSeatId;
    private Long thirdSeatId;
    private String firstSeatName;
    private String secondSeatName;
    private String thirdSeatName;
    private String firstSeatDescription;
    private String secondSeatDescription;
    private String thirdSeatDescription;
    @Serial
    private static final long serialVersionUID = 1L;
}
