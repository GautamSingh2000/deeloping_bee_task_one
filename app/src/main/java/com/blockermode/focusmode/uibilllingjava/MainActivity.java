package com.blockermode.focusmode.uibilllingjava;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.SkuDetailsParams;
import com.blockermode.focusmode.uibilllingjava.Entity.AppDatabase;
import com.blockermode.focusmode.uibilllingjava.Entity.Subscription;
import com.blockermode.focusmode.uibilllingjava.model.PremiumSelectionDetail;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements PremiumAdapter.OnItemClickListener{
    RecyclerView recyclerView;
    Activity activity;

    PremiumAdapter adapter;
    ArrayList<PremiumSelectionDetail> list = new ArrayList<>();
    PremiumSelectionDetail premiumSelectionDetail;
    AppDatabase appDatabase;
    String productid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        activity = this;
        appDatabase = AppDatabase.getInstance(this);

        findViewById(R.id.button_close).setOnClickListener(view -> onBackPressed());
        recyclerViewSetup();

        findViewById(R.id.buttonBuy).setOnClickListener(view -> {
            if (adapter.getSelected() == null) {
                Toast.makeText(activity, "No Selection", Toast.LENGTH_SHORT).show();
                return;
            }

            PremiumSelectionDetail selectedPremium = adapter.getSelected();
            String productId = selectedPremium.getProductId();

            Subscription subscription = new Subscription();
            subscription.setProductId(productId);
            new InsertSubscriptionTask().execute(subscription);
            // Check if the user has already purchased the subscription
            Subscription purchasedSubscription = appDatabase.subscriptionDao().getSubscriptionById(productId);



            if (purchasedSubscription != null) {
                // User has already purchased the subscription
                Toast.makeText(activity, "Item already purchased.", Toast.LENGTH_SHORT).show();
            } else {
//
//                 BillingClient billingClient = BillingClient.newBuilder(activity).enablePendingPurchases().build();
//                 // Connect to the Google Play Billing service
//                 billingClient.startConnection(new BillingClientStateListener() {
//                     @Override
//                     public void onBillingSetupFinished(BillingResult billingResult) {
//                         if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
//                             // Query SKU details to get the specific product details
//                             SkuDetailsParams params = SkuDetailsParams.newBuilder()
//                                     .setSkusList(Collections.singletonList(productId))
//                                     .setType(BillingClient.SkuType.SUBS) // Or BillingClient.SkuType.INAPP for one-time purchase
//                                     .build();
//                             billingClient.querySkuDetailsAsync(params, (billingResult, skuDetailsList) -> {
//                                 if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && skuDetailsList != null) {
//                                     // Launch the billing flow for the selected product
//                                     BillingFlowParams flowParams = BillingFlowParams.newBuilder()
//                                             .setSkuDetails(skuDetailsList.get(0))
//                                             .build();
//                                     BillingResult result = billingClient.launchBillingFlow(activity, flowParams);
//                                     // Handle the result of the billing flow (e.g., success, failure, etc.)
//                                 }
//                             });
//                         }
//                     }
//
//                     @Override
//                     public void onBillingServiceDisconnected() {
//                         // Handle billing service disconnect
//                     }
//                 });
            }
        });
    }


    private void recyclerViewSetup() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list.add(new PremiumSelectionDetail(
                "BlockerMode Monthly Subscription",
                "₹120 / Monthly",
                "bm_monthly_subscription",
                "bm-monthly-subscription",
                "subscription",1
        ));

        list.add(new PremiumSelectionDetail(
                "BlockerMode Quarterly Subscription",
                "₹350 / Quarterly",
                "bm_quarterly_subscription",
                "bm-quarterly-subscription",
                "subscription",2
        ));

        list.add(new PremiumSelectionDetail(
                "BlockerMode Yearly Subscription",
                "₹1,200 / Yearly",
                "bm_yearly_subscription",
                "bm-yearly-subscription",
                "subscription",3
        ));

        list.add(new PremiumSelectionDetail(
                "BlockerMode One Time Purchase Subscription",
                "₹5,500 / One Time Purchase",
                "bm_one_time_purchase_subscription",
                "purchase",
                "purchase",4
        ));

        adapter = new PremiumAdapter(activity, list);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(int position) {
        productid = list.get(position).getProductId();
    }



    private class InsertSubscriptionTask extends AsyncTask<Subscription, Void, Void> {
        @Override
        protected Void doInBackground(Subscription... subscriptions) {
            appDatabase.subscriptionDao().insertSubscription(subscriptions[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(activity, "Your subscription is stored !!", Toast.LENGTH_SHORT).show();
        }
    }
}