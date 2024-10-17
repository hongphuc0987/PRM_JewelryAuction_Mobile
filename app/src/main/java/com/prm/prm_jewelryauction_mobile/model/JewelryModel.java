package com.prm.prm_jewelryauction_mobile.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class JewelryModel extends BaseModel{
    private int id;
    private UserModel sellerId;
    private String name;
    private String description;
    private CategoryModel category;
    private double weight;
    private String size;
    private String color;
    private String sex;
    private BrandModel brand;
    private String jewelryCondition;
    private double staringPrice;
    private String status;
    private CollectionModel collection;
    private List<JewelryImageModel> jewelryImages;
    private List<JewelryMaterialModel> jewelryMaterials;
    private String thumbnail;
    private double price;

    public JewelryModel(String createdAt, String updatedAt, int id, UserModel sellerId, String name, String description, CategoryModel category, double weight, String size, String color, String sex, BrandModel brand, String jewelryCondition, double staringPrice, String status, CollectionModel collection, List<JewelryImageModel> jewelryImages, List<JewelryMaterialModel> jewelryMaterials, String thumbnail, double price) {
        super(createdAt, updatedAt);
        this.id = id;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.sex = sex;
        this.brand = brand;
        this.jewelryCondition = jewelryCondition;
        this.staringPrice = staringPrice;
        this.status = status;
        this.collection = collection;
        this.jewelryImages = jewelryImages;
        this.jewelryMaterials = jewelryMaterials;
        this.thumbnail = thumbnail;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getSellerId() {
        return sellerId;
    }

    public void setSellerId(UserModel sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BrandModel getBrand() {
        return brand;
    }

    public void setBrand(BrandModel brand) {
        this.brand = brand;
    }

    public String getJewelryCondition() {
        return jewelryCondition;
    }

    public void setJewelryCondition(String jewelryCondition) {
        this.jewelryCondition = jewelryCondition;
    }

    public double getStaringPrice() {
        return staringPrice;
    }

    public void setStaringPrice(double staringPrice) {
        this.staringPrice = staringPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CollectionModel getCollection() {
        return collection;
    }

    public void setCollection(CollectionModel collection) {
        this.collection = collection;
    }

    public List<JewelryImageModel> getJewelryImages() {
        return jewelryImages;
    }

    public void setJewelryImages(List<JewelryImageModel> jewelryImages) {
        this.jewelryImages = jewelryImages;
    }

    public List<JewelryMaterialModel> getJewelryMaterials() {
        return jewelryMaterials;
    }

    public void setJewelryMaterials(List<JewelryMaterialModel> jewelryMaterials) {
        this.jewelryMaterials = jewelryMaterials;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
