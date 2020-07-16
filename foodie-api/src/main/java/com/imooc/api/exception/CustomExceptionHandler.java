package com.imooc.api.exception;

import com.imooc.utils.IMOOCJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 自定义异常助手类
 *
 * @author wangyong
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 上传文件超过500KB，捕获异常MaxUploadSizeExceededException
     *
     * @param ex 异常
     * @return 返给前端的内容
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public IMOOCJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        return IMOOCJSONResult.errorMap("文件上传大小不能超过500KB,请压缩或降低图片质量在上传！");
    }

}
