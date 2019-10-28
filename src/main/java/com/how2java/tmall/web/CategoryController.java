package com.how2java.tmall.web;
 
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
  
@RestController
public class CategoryController {
    @Autowired CategoryService categoryService;
     
    @GetMapping("/categories")
    public Page4Navigator<Category> list(@RequestParam(value = "start",defaultValue = "0") int start,@RequestParam
            (value = "size",defaultValue = "5")int size)
            //RequestParam获取请求参数的值，也就是 ？后面start的值
            throws Exception {
        start=start<0?0:start;
        return categoryService.list(start,size,5);
    }
    @PostMapping("/categories")
    public Object add(Category bean, MultipartFile image, HttpServletRequest request) throws Exception {
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }
    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }
    @DeleteMapping("/categories/{id}")
    public String  delete(@PathVariable("id") int id,HttpServletRequest request) throws Exception {
        //@PathVariable 接收请求路径中变量的值，也就是获得id的值
        categoryService.delete(id);
        File imgFolder = new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imgFolder, id + ".jpg");
        file.delete();
        return null;
    }
      @GetMapping("/categories/{id}")   // ??????????????
        public Category get(@PathVariable("id")int id,HttpServletRequest request)
        throws Exception {
             return   categoryService.get(id);

      }
      @PutMapping("/categories/{id}")
      //  前端的bean无法自动注入下面的bean（put方式无法注入），image和name是自动注入的
     public Object update( Category bean ,MultipartFile image,HttpServletRequest request,String name)throws Exception{
           bean.setName(name);  // 或者  String name=request.getParameter("name");
          categoryService.add(bean);
          if(image!=null) {
              saveOrUpdateImageFile(bean, image, request);
          }
         return bean;
      }

    }




