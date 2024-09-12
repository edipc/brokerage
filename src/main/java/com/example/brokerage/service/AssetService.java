package com.example.brokerage.service;

import com.example.brokerage.exception.ApiMessage;
import com.example.brokerage.exception.custom.BadRequestException;
import com.example.brokerage.exception.custom.IllegalArgumentException;
import com.example.brokerage.model.Asset;
import com.example.brokerage.persistence.repository.AssetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;

    @Transactional
    public void depositMoney(Asset asset, double amount) {

        List<Asset> assets = assetRepository.findByCustomerId(asset.getCustomerId());
        for (Asset ast : assets) {
            if (ast.getAssetName().equals(asset.getAssetName()) && asset.getCustomerId().equals(ast.getCustomerId())) {
                throw new BadRequestException(
                        ApiMessage.ASSET_NOT_MATCH.getStatus(),
                        ApiMessage.ASSET_NOT_MATCH.name(), ApiMessage.ASSET_NOT_MATCH.getMessage());
            }
        }
        asset.setAssetName(asset.getAssetName());
        asset.setCustomerId(asset.getCustomerId());
        asset.setUsableSize(amount);
        asset.setSize(amount);
        log.info("Deposit money successfull with asset name '{}'", asset.getAssetName());
        assetRepository.save(asset);
    }

    @Transactional
    public void withdrawMoney(Integer customerId, double amount, String iban) {
        if (!isValidIban(iban)) {
            throw new IllegalArgumentException(ApiMessage.INVALID_IBAN.getStatus(),
                    ApiMessage.INVALID_IBAN.name(), ApiMessage.INVALID_IBAN.getMessage());
        }

        Asset asset = assetRepository.findByCustomerIdAndAssetName(customerId, "TRY");
        if (asset == null || asset.getUsableSize() < amount) {
            throw new IllegalArgumentException(ApiMessage.ASSET_NOT_FOUND.getStatus(),
                    ApiMessage.ASSET_NOT_FOUND.name(), ApiMessage.ASSET_NOT_FOUND.getMessage());
        }

        asset.setUsableSize(asset.getUsableSize() - amount);
        log.info("Withdraw money successfull with asset name '{}'", asset.getAssetName());
        assetRepository.save(asset);
    }

    private boolean isValidIban(String iban) {
        return iban != null && iban.length() >= 15 && iban.length() <= 34 && iban.matches("[A-Z0-9]+");
    }

    public List<Asset> listAssets(Integer customerId) {
        log.info("Listing assets for customer '{}'", customerId);
        return assetRepository.findByCustomerId(customerId);
    }
}

