import { request } from 'umi';

// 用户列表
export const list = async (): Promise<API.R<API.User[]>> => {
    return request(`/azir/user/list`, {
        method: 'GET'
    });
};

// 用户详情
export const info = async (id: number): Promise<API.R<API.User>> => {
    return request(`/azir/user/info/${id}`, {
        method: 'GET'
    });
};

// 更新用户是否为admin
export const updateIsAdmin = async (id: number): Promise<API.R<boolean>> => {
  return request(`/azir/user//update_admin/${id}`, {
    method: 'PUT'
  });
};
