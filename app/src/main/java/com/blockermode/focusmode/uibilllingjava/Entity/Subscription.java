package com.blockermode.focusmode.uibilllingjava.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity(tableName = "subscriptions")
    public class Subscription {
        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "product_id")
        public String productId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId( String productId) {
            this.productId = productId;
        }

        public Subscription()
        {
        }

    }

