package com.super2k.receiptcollector.data;

/**
 * An item from a receipt, agnostic and non proprietary
 *
 */
public class Item {

    protected float quantity;
    protected float price;
    protected String personalOfferId;
    protected float voucherValue;
    protected float disountQuantity;
    protected String slipId;
    protected String itemDesc;
    protected float discountValue;
    protected String receiptId;

    public Item(float quantity, float price, String personalOfferId, float voucherValue, float discountQuantity,
            String slipId, String itemDesc, float discountValue, String receiptId) {
        this.quantity = quantity;
        this.price = price;
        this.personalOfferId = personalOfferId;
        this.voucherValue = voucherValue;
        this.disountQuantity = discountQuantity;
        this.slipId = slipId;
        this.itemDesc = itemDesc;
        this.discountValue = discountValue;
        this.receiptId = receiptId;
        if (itemDesc.trim().length() == 0) {
            System.out.println("Empty desc");
        }
    }

    public float getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getPersonalOfferId() {
        return personalOfferId;
    }

    public float getVoucherValue() {
        return voucherValue;
    }

    public float getDisountQuantity() {
        return disountQuantity;
    }

    public String getSlipId() {
        return slipId;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public float getDiscountValue() {
        return discountValue;
    }

    public String getReceiptId() {
        return receiptId;
    }

}
