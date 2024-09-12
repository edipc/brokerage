package com.example.brokerage.persistence.impl;


import com.example.brokerage.persistence.AssetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetH2Repository extends JpaRepository<AssetEntity, Integer> {

    Optional<AssetEntity> findByCustomerIdAndAssetName(Integer customerId, String assetName);

    List<AssetEntity> findByCustomerId(Integer customerId);
}
