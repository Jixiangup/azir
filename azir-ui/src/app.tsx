import type { Settings as LayoutSettings } from '@ant-design/pro-layout';
import { SettingDrawer } from '@ant-design/pro-layout';
import { PageLoading } from '@ant-design/pro-layout';
// @ts-ignore
import type { RunTimeLayoutConfig } from 'umi';
import { history, Link } from 'umi';
import RightContent from '@/components/RightContent';
import Footer from '@/components/Footer';
import { currentUser as queryCurrentUser } from './services/azir/api';
import { BookOutlined, LinkOutlined, SlidersOutlined, UserOutlined } from '@ant-design/icons';
import defaultSettings from '../config/defaultSettings';
import { list as queryMenus } from './services/azir/menu';
import { message } from 'antd';
import type { MenuDataItem } from './.umi/plugin-antd-icon-config/app';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import SmileOutlined from '@ant-design/icons/SmileOutlined';

const isDev = process.env.NODE_ENV === 'development';
const loginPath = '/user/login';

const IconMap = {
  SmileOutlined: <SmileOutlined />,
  SlidersOutlined: <SlidersOutlined />,
  UserOutlined: <UserOutlined />,
};

/** 获取用户信息比较慢的时候会展示一个 loading */
export const initialStateConfig = {
  loading: <PageLoading />,
};

/**
 * @see  https://umijs.org/zh-CN/plugins/plugin-initial-state
 * */
export async function getInitialState(): Promise<{
  settings?: Partial<LayoutSettings>;
  currentUser?: API.CurrentUser;
  menus?: API.Menu[];
  loading?: boolean;
  fetchUserInfo?: () => Promise<API.CurrentUser | undefined>;
  fetchMenus?: () => Promise<API.R<API.Menu[]> | undefined>;
}> {
  const fetchMenus = async () => {
    const menuResponse = await queryMenus(null);
    if (!menuResponse?.status) {
      new Error()
      message.error(menuResponse?.message)
    }
    return menuResponse;
  }
  const fetchUserInfo = async () => {
    try {
      const msg = await queryCurrentUser();
      return msg.data;
    } catch (error) {
      history.push(loginPath);
    }
    return undefined;
  };
  const menuResponse = await fetchMenus();
  // 如果不是登录页面，执行
  if (history.location.pathname !== loginPath) {
    const currentUser = await fetchUserInfo();
    return {
      fetchUserInfo,
      fetchMenus,
      currentUser,
      menus: menuResponse.data,
      settings: defaultSettings,
    };
  }
  return {
    fetchUserInfo,
    menus: menuResponse.data,
    settings: defaultSettings,
    fetchMenus,
  };
}

// ProLayout 支持的api https://procomponents.ant.design/components/layout
// @ts-ignore
export const layout: RunTimeLayoutConfig = ({ initialState, setInitialState }) => {

  const handlerMenu = (backendMenus: API.Menu[] | undefined): MenuDataItem[] => {
    if (backendMenus === undefined || !backendMenus) {
      return [];
    }
    return backendMenus.map((item) => {
      const data: MenuDataItem = {
        ...item,
        icon: item.icon && IconMap[item.icon as string],
      }
      return data;
    })
  };

  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    waterMarkProps: {
      content: initialState?.currentUser?.username,
    },
    footerRender: () => <Footer />,
    onPageChange: () => {
      const { location } = history;
      // 如果没有登录，重定向到 login
      if (!initialState?.currentUser && location.pathname !== loginPath) {
        history.push(loginPath);
      }
    },
    links: isDev
      ? [
          <Link key="openapi" to="/umi/plugin/openapi" target="_blank">
            <LinkOutlined />
            <span>OpenAPI 文档</span>
          </Link>,
          <Link to="/~docs" key="docs">
            <BookOutlined />
            <span>业务组件文档</span>
          </Link>,
        ]
      : [],
    menuHeaderRender: undefined,
    // 自定义路由
    menuDataRender: () => {
      const menuDataList = handlerMenu(initialState?.menus)
      return menuDataList;
    },
    // 自定义 403 页面
    // unAccessible: <div>unAccessible</div>,
    // 增加一个 loading 的状态
    childrenRender: (children: any, props: { location: { pathname: string | string[]; }; }) => {
      // if (initialState?.loading) return <PageLoading />;
      return (
        <>
          {children}
          {!props.location?.pathname?.includes('/login') && (
            <SettingDrawer
              disableUrlParams
              enableDarkTheme
              settings={initialState?.settings}
              onSettingChange={(settings) => {
                setInitialState((preInitialState: any) => ({
                  ...preInitialState,
                  settings,
                }));
              }}
            />
          )}
        </>
      );
    },
    ...initialState?.settings,
  };
};
