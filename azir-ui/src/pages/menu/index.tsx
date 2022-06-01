import { list as queryMenus } from '@/services/azir/menu';
import { useEffect, useState } from 'react';
import { DownOutlined } from '@ant-design/icons';
import type { ProColumns } from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
import { Button, message } from 'antd';
import { assertPath } from '@/util/AssertUtils';
  
const columns: ProColumns<API.Menu>[] = [
    {
        title: 'Icon',
        dataIndex: 'icon',
    },
    {
      title: '名称',
      dataIndex: 'name',
    },
    {
        title: '地址',
        dataIndex: 'path',
    },
    {
        title: '创建时间',
        dataIndex: 'gmtCreate',
        valueType: 'date'
    },
    {
      title: '操作',
      key: 'option',
      valueType: 'option',
      render: () => [
        <a key="1">编辑</a>,
        <a key="2">删除</a>,
      ],
    },
];


/**
 * 路由列表首页,显示所有路由
 */
const Menu: React.FC = () => {

    // 表单值对象
    const [tableData, setTableData] = useState<API.Menu[] | undefined>([])
    /**
     * 钩子函数
     */
    useEffect(() => {

        // 创建之前等
        // 路由鉴权
        assertPath();

        // 初始化表单数据
        queryMenus().then(response => {
            if (response.status) {
                setTableData(response.data)
            }
        })
        .catch(error => {
          message.error("路由菜单获取失败");
          throw Error(error)
        })

        return () => {
            // return出来的函数本来就是更新前，销毁前执行的函数，现在不监听任何状态，所以只在销毁前执行
        };
    }, []);
    return (
        <ProTable<API.Menu>
            rowKey="id"
            columns={columns}
            dataSource={tableData}
            request={(params, sorter, filter) => {
                // 表单搜索项会从 params 传入，传递给后端接口。
                console.log(params, sorter, filter);
                return Promise.resolve({
                data: tableData,
                success: true,
                });
            }}
            pagination={{
                showQuickJumper: true,
            }}
            search={false}
            dateFormatter="string"
            headerTitle="路由列表"
            options={false}
            toolBarRender={() => [
                <Button key="primary" type="primary">
                    创建路由
                </Button>
            ]}
        />
    )
}
export default Menu;