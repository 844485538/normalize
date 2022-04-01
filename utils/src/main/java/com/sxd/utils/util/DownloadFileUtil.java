package com.sxd.utils.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author 李健新
 * @Date 2020/11/13
 * @Description
 *
 *      下载文件工具类
 */
public class DownloadFileUtil {

    private static final String CONTENT_LENGTH_ATTR_KRY = "Content-Length";

    private static final String UPLOAD_EXCEL_CONTEXT_TYPE = "application/octet-stream;charset=utf-8";

    /**
     * 以流的形式下载文件
     *
     * @param response
     * @return
     */
    public static void downloadFile(File file, HttpServletRequest request, HttpServletResponse response) {
        try(InputStream fis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // 清空response
            response.reset();
            // 设置Header
            setHeader(file, request, response);
            try (OutputStream toClient = new BufferedOutputStream(response.getOutputStream());) {
                response.setContentType(UPLOAD_EXCEL_CONTEXT_TYPE);
                toClient.write(buffer);
                toClient.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException("下载文件异常");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("下载文件异常");
        }
    }

    /**
     * 设置header解决文件名中文乱码问题
     *
     * @param response
     */
    private static void setHeader(File file, HttpServletRequest request, HttpServletResponse response){
        try {
            String fileName = file.getName();
            // Linux getName()会带出文件的上层目录
            if (fileName.indexOf("tmp") != -1) {
                fileName = fileName.substring(fileName.indexOf("tmp") + 3);
            }
            // 获取浏览器的信息
            String agent = request.getHeader("USER-AGENT");
            if(agent != null && agent.toLowerCase().indexOf("firefox")>0){
                //火狐浏览器自己会对URL进行一次URL转码所以区别处理
                response.setHeader("Content-Disposition",
                        "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO-8859-1"));
                response.setHeader("Content-FileName",new String(fileName.getBytes("GB2312"),"ISO-8859-1"));
            }else if(agent.toLowerCase().indexOf("safari")>0){
                //苹果浏览器需要用ISO 而且文件名得用UTF-8
                response.setHeader("Content-Disposition",
                        "attachment; filename="+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
                response.setHeader("Content-FileName",new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
            }else{
                //其他的浏览器
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-FileName", java.net.URLEncoder.encode(fileName, "UTF-8"));
            }
            // 设置 Content-Length
            response.addHeader("Content-Length", "" + file.length());
            // 允许前端获取请求头
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("下载文件异常");
        }
    }

}
