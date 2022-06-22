package com.ljh.lottery.interfaces.assembler;

import com.ljh.lottery.domain.strategy.model.vo.DrawAwardVO;
import com.ljh.lottery.rpc.dto.AwardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Created with IntelliJ IDEA.
 * Description: 对象转换配置
 *
 * @Author: ljh
 * DateTime: 2022-06-22 9:50
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVO, AwardDTO>{

    /**
     * 映射属性，配置不同名属性映射规则
     * @param var1  源
     * @return
     */
    @Mapping(target = "userId", source = "uId")
    @Override
    AwardDTO sourceToTarget(DrawAwardVO var1);

    /**
     * 反向映射
     * @param var1  源
     * @return
     */
    @Override
    DrawAwardVO targetToSource(AwardDTO var1);
}
