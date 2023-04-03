package com.scausw215.train.entity.VO;

import com.scausw215.train.entity.DTO.TrainInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 用户车次List信息VO
 * @author sensnow
 */
@Data
@AllArgsConstructor
public class UserTrainInfoListVO {

    /**
     * 用户车次信息列表
     */
    private List<TrainInfoDTO> list;
    /**
     * 总共的页数
     */
    private Integer pageTotal;

}
