import { Space, message, Select, Button, Drawer, Form, Input } from 'antd';
import { QuestionCircleOutlined } from '@ant-design/icons';
import React, { useState, useEffect } from 'react';
import { useModel, SelectLang } from 'umi';
import Avatar from './AvatarDropdown';
import HeaderSearch from '../HeaderSearch';
import styles from './index.less';
import { create, list, selectTenant } from '@/services/azir/tenant';
import Cookies from 'js-cookie';

export type SiderTheme = 'light' | 'dark';

const { Option } = Select;

const GlobalHeaderRight: React.FC = () => {
  const { initialState } = useModel('@@initialState');
  
  // 当前用户的租户列表
  const [tenants, setTenants] = useState<API.Tenant[] | undefined>([])
  // 控制添加租户表单是否打开
  const [visible, setVisible] = useState(false);

  const onCloseTenantDrawer = () => {
    setVisible(false);
  };

  const onShowTenantDrawer = () => {
    setVisible(true);
  };

  const refreshTenants = () => {
    list()
      .then(response => {
        setTenants(response?.data)
      })
      .catch(error => {
        console.log('error', error);
        message.error("租户信息获取失败");
    });
  }

  useEffect(() => {
    refreshTenants();
  }, []);

  if (!initialState || !initialState.settings) {
    return null;
  }

  const { navTheme, layout } = initialState.settings;
  let className = styles.right;

  if ((navTheme === 'dark' && layout === 'top') || layout === 'mix') {
    className = `${styles.right}  ${styles.dark}`;
  }

  const handlerSelectTenant = (item: any) => {
    if (item?.key !== Cookies.get("tenant_id")) {
      selectTenant(item?.key)
      .then(response => {
        if (response?.status) {
          window.location.reload();
        }
      })
      .catch(error => {
        message.error("租户切换失败");
        throw Error(error);
      })
    }
  }

  const handlerCreateTenant = (values: any) => {
    create(values)
    .then(response => {
      if (response?.status) {
        message.success(response?.message);
        // 获取租户列表
        refreshTenants();
        setVisible(false);
      } else {
        throw Error(response?.message)
      }
    })
    .catch(error => {
      console.log('error', error);
      message.error(error.message)
    })
  };

  return (
    <Space className={className}>

      <Drawer title="创建租户" placement="right" onClose={onCloseTenantDrawer} visible={visible}>
        <Form
          name="创建租户"
          labelCol={{ span: 8 }}
          wrapperCol={{ span: 16 }}
          onFinish={handlerCreateTenant}
          // onFinishFailed={onFinishFailed} // 提交表单验证失败后的回调事件
          autoComplete="off">
          <Form.Item
            label="中文名称"
            name="cnName"
            rules={[{ required: true, message: '请输入租户中文名称!' }]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            label="英文名称"
            name="enName"
            rules={[{ required: true, message: '请输入租户中文名称!' }]}
          >
            <Input />
          </Form.Item>

          <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
      </Drawer>

      {/* 租户选择 */}
      <Select 
        defaultValue={Cookies.get("tenant_name") ? Cookies.get("tenant_name") : "选择租户"} 
        style={{ width: 120 }} 
        onSelect={(_: any, item: any) => handlerSelectTenant(item)}
        notFoundContent={<Button type='primary' onClick={() => {onShowTenantDrawer()}}>创建租户</Button>}
        bordered={false}>
        {
          tenants?.map((item) => {
            return (
              <Option key={item.id} value={item.cnName}>{item.cnName}</Option>
            )
          })
        }
      </Select>

      <HeaderSearch
        className={`${styles.action} ${styles.search}`}
        placeholder="站内搜索"
        defaultValue="umi ui"
        options={[
          { label: <a href="https://umijs.org/zh/guide/umi-ui.html">umi ui</a>, value: 'umi ui' },
          {
            label: <a href="next.ant.design">Ant Design</a>,
            value: 'Ant Design',
          },
          {
            label: <a href="https://protable.ant.design/">Pro Table</a>,
            value: 'Pro Table',
          },
          {
            label: <a href="https://prolayout.ant.design/">Pro Layout</a>,
            value: 'Pro Layout',
          },
        ]}
        // onSearch={value => {
        //   console.log('input', value);
        // }}
      />
      <span
        className={styles.action}
        onClick={() => {
          window.open('https://pro.ant.design/docs/getting-started');
        }}
      >
        <QuestionCircleOutlined />
      </span>
      <Avatar />
      <SelectLang className={styles.action} />
    </Space>
  );
};
export default GlobalHeaderRight;
