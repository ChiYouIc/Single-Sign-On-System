import requests from "@/utils/RequestUtil";

export async function getNotices() {
  return requests('/api/sys/noticeIcon');
}
