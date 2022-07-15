package com.bnyte.azir.api.mapstruct;

import com.bnyte.azir.api.mapstruct.annotation.InitIgnore;
import com.bnyte.azir.api.vo.user.CurrentUserVO;
import com.bnyte.azir.api.vo.user.UserVO;
import com.bnyte.azir.common.entity.console.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Mapper
public interface UserTransfer {

    UserTransfer INSTANCE = Mappers.getMapper(UserTransfer.class);

    CurrentUserVO toCurrentUserVO(User user);

    List<UserVO> toVOS(List<User> user);

    UserVO toVO(User user);


    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "parentUserId", ignore = true),
            @Mapping(target = "status", ignore = true)
    })
    @InitIgnore
    User domain(UserVO vo);

}
