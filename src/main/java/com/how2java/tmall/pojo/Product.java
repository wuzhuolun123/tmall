package com.how2java.tmall.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Product")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    int id;

   @ManyToOne
   @JoinColumn(name="cid")
   private Category category;   // ？？？？？？？？
    @Transient   //表示该属性不是到数据库表字段的映射
    private ProductImage firstProductImage;


    @Transient
    private List<ProductImage> productSingleImages;
    @Transient
    private List<ProductImage> productDetailImages;
    @Transient
    private int saleCount;
    @Transient
    private int reviewCount;

    private  String name;
    private  String subTitle;
    private  float originalPrice;
    private  float promotePrice;
    private  Date createDate;
    private  int stock;
    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }

    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }
    public   int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public Category getCategory() {
        return category;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public float getPromotePrice() {
        return promotePrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setPromotePrice(float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }
    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }
}
