package com.how2java.tmall.web;

import com.how2java.tmall.dao.productDAO;
import com.how2java.tmall.pojo.*;
import com.how2java.tmall.service.*;
import com.how2java.tmall.util.Page4Navigator;
import com.how2java.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;

 @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value="start",defaultValue = "0") int start,@RequestParam(
            value = "size",defaultValue = "5"
 ) int size)throws Exception{
      start=start<0?0:start;
     Page4Navigator<Product> page =productService.list(cid, start, size,5 );
     productImageService.setFirstProdutImages(page.getContent());
    return page;

     }
     @PostMapping("/products")
       public Object add(@RequestBody Product bean)throws Exception{
          bean.setCreateDate(new Date());
            productService.add(bean);
            return bean;
 }
        @DeleteMapping("/products/{id}")
       public String delete(@PathVariable("id") int id)throws Exception{
           productService.delete(id);
           return null;
       }
       @PutMapping("/products")
       public Object update(@RequestBody Product bean)throws Exception{
           productService.update(bean);
           return bean;
       }
    @GetMapping("/products/{id}")
       public  Object get(@PathVariable("id") int id)throws Exception{
           return    productService.get(id);
       }
    @GetMapping("/foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) {
        Product product = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> productDetailImages = productImageService.listDetailProductImages(product);
        product.setProductSingleImages(productSingleImages);          // 设置product的图片
        product.setProductDetailImages(productDetailImages);

        List<PropertyValue> pvs = propertyValueService.list(product);  //获取产品的属性值
        List<Review> reviews = reviewService.list(product);            //获取产品的评价
        productService.setSaleAndReviewNumber(product);     // 设置销量和评价数
        productImageService.setFirstProdutImage(product);  //设置首张图片

        Map<String,Object> map= new HashMap<>();
        map.put("product", product);
        map.put("pvs", pvs);
        map.put("reviews", reviews);

        return Result.success(map);
    }


}
