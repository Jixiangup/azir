import { created as createMenu, deleteById as deleteMenuById, info as queryMenuInfo, list as queryMenus, update as updateMenu } from '@/services/azir/menu';
import { useEffect, useState } from 'react';
import type { ProColumns } from '@ant-design/pro-components';
import { ProTable } from '@ant-design/pro-components';
import { Button, Drawer, message, Popconfirm, Form, Input, InputNumber, Tooltip } from 'antd';
import { assertPath } from '@/util/AssertUtils';
import { QuestionCircleOutlined } from '@ant-design/icons';

/**
 * 路由列表首页,显示所有路由
 */
const Menu: React.FC = () => {    

    // 表单值对象
    const [tableData, setTableData] = useState<API.Menu[] | undefined>([])

    const [visible, setVisible] = useState(false);

    const [menuInfo] = Form.useForm()

    const showCreateMenu = (id: number | undefined | any) => {
        queryMenuInfo(id).then(response => {
            if (response.status) {
                menuInfo.setFieldsValue(response.data)
            }
            
        })
        .catch(error => {
            message.error("路由菜单信息获取失败" + error?.message);
        })
        setVisible(true);

    };

    const onCloseCreateMenu = () => {
        setVisible(false);
    };

    const menus = () => {
        queryMenus().then(response => {
            if (response.status) {
                setTableData(response.data)
            }
        })
        .catch(error => {
          message.error("路由菜单获取失败");
          throw Error(error)
        })
    }

    const handlerFormOnFinish = (values: API.Menu) => {
        if (!values?.id) {
            createMenu(values).then(response => {
                if (response.status) {
                    onCloseCreateMenu();
                    menus();
                    message.success("路由创建成功")
                } else {
                    message.error(response.message)
                }
            })
            .catch(error => {
                message.error("路由创建失败");
                throw Error(error)
            });
        } else {
            updateMenu(values).then(response => {
                if (response.status) {
                    onCloseCreateMenu();
                    menus();
                    message.success("成功")
                } else {
                    message.error(response.message)
                }
            })
            .catch(error => {
                message.error("路由创建失败");
                throw Error(error)
            });
        }
    };

    const handlerFormOnFinishFailed = (errorInfo: any) => {
        console.log('Failed:', errorInfo);
    };

    const confirmRemoveMenu = (id: number | undefined) => {
        if (id) {
            deleteMenuById(id);
            menus();
            message.success("成功");
        }
        else message.warn("删除失败,没有找到合适参数请刷新页面后重试")
    }

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
          render: (_, record) => [
            <Button type='primary' key="edit_menu" onClick={() => showCreateMenu(record?.id)}>编辑</Button>,
            <Popconfirm key='delete_menu'
                title="你确定删除这条路由吗?"
                onConfirm={() => confirmRemoveMenu(record?.id)}
                okText='删除'
                cancelText='取消'
            >
                <Button key='delete_menu' danger>删除</Button>
            </Popconfirm>,
          ],
        },
    ];

    /**
     * 钩子函数
     */
    useEffect(() => {

        // 创建之前等
        // 路由鉴权
        assertPath();

        // 初始化表单数据
        menus()

        return () => {
            // return出来的函数本来就是更新前，销毁前执行的函数，现在不监听任何状态，所以只在销毁前执行
        };
    }, []);
    return (
        <>
            <ProTable<API.Menu>
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
                search={false}
                dateFormatter="string"
                headerTitle="路由列表"
                options={false}
                toolBarRender={() => [
                    <Button key="primary" type="primary" onClick={() => showCreateMenu(null)}>
                        创建路由
                    </Button>
                ]}
            />
            <Drawer title="路由管理" placement="right" onClose={onCloseCreateMenu} visible={visible}>
                <Form
                    name="basic"
                    labelCol={{ span: 8 }}
                    wrapperCol={{ span: 16 }}
                    initialValues={{ remember: true }}
                    onFinish={handlerFormOnFinish}
                    onFinishFailed={handlerFormOnFinishFailed}
                    autoComplete="off"
                    form={menuInfo}
                    >
                    <Form.Item label="id" name="id" hidden>
                        <Input />
                    </Form.Item>

                    {/* <Tooltip title="这是一条提示">

                    </Tooltip> */}
                    <Form.Item
                        label={
                            <span>
                                名称&nbsp;<Tooltip title="用于显示在左侧路由名称"><QuestionCircleOutlined /></Tooltip>
                            </span>
                        }
                        name="name"
                        rules={[{ required: true, message: '请输入菜单路由名称!' }]}
                        >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label={
                            <span>
                                路径&nbsp;<Tooltip title="当前路由的请求路径"><QuestionCircleOutlined /></Tooltip>
                            </span>
                        }
                        name="path"
                        rules={[{ required: true, message: '请输入菜单路径!' }]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label={
                            <span>
                                父节点&nbsp;<Tooltip title="父节点决定菜单层级"><QuestionCircleOutlined /></Tooltip>
                            </span>
                        }
                        name="parentId">
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label={
                            <span>
                                icon&nbsp;<Tooltip title="显示路由icon当为根路由时才会显示"><QuestionCircleOutlined /></Tooltip>
                            </span>
                        }
                        name="icon">
                        <Input />
                    </Form.Item>

                    <Form.Item
                        label={
                            <span>
                                权重&nbsp;<Tooltip title="路由优先级权重,数字越小优先级越高"><QuestionCircleOutlined /></Tooltip>
                            </span>
                        }
                        name="weights">
                        <InputNumber />
                    </Form.Item>
                    

                    <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
                        <Button type="primary" htmlType="submit">
                            提交
                        </Button>
                    </Form.Item>
                </Form>
            </Drawer>
        </>
    )
}
export default Menu;