export default [
  {
    path: '/codeCallback',
    layout: false,
    component: './Callback',
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
    name: '应用列表',
    icon: 'appstore',
    path: '/app',
    component: './AppList',
  },
  {
    name: '日志列表',
    icon: 'fileText',
    path: '/log',
    component: './log',
  },
  {
    path: '/',
    redirect: '/welcome',
  },
  {
    component: './404',
  },
];
