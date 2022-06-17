package cn.cy.server.web.sys.service.impl;

import cn.cy.common.util.function.ActuatorUtil;
import cn.cy.server.core.exception.UnknownAppException;
import cn.cy.server.web.sso.entity.RequestPathInfo;
import cn.cy.server.web.sys.entity.App;
import cn.cy.server.web.sys.mapper.AppMapper;
import cn.cy.server.web.sys.mapper.AppRequestPathMapper;
import cn.cy.server.web.sys.service.IAppRequestPathService;
import cn.cy.sso.model.RequestPath;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 友
 * @Date: 2022/6/17 11:28
 * @Description: 应用请求路径Service实现
 */
@Slf4j
@Service
public class AppRequestPathServiceImpl implements IAppRequestPathService {

    @Resource
    private AppMapper appMapper;

    @Resource
    private AppRequestPathMapper pathMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAppRequestPath(String appCode, List<RequestPath> nowPathList) {

        App app = appMapper.selectOneAppByAppCode(appCode);
        if (ObjectUtil.isNull(app)) {
            throw new UnknownAppException("Unknown app code: " + appCode);
        }

        RequestPathInfo pathInfo;
        List<RequestPathInfo> oldPathInfoList = pathMapper.selectAllRequestPathByAppId(app.getId());
        if (CollUtil.isEmpty(oldPathInfoList)) {

            oldPathInfoList = new ArrayList<>();

            for (RequestPath info : nowPathList) {
                pathInfo = BeanUtil.copyProperties(info, RequestPathInfo.class);
                pathInfo.setAppId(app.getId());
                pathInfo.setCreateTime(LocalDateTime.now());
                pathInfo.setVersion(1);
                pathInfo.setStatus(1);

                oldPathInfoList.add(pathInfo);
            }

            return pathMapper.insertRequestPathBatch(oldPathInfoList);
        }

        Map<String, List<RequestPathInfo>> oldPathInfoMap = oldPathInfoList.stream().collect(Collectors.groupingBy(o -> o.getUrl() + "_" + o.getRequestMethod()));
        Map<String, List<RequestPath>> nowPathInfo = nowPathList.stream().collect(Collectors.groupingBy(o -> o.getUrl() + "_" + o.getRequestMethod()));

        List<RequestPathInfo> needInsertList = new ArrayList<>();
        List<RequestPathInfo> needUpdateList = new ArrayList<>();
        List<Long> needDeleteList = new ArrayList<>();

        // 处理新增和更新的 url
        for (Map.Entry<String, List<RequestPath>> m : nowPathInfo.entrySet()) {
            String url = m.getKey();
            List<RequestPath> value = m.getValue();
            RequestPath rp = value.get(0);

            List<RequestPathInfo> list = oldPathInfoMap.get(url);
            // 新增 url
            if (list == null) {
                pathInfo = BeanUtil.copyProperties(rp, RequestPathInfo.class);
                pathInfo.setAppId(app.getId());
                pathInfo.setCreateTime(LocalDateTime.now());
                pathInfo.setVersion(1);
                pathInfo.setStatus(1);

                needInsertList.add(pathInfo);
            }
            // 已存在的 url
            else {
                RequestPathInfo rpi = list.get(0);
                if (!StrUtil.equals(rpi.getHandleMethod(), rp.getHandleMethod()) || ObjectUtil.equal(rpi.getStatus(), 0)) {
                    rpi.setHandleMethod(rp.getHandleMethod());
                    rpi.setParamCount(rp.getParamCount());
                    rpi.setVersion(rpi.getVersion() + 1);
                    rpi.setStatus(1);

                    needUpdateList.add(rpi);
                }
            }
        }

        // 处理已删除的 url
        List<String> oldPathMethodList = oldPathInfoList.stream().map(o -> o.getUrl() + "_" + o.getRequestMethod()).collect(Collectors.toList());
        List<String> nowPathMethodList = nowPathList.stream().map(o -> o.getUrl() + "_" + o.getRequestMethod()).collect(Collectors.toList());
        oldPathMethodList.removeAll(nowPathMethodList);
        for (String s : oldPathMethodList) {
            oldPathInfoMap.get(s).forEach(o -> needDeleteList.add(o.getId()));
        }

        ActuatorUtil.isNotEmpty(needInsertList, pathMapper::insertRequestPathBatch);
        ActuatorUtil.isNotEmpty(needUpdateList, pathMapper::updateRequestPathBatch);
        if (CollUtil.isNotEmpty(needDeleteList)) {
            pathMapper.updateRequestPathStatusByIds(needDeleteList, 0);
        }

        return 1;
    }
}
