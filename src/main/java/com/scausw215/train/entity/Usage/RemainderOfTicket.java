package com.scausw215.train.entity.Usage;

import lombok.Data;

/**
 * 剩余作为
 */
@Data
public class RemainderOfTicket {
    /**
     * 一等座
     */
    int firstClass;
    /**
     * 二等座
     */
    int secondClass;
    /**
     * 三等座
     */
    int thirdClass;

}
