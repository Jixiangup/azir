package com.bnyte.azir.api.mapstruct;

import com.bnyte.azir.api.vo.user.CurrentUserVO;
import com.bnyte.azir.common.entity.console.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-28T08:00:24+0800",
    comments = "version: 1.5.0.RC1, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class UserTransferImpl implements UserTransfer {

    @Override
    public CurrentUserVO toCurrentUserVO(User user) {
        if ( user == null ) {
            return null;
        }

        CurrentUserVO currentUserVO = new CurrentUserVO();

        currentUserVO.setId( user.getId() );
        currentUserVO.setUsername( user.getUsername() );
        currentUserVO.setAvatar( user.getAvatar() );

        return currentUserVO;
    }
}
