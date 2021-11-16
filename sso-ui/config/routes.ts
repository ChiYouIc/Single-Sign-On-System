export default [
  {
    path: '/codeCallback',
    layout: false,
    component: './Callback'
  },
  {
    path: '/user',
    layout: false,
    routes: [
      {
        path: '/user',
        routes: [
          {
            name: 'login',
            path: '/user/login',
            component: './user/Login',
          },
        ],
      },
      {
        component: './404',
      },
    ],
  },
  {
    path: '/welcome',
    name: 'welcome',
    icon: 'smile',
    component: './Welcome',
  },
  {
    name: '用户列表',
    icon: 'table',
    path: '/customer',
    component: './CustomerList',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  }
];
