import { useEffect } from 'react';
import List from './list';

/**
 * 用户管理
 */
const Account: React.FC = () => {




    /**
     * 钩子函数
     */
    useEffect(() => {
        // 创建之前等
        return () => {
            // return出来的函数本来就是更新前，销毁前执行的函数，现在不监听任何状态，所以只在销毁前执行
        };
    }, []);
    return (
        <>
            <List />
        </>
    )
}
export default Account;