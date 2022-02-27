export type AppListItem = {
  id: number;
  appCode: string;
  appName: string;
  sort: number;
  status: number;
}

export type AppList = {
  data?: AppListItem[];
  total?: number;
  success?: boolean;
}
