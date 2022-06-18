import ProTable from '@ant-design/pro-table';
import React, { useEffect, useState } from 'react';
import { list as queryUsers } from '@/services/azir/user';
import { Button, message, Popconfirm, Switch } from 'antd';
import type { ProColumns } from '@ant-design/pro-components';
import { assertPath } from '@/util/AssertUtils';
import { CheckOutlined, CloseOutlined } from '@ant-design/icons';

/**
 * 用户列表表单
 */
const List: React.FC = () => {

    // 表单值对象
    const [tableData, setTableData] = useState<API.User[] | undefined>([])

    const showCreateUser = (id: number | undefined | any) => {
        console.log('id', id);
        // queryUserInfo(id).then(response => {
        //     if (response.status) {
        //         menuInfo.setFieldsValue(response.data)
        //     }

        // })
        // .catch(error => {
        //     message.error("路由菜单信息获取失败" + error?.message);
        // })
        // setVisible(true);

    };


    const confirmRemoveUser = (id: number | undefined) => {
        console.log('id', id);
        // if (id) {
        //     deleteMenuById(id);
        //     menus();
        //     message.success("成功");
        // }
        // else message.warn("删除失败,没有找到合适参数请刷新页面后重试")
    }

    const columns: ProColumns<API.User>[] = [
        {
            title: '账号',
            dataIndex: 'account',
        },
        {
          title: '名称',
          dataIndex: 'username',
        },
        {
            title: '头像',
            dataIndex: 'avatar',
        },
        {
            title: 'admin',
            dataIndex: 'admin',
            render: (_, record) => [
                <Switch
                    key={record.account}
                    checkedChildren={<CheckOutlined />}
                    unCheckedChildren={<CloseOutlined />}
                    checked={record.admin}
                />
            ]
        },
        {
          title: '操作',
          key: 'option',
          valueType: 'option',
          render: (_, record) => [
            <Button type='primary' key="edit_menu" onClick={() => showCreateUser(record?.id)}>编辑</Button>,
            <Popconfirm key='delete_menu'
                title="你确定删除这个用户吗?"
                onConfirm={() => confirmRemoveUser(record?.id)}
                okText='删除'
                cancelText='取消'
            >
                <Button key='delete_menu' danger>删除</Button>
            </Popconfirm>,
          ],
        },
    ];

    // 刷新用户列表
    const refreshUsers = () => {
        queryUsers().then(response => {
            if (response.status) {
                setTableData(response.data)
            } else message.error("获取用户列表失败");
        })
        .catch(error => {
            message.error("获取用户列表失败", error);
        })
    }

    /**
     * 钩子函数
     */
    useEffect(() => {
        // 创建之前等
        assertPath()
        refreshUsers()
        return () => {
            // return出来的函数本来就是更新前，销毁前执行的函数，现在不监听任何状态，所以只在销毁前执行
        };
    }, []);
    return (
        <>
            <ProTable<API.User>
                rowKey="id"
                columns={columns}
                dataSource={tableData}
                request={(params, sorter, filter) => {
                    // 表单搜索项会从 params 传入，传递给后端接口。
                    console.log("111", params, sorter, filter);
                    return Promise.resolve({
                    data: tableData,
                    success: true,
                    });
                }}
                pagination={{
                    showQuickJumper: true,
                }}
                dateFormatter="string"
                headerTitle="用户列表"
                options={false}
                toolBarRender={() => [
                    <Button key="createUser" type="primary" onClick={() => showCreateUser(null)}>
                        创建用户
                    </Button>
                ]}
            />
        </>
    )
}
export default List;
