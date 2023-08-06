package com.blockermode.focusmode.uibilllingjava.Entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SubscriptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubscription(Subscription subscription);

    @Query("SELECT * FROM subscriptions WHERE product_id = :productId")
        Subscription getSubscriptionById(String productId);


    Subscription getSubscriptionById(int subscriptionId);

    @Query("SELECT * FROM subscriptions")
    List<Subscription> getAllSubscriptions();


    @Delete
    void deleteSubscription(Subscription subscription);

    @Update
        void Updatesub(Subscription subscription);
}

