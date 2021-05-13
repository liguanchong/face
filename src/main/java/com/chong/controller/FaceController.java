package com.chong.controller;
import com.chong.utils.FaceUtil;
import cn.hutool.core.codec.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.UUID;


@Controller
@RequestMapping("/")
public class FaceController {

      @RequestMapping("/jiance")
    public void jiance(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
          //先获取要上传的文件目录
          String path = request.getSession().getServletContext().getRealPath("/upload/");
          //创建File对象，一会向该路径下上传文件
          File file = new File(path);
          //判断目录是否存在，如果不存在则创建
          if (file.exists() == false) {
              file.mkdir();
          }
          String img = request.getParameter("imgData");
          //创建要上传的图片文件，还不存在
          File picture = null;
          byte[] decode = Base64.decode(base64Process(img));
          String filename = UUID.randomUUID().toString() + ".png";
          picture = new File(path, "img.png");
          FileOutputStream fileOutputStream = new FileOutputStream(picture);
          fileOutputStream.write(decode);
          fileOutputStream.close();
          boolean res = false;
          try {
              //判断是否包含人脸信息
              String faceToken = FaceUtil.detect(picture);
              if (faceToken != null) {
                  //如果包含，进行查找
                  res = FaceUtil.search(faceToken);
                  } else {

                  }
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              picture.delete();
              PrintWriter pw = response.getWriter();
              String msg = "{\"success\":" + res + "}";
              pw.write(msg);
              pw.close();
          }

    }


        @RequestMapping("/luru")
    public void luru(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        //先获取要上传的文件目录
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        //创建File对象，一会向该路径下上传文件
        File file = new File(path);
        //判断目录是否存在，如果不存在则创建
        if (file.exists() == false) {
            file.mkdir();
        }
        String img = request.getParameter("imgData");
        //创建要上传的图片文件，还不存在
        File picture = null;
        byte[] decode = Base64.decode(base64Process(img));
        String filename = UUID.randomUUID().toString() + ".png";
        picture = new File(path, "img.png");
        FileOutputStream fileOutputStream = new FileOutputStream(picture);
        fileOutputStream.write(decode);
        fileOutputStream.close();

        boolean res = false;
        boolean delFlag = true;
        try {
            //判断是否包含人脸信息
            String faceToken = FaceUtil.detect(picture);
            System.out.println("facetoken"+faceToken);
            if (faceToken != null) {
                //如果包含，进行查找
                res = FaceUtil.search(faceToken);
                System.out.println("res"+res);
                if (res) { //注册过，删除照片
                    res = false;
                } else {
                    FaceUtil.addFace(faceToken);//没有，添加人脸
                    res = true;
                    delFlag = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (delFlag) {
                picture.delete();
            }
            PrintWriter pw = response.getWriter();
            String msg = "{\"success\":" + res + "}";
            System.out.println("picture"+picture);
            System.out.println("deflag"+delFlag);
            System.out.println("res"+res);
            pw.write(msg);
            pw.close();
        }
    }
    private String base64Process(String base64Str) {
        if (!StringUtils.isEmpty(base64Str)) {
            String photoBase64 = base64Str.substring(0, 30).toLowerCase();
            int indexOf = photoBase64.indexOf("base64,");
            if (indexOf > 0) {
                base64Str = base64Str.substring(indexOf + 7);
            }
            return base64Str;
        } else {
            return "";
        }
    }


}




