import { allowAccess } from '@/services/azir/menu';
import { useEffect } from 'react';
import { DownOutlined, EllipsisOutlined, QuestionCircleOutlined } from '@ant-design/icons';
import type { ProColumns } from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
import { Button, Tooltip } from 'antd';
import { history } from 'umi';

const path403 = "/403"

export type Status = {
    color: string;
    text: string;
  };
  
const statusMap = {
    0: {
        color: 'blue',
        text: '进行中',
    },
    1: {
        color: 'green',
        text: '已完成',
    },
    2: {
        color: 'volcano',
        text: '警告',
    },
    3: {
        color: 'red',
        text: '失败',
    },
    4: {
        color: '',
        text: '未完成',
    },
};

export type TableListItem = {
key: number;
name: string;
containers: number;
creator: string;
status: Status;
createdAt: number;
};
const tableListDataSource: TableListItem[] = [];

const creators = ['付小小', '曲丽丽', '林东东', '陈帅帅', '兼某某'];

for (let i = 0; i < 5; i += 1) {
tableListDataSource.push({
    key: i,
    name: 'AppName',
    containers: Math.floor(Math.random() * 20),
    creator: creators[Math.floor(Math.random() * creators.length)],
    status: statusMap[Math.floor(Math.random() * 10) % 5],
    createdAt: Date.now() - Math.floor(Math.random() * 100000),
});
}

const columns: ProColumns<TableListItem>[] = [
    {
        title: '路由名称',
        width: 120,
        dataIndex: 'name',
    },
    {
        title: '路由路径',
        width: 120,
        dataIndex: 'path',
    },
    {
        title: 'Icon',
        width: 120,
        dataIndex: 'icon',
    },
    {
        title: (
        <>
            创建时间
            <Tooltip placement="top" title="这是一段描述">
            <QuestionCircleOutlined style={{ marginLeft: 4 }} />
            </Tooltip>
        </>
        ),
        width: 140,
        key: 'since',
        dataIndex: 'createdAt',
        valueType: 'date',
        sorter: (a, b) => a.createdAt - b.createdAt,
    },
    {
        title: '操作',
        width: 164,
        key: 'option',
        valueType: 'option',
        render: () => [
        <a key="1">链路</a>,
        <a key="2">报警</a>,
        <a key="3">监控</a>,
        <a key="4">
            <EllipsisOutlined />
        </a>,
        ],
    },
];

const expandedRowRender = () => {
    const data = [];
    for (let i = 0; i < 3; i += 1) {
        data.push({
        key: i,
        date: '2014-12-24 23:12:00',
        name: 'This is production name',
        upgradeNum: 'Upgraded: 56',
        });
    }
    return (
        <ProTable
            columns={[
                { title: 'Date', dataIndex: 'date', key: 'date' },
                { title: 'Name', dataIndex: 'name', key: 'name' },

                { title: 'Upgrade Status', dataIndex: 'upgradeNum', key: 'upgradeNum' },
                {
                title: 'Action',
                dataIndex: 'operation',
                key: 'operation',
                valueType: 'option',
                render: () => [<a key="Pause">Pause</a>, <a key="Stop">Stop</a>],
                },
            ]}
            headerTitle={false}
            search={false}
            options={false}
            dataSource={data}
            pagination={false}
        />
    );
};


/**
 * 路由列表首页,显示所有路由
 */
const Menu: React.FC = () => {
    /**
     * 钩子函数
     */
    useEffect(() => {
        const { location } = history;
        // 创建之前等
        allowAccess(location.pathname).then(response => {
            const { status, data } = response;
            console.log('status', status);
            console.log('data', data);
            if (status && !data) {
                history.push(path403)
            }
        })
        return () => {
            // return出来的函数本来就是更新前，销毁前执行的函数，现在不监听任何状态，所以只在销毁前执行
        };
    }, []);
    return (
        <ProTable<TableListItem>
            columns={columns}
            request={(params, sorter, filter) => {
                // 表单搜索项会从 params 传入，传递给后端接口。
                console.log(params, sorter, filter);
                return Promise.resolve({
                data: tableListDataSource,
                success: true,
                });
            }}
            rowKey="key"
            pagination={{
                showQuickJumper: true,
            }}
            expandable={{ expandedRowRender }}
            search={false}
            dateFormatter="string"
            headerTitle="嵌套表格"
            options={false}
            toolBarRender={() => [
                <Button key="show">查看日志</Button>,
                <Button key="out">
                导出数据
                <DownOutlined />
                </Button>,
                <Button key="primary" type="primary">
                创建应用
                </Button>,
            ]}
        />
    )
}
export default Menu;