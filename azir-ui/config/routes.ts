export default [
  {
    path: '/403',
    component: './exception/403',
  },
  {
    path: '/user',
    layout: false,
    routes: [
      {
        name: 'login',
        path: '/user/login',
        component: './user/Login',
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/account',
    component: './account',
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    path: '/menu',
    name: '目录管理',
    icon: 'smile',
    component: './menu',
    routes: [
      {
        path: '/menu/lsit',
        name: '目录列表',
        icon: 'smile',
        component: './menu',
      }
    ]
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
