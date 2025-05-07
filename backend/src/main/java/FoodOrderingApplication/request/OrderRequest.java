package com.project.Online.Food.Ordering.backend.request;

import com.project.Online.Food.Ordering.backend.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurtantId;
    private Address deliveryAddress;
}
