package com.imooc.api.controller.center;

import com.imooc.api.controller.BaseController;
import com.imooc.api.resource.FileUpload;
import com.imooc.bo.center.CenterUserBO;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.DateUtil;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyong
 */
@Api(value = "用户信息", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("/userInfo")
public class CentUserController extends BaseController {

    @Autowired
    CenterUserService centerUserService;

    @Autowired
    FileUpload fileUpload;

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("/uploadFace")
    public IMOOCJSONResult uploadFace(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 定义头像保存地址
//        String fileSpec = IMAGE_USER_FACE_LOCATION;
        String fileSpec = fileUpload.getImageUserFaceLocation();

        // 在路径上为每一个用户增加一个userId，用于区分不同用户上传
        String uploadPathPrefix = File.separator + userId;
        String newFileName = null;
        // 开始文件上传
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                // 获得文件上传的文件名称
                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    // 文件重命名 imooc-face.png -> ["imooc-face, "png"]
                    String[] fileNameArr = fileName.split("\\.");

                    if (fileNameArr.length < 1) {
                        return IMOOCJSONResult.errorMsg("文件没有后缀名");
                    }

                    // 获取文件的后缀名
                    String suffix = fileNameArr[fileNameArr.length - 1];

                    if (!"png".equalsIgnoreCase(suffix)
                            && !"jpg".equalsIgnoreCase(suffix)
                            && !"jpeg".equalsIgnoreCase(suffix)) {
                        return IMOOCJSONResult.errorMsg("图片格式不正确");
                    }

                    // face-{userId}.png
                    // 文件名称重组, 覆盖式上传，增量式：额外拼接当前时间
                    newFileName = "face-" + userId + "." + suffix;

                    // 上传头像最终保存位置
                    String finalFacePath = fileSpec + uploadPathPrefix + File.separator + newFileName;
                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null) {
                        // 创建文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    // 文件输出保存到目录
                    fileOutputStream = new FileOutputStream(outFile);
                    InputStream inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return IMOOCJSONResult.errorMsg("文件不能为空！");
        }
        // 更新用户头像到数据库
        String faceUrl = fileUpload.getImageServerUrl() + "/" + userId + "/" + newFileName + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);
        centerUserService.updateUserFace(userId, faceUrl);
        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("/update")
    public IMOOCJSONResult update(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (result.hasErrors()) {
            Map<String, String> errorMap = getErrors(result);
            return IMOOCJSONResult.errorMap(errorMap);
        }

        Users user = centerUserService.updateUserInfo(userId, centerUserBO);

        setNullProperty(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);

        // TODO 后续要改，增加令牌token，会整合redis，分布式会话
        return IMOOCJSONResult.ok();
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}
