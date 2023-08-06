package com.blockermode.focusmode.uibilllingjava.model;

import java.io.Serializable;

public class PremiumSelectionDetail implements Serializable {

    private boolean isChecked = false;
    private String title;
    private String price;
    private String productId;
    private String basePlanId;
    private String type;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PremiumSelectionDetail(String title, String price, String productId, String basePlanId, String type, int id) {
        this.title = title;
        this.price = price;
        this.productId = productId;
        this.basePlanId = basePlanId;
        this.type = type;
        this.id=id;
    }


    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBasePlanId() {
        return basePlanId;
    }

    public void setBasePlanId(String basePlanId) {
        this.basePlanId = basePlanId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
