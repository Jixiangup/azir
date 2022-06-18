// @ts-ignore
import { request } from 'umi';

export const list = async (optional: any): Promise<API.R<API.Menu[]>> => {
    return request(`/azir/menu/list`, {
      method: 'GET',
      params: {
        ...optional
      }
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

/** 删除路由 */
export const deleteById = async (id: number): Promise<API.R<boolean>> => {
    return request(`/azir/menu/delete/${id}`, {
        method: 'DELETE'
    });
};

/** 创建路由 */
export const created = async (payload: API.Menu): Promise<API.R<number>> => {
    return request(`/azir/menu/created`, {
        method: 'POST',
        data: {
            ...payload
        }
    });
};

/** 更新路由 */
export const update = async (payload: API.Menu): Promise<API.R<boolean>> => {
    return request(`/azir/menu/update`, {
        method: 'PUT',
        data: {
            ...payload
        }
    });
};

/** 路由信息 */
export const info = async (id: number | undefined | null): Promise<API.R<API.Menu>> => {
    return request(`/azir/menu/info/${id}`, {
        method: 'GET'
    });
};
