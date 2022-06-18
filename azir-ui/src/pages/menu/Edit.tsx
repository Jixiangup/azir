import React, {useEffect} from 'react'

/**
 * @Author bnyte
 * @Since 1.0.0
 */
export const Edit: React.FC = () => {

  /* state */

  /* effect */
  useEffect(() => {

    // 创建之前等

    return () => {
      // return出来的函数本来就是更新前，销毁前执行的函数，现在不监听任何状态，所以只在销毁前执行
    };
  }, []);

  /* methods */

  /* render */
  return (
    <>

    </>
  );
};
