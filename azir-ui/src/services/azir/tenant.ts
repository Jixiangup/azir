import { request } from 'umi';

export const list = async (): Promise<API.Tenants | undefined> => {
    return request(`/azir/tenant/list`, {
        method: 'GET'
    });
};

/** 切换租户 */
export const selectTenant = async (tenantId: number) => {
    return request(`/azir/tenant/select_tenant/${tenantId}`, {
        method: 'GET'
    });
};

/** 创建租户 */
export const create = async (options?: { [key: string]: any }) => {
    return request(`/azir/tenant/create`, {
        method: 'POST',
        data: {
            ...options
        },
    });
};