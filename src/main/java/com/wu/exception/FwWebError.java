package com.wu.exception;

/**
 * @author zps
 * @date 2018/9/10
 */
public enum FwWebError {

    // 2000-->Success!
    // 6000-->Fail！
    // 6000也可以作为自定义错误信息状态码

    // common
    COMMON_ERROR("Fail！", 6000),
    COMMON_PARAMS_ERR("提交参数不合法", 6001),
    COMMON_EMPTY_CONDITION_RESULT("没有找到符合条件的数据", 6002),
    COMMON_PARAMS_NOT_EXIST("提交的字段不存在", 6003),

    // sql
    COMMON_SQL_ERROR("mysql通用错误", 6100),
    COMMON_SQL_INSERT_FAIL("增加失败", 6101),
    COMMON_SQL_DELETE_FAIL("删除失败", 6102),
    COMMON_SQL_UPDATE_FAIL("修改失败", 6103),
    COMMON_SQL_NAME_EXIST("该名称已存在", 6104),
    COMMON_SQL_ID_NOT_EXIST("主键ID不能为空", 6105),

    // io
    COMMON_IO_ERROR("io通用错误", 6200),
    COMMON_FILE_NOT_EXIST("文件没找到，请联系管理员", 6201),
    COMMON_FILE_DATA_NULL("文档中不不存在有效的数据", 6202),
    COMMON_FILE_DATA_ERR("文档中的数据格式错误", 6203),

    // form
    COMMON_INVALID_PASSWORD("密码格式错误", 6300),
    COMMON_INVALID_EMAIL("邮件格式错误", 6301),
    COMMON_INVALID_NAME("账号格式错误", 6302),
    COMMON_INVALID_PARAMS("填写字段不合法", 6303),

    // shiro-login
    NO_LOGIN("no_login", 6400),
    UNAUTHORIZED("验证失败", 6401),
    ADMIN_ONLY("只有管理员账号可以调用这个接口", 6402),
    NO_PERSSIOM("没有权限请求", 6403),
    WRONG_ACCOUNT_OR_PSW("账号或密码错误", 6404),
    WRONG_ACCOUNT_PSW("账号密码错误", 6405),
    WRONG_ACCOUNT_WRONG("没验证或验证过期", 401),

    // uploading
    COMMON_UPLOAD_FILE_TYPE_ERROR("上传文件格式错误", 6500),
    COMMON_UPLOAD_FILE_UPLOADING("uploading", 6501),
    COMMON_UPLOAD_FILE_NOT_EXIST("文件不存在", 6502),
    COMMON_UPLOAD_FILE_SIZE_MAX("上传的文件大小不能超过100MB", 6503),

    // es
    ES_BIG_PAGE_SEARCH("单页查询数据不能超过10000!", 9000);

    // NoSQL

    public final String info;
    public final int statusCode;

    FwWebError(String info, int statusCode) {
        this.info = info;
        this.statusCode = statusCode;
    }
}
