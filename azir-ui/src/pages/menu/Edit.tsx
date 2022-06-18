import React, {useEffect} from 'react'
import {Button, Drawer, Form, Input, InputNumber, message, Tooltip, TreeSelect} from "antd";
import {QuestionCircleOutlined} from "@ant-design/icons";
import {created as createMenu, update as updateMenu} from "@/services/azir/menu";
import type {Menu} from "@/pages/menu/typings";

/**
 * @Author bnyte
 * @Since 1.0.0
 */
export const Edit: React.FC<Menu.EditProps> = (props) => {

  /* props对象属性 */
  const {setVisible, menus, refreshMenus, selectMenu, form} = props;

  /* state */

  /* effect */
  useEffect(() => {

    // 创建之前等

    return () => {
      // return出来的函数本来就是更新前，销毁前执行的函数，现在不监听任何状态，所以只在销毁前执行
    };
  }, []);

  /* methods */
  const handlerFoldersTree = (): API.Menu[] => {
    const result: API.Menu[] = JSON.parse(JSON.stringify(menus));
    const data: API.Menu = {
      id: -1, children: [], gmtCreate: "", icon: "", menu: "", name: "-", path: "", weights: undefined
    };
    result.push(data);
    return result.filter(item => {
      if (!selectMenu) return true;
      return item.name != selectMenu;
    })
    .sort((pre, next) => {
      if (!pre.id || !next.id) return 0;
      return pre.id - next.id;
    });
  }

  const submit = (values: API.Menu) => {
    debugger
    if (!values?.id) {
      createMenu(values).then(response => {
        if (response.status) {
          setVisible(false);
          message.success("路由创建成功");
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
          setVisible(false);
          refreshMenus();
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

  /* render */
  return (
    <>
      <Drawer title="路由管理" placement="right" onClose={() => setVisible(false)} visible={props.visible}>
        <Form
          name="basic"
          labelCol={{ span: 8 }}
          wrapperCol={{ span: 16 }}
          initialValues={{ remember: true }}
          onFinish={submit}
          // onFinishFailed={handlerFormOnFinishFailed}
          autoComplete="off"
          form={form}
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
            <TreeSelect
              treeData={handlerFoldersTree()}
              placeholder="请选择父节点"
              value={selectMenu}
              fieldNames={
                {
                  label: "name",
                  value: "id",
                  children: "children"
                }
              }
            />
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
  );
};
