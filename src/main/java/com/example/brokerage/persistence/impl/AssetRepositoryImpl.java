package com.example.brokerage.persistence.impl;

import com.example.brokerage.mapper.Mapper;
import com.example.brokerage.model.Asset;
import com.example.brokerage.persistence.AssetEntity;
import com.example.brokerage.persistence.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AssetRepositoryImpl implements AssetRepository {

    private final AssetH2Repository assetH2Repository;

    @Override
    public Asset findByCustomerIdAndAssetName(Integer customerId, String assetName) {
        Optional<AssetEntity> assetEntity = assetH2Repository.findByCustomerIdAndAssetName(customerId, assetName);
        return assetEntity.map(Mapper.INSTANCE::assetEntityToAsset).orElse(null);
    }

    @Override
    public List<Asset> findByCustomerId(Integer customerId) {
        List<AssetEntity> assetEntities = assetH2Repository.findByCustomerId(customerId);
        return Mapper.INSTANCE.assetEntityListToAssetList(assetEntities);
    }

    @Override
    public Asset save(Asset asset) {
        AssetEntity assetEntity = Mapper.INSTANCE.assetToAssetEntity(asset);
        AssetEntity savedAssetEntity = assetH2Repository.save(assetEntity);
        return Mapper.INSTANCE.assetEntityToAsset(savedAssetEntity);
    }
}

