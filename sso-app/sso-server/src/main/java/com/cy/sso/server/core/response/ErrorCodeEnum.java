package com.cy.sso.server.core.response;


import cn.hutool.core.util.StrUtil;
import com.cy.sso.server.core.exception.UnknownEnumException;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/4 15:09
 * @Description: 业务异常枚举
 */
public enum ErrorCodeEnum {

    /**
     * 400
     */
    BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, "请求参数有误", StrUtil.EMPTY),
    /**
     * JSON格式错误
     */
    JSON_FORMAT_ERROR(HttpServletResponse.SC_BAD_REQUEST, "JSON格式错误", StrUtil.EMPTY),
    /**
     * 401
     */
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请先进行认证", StrUtil.EMPTY),
    CAPTCHA_UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "验证码错误", StrUtil.EMPTY),
    /**
     * 403
     */
    FORBIDDEN(HttpServletResponse.SC_FORBIDDEN, "暂无权操作，如需授权，请联系管理员", StrUtil.EMPTY),
    /**
     * 404
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "未找到", StrUtil.EMPTY),
    /**
     * 405
     */
    METHOD_NOT_ALLOWED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "请求方式不支持", StrUtil.EMPTY),
    /**
     * 406
     */
    NOT_ACCEPTABLE(HttpServletResponse.SC_NOT_ACCEPTABLE, "不可接受该请求", StrUtil.EMPTY),
    /**
     * 411
     */
    LENGTH_REQUIRED(HttpServletResponse.SC_LENGTH_REQUIRED, "请求长度受限制", StrUtil.EMPTY),
    /**
     * 415
     */
    UNSUPPORTED_MEDIA_TYPE(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "该请求不支持的媒体类型", StrUtil.EMPTY),
    /**
     * 416
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "该请求不在请求满足的范围", StrUtil.EMPTY),
    /**
     * 500
     */
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器出了点问题，抱歉", StrUtil.EMPTY),
    /**
     * 503
     */
    SERVICE_UNAVAILABLE(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "请求超时", StrUtil.EMPTY),
    /**
     * 消息异常
     */
    MSG_EXCEPTION(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "默认消息异常", StrUtil.EMPTY),
    // ----------------------------------------------------业务异常----------------------------------------------------

    OPERATION_FAILED(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "操作失败", StrUtil.EMPTY),

    /**
     * Dept
     */
    DEPT_EXISTING_LOWER_LEVEL_DEPT(HttpServletResponse.SC_BAD_REQUEST, "当前部门存在下属部门，不允许删除", StrUtil.EMPTY),
    DEPT_EXISTING_USER(HttpServletResponse.SC_BAD_REQUEST, "当前部门还存在用户，不允许删除", StrUtil.EMPTY), DEPT_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "部门名称已经存在", StrUtil.EMPTY),
    DEPT_PARENT_DEPT_CANNOT_MYSELF(HttpServletResponse.SC_BAD_REQUEST, "上级部门不能是当前部门", StrUtil.EMPTY),
    /**
     * User
     */
    USER_OLD_PASSWORD_ERROR(HttpServletResponse.SC_BAD_REQUEST, "修改密码失败，旧密码错误", StrUtil.EMPTY),
    USER_AVATAR_NOT_EMPTY(HttpServletResponse.SC_BAD_REQUEST, "用户头像不能为空", StrUtil.EMPTY),
    USER_AVATAR_UPLOAD_FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "用户头像上传失败", StrUtil.EMPTY),
    USER_CANNOT_UPDATE_SUPER_ADMIN(HttpServletResponse.SC_BAD_REQUEST, "不可以修改超级管理员", StrUtil.EMPTY),
    USER_ACCOUNT_EXIST(HttpServletResponse.SC_BAD_REQUEST, "账号已存在", StrUtil.EMPTY),
    USER_PHONE_EXIST(HttpServletResponse.SC_BAD_REQUEST, "手机号已存在", StrUtil.EMPTY),
    USER_EMAIL_EXIST(HttpServletResponse.SC_BAD_REQUEST, "Email已存在", StrUtil.EMPTY),
    USER_NOT_ONLINE(HttpServletResponse.SC_BAD_REQUEST, "用户已下线", StrUtil.EMPTY),
    USER_CANNOT_RETREAT_CURRENT_ACCOUNT(HttpServletResponse.SC_BAD_REQUEST, "当前登陆用户无法强退", StrUtil.EMPTY),
    USER_ELSEWHERE_LOGIN(HttpServletResponse.SC_UNAUTHORIZED, "您已在别处登录，请您修改密码或重新登录", StrUtil.EMPTY),
    USER_USERNAME_OR_PASSWORD_IS_WRONG(HttpServletResponse.SC_BAD_REQUEST, "用户名、密码或验证码错误", StrUtil.EMPTY),
    USER_BAD_CREDENTIALS(HttpServletResponse.SC_BAD_REQUEST, "凭据无效，拒绝身份验证请求", StrUtil.EMPTY),

    /**
     * Menu
     */
    MENU_EXISTING_LOWER_LEVEL_MENU(HttpServletResponse.SC_BAD_REQUEST, "当前菜单存在子菜单，不允许删除", StrUtil.EMPTY),
    MENU_EXISTING_USING(HttpServletResponse.SC_BAD_REQUEST, "菜单已被使用，不允许删除", StrUtil.EMPTY), MENU_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "菜单名称已存在", StrUtil.EMPTY),
    /**
     * File
     */
    FILE_UPLOAD_FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件上传失败", StrUtil.EMPTY), FILE_DOWNLOAD_FAIL(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件下载失败", StrUtil.EMPTY),
    FILE_ILLEGAL_FILENAME(HttpServletResponse.SC_BAD_REQUEST, "文件名称非法，不允许下载", StrUtil.EMPTY),
    /**
     * Job
     */
    JOB_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, "未找到该定时任务", StrUtil.EMPTY),
    /**
     * Config
     */
    CONFIG_KEY_EXIST(HttpServletResponse.SC_BAD_REQUEST, "参数键名已存在", StrUtil.EMPTY),
    /**
     * Dict
     */
    DICT_TYPE_EXIST(HttpServletResponse.SC_BAD_REQUEST, "字典类型已存在", StrUtil.EMPTY),
    /**
     * Post
     */
    POST_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "岗位名称已存在", StrUtil.EMPTY), POST_CODE_EXIST(HttpServletResponse.SC_BAD_REQUEST, "岗位编码已存在", StrUtil.EMPTY),
    /**
     * Role
     */
    ROLE_NAME_EXIST(HttpServletResponse.SC_BAD_REQUEST, "角色名称已存在", StrUtil.EMPTY), ROLE_KEY_EXIST(HttpServletResponse.SC_BAD_REQUEST, "角色权限已存在", StrUtil.EMPTY),
    /**
     * Gen
     */
    GEN_IMPORT_TABLE_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "生成代码表导入错误", StrUtil.EMPTY),

    ;

    private final int code;
    private final String msg;
    private final String pagePath;

    ErrorCodeEnum(int code, String msg, String pagePath) {
        this.code = code;
        this.msg = msg;
        this.pagePath = pagePath;
    }

    /**
     * 转换为ErrorCode(自定义返回消息)
     *
     * @param msg
     * @return
     */
    public ErrorCode overrideMsg(String msg) {
        return ErrorCode.builder().code(code()).exception(name()).msg(msg).build();
    }

    /**
     * 转换为ErrorCode(自定义返回消息)
     *
     * @param path
     * @return
     */
    public ErrorCode overridePath(String path) {
        return ErrorCode.builder().code(code()).exception(name()).msg(msg()).path(path).build();
    }

    /**
     * 转换为ErrorCode(自定义返回消息)
     *
     * @param msg
     * @return
     */
    public ErrorCode convert(String msg) {
        return ErrorCode.builder().code(code()).exception(name()).msg(msg).build();
    }

    /**
     * 转换为ErrorCode
     *
     * @return
     */
    public ErrorCode convert() {
        return ErrorCode.builder().code(code()).exception(name()).msg(msg()).build();
    }

    public static ErrorCodeEnum getErrorCode(String errorCode) {
        ErrorCodeEnum[] enums = ErrorCodeEnum.values();
        for (ErrorCodeEnum errorCodeEnum : enums) {
            if (errorCodeEnum.name().equalsIgnoreCase(errorCode)) {
                return errorCodeEnum;
            }
        }
        throw new UnknownEnumException("Error: Unknown errorCode, or do not support changing errorCode!\n");
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public String pagePath() {
        return this.pagePath;
    }

}
