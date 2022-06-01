import { request } from 'umi';

export const list = async (): Promise<API.R<API.Menu[]>> => {
    return request(`/azir/menu/list`, {
        method: 'GET'
    });
};

/** 获取用户是否有当前path的权限 */
export const allowAccess = async (path: string): Promise<API.R<boolean>> => {
    return request(`/azir/menu/allow_access`, {
        method: 'GET',
        params: {
            "path": path
        }
    });
};