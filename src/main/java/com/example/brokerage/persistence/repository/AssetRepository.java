package com.example.brokerage.persistence.repository;


import com.example.brokerage.model.Asset;

import java.util.List;


public interface AssetRepository  {
    Asset findByCustomerIdAndAssetName(Integer customerId, String assetName);

    List<Asset> findByCustomerId(Integer customerId);

    Asset save(Asset asset);
}
