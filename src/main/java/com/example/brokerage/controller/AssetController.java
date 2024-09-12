package com.example.brokerage.controller;

import com.example.brokerage.controller.dto.AssetDto;
import com.example.brokerage.controller.resource.AssetResource;
import com.example.brokerage.mapper.Mapper;
import com.example.brokerage.model.Asset;
import com.example.brokerage.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    @PostMapping("/deposit")
    @PreAuthorize("hasRole('USER')")
    public String depositMoney(@RequestBody AssetDto assetDto, @RequestParam Double amount) {
        assetService.depositMoney(Mapper.INSTANCE.assetDtoToAsset(assetDto), amount);
        return ("Money deposited successfully");
    }

    @PostMapping("/withdraw")
    @PreAuthorize("hasRole('USER')")
    public String withdrawMoney(@RequestParam Integer customerId, @RequestParam double amount, @RequestParam String iban) {
        assetService.withdrawMoney(customerId, amount, iban);
        return ("Money withdrawn successfully");
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public List<AssetResource> listAssets(@RequestParam Integer customerId) {
        return Mapper.INSTANCE.assetListToAssetResourceList(assetService.listAssets(customerId));
    }
}
