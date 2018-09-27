package com.super2k.receiptcollector.data;

import java.util.ArrayList;
import java.util.List;

import com.super2k.receiptcollector.dao.Receipts;

public class DefaultReceipts implements Receipts {

    private List<Receipt> receipts = new ArrayList<>();

    public DefaultReceipts(List<Receipt> receipts) {
        this.receipts.addAll(receipts);
    }

    public DefaultReceipts(Receipt[] receipts) {
        for (Receipt r : receipts) {
            this.receipts.add(r);
        }
    }

    @Override
    public List<Receipt> getReceipts() {
        return receipts;
    }

    @Override
    public int getCount() {
        return receipts.size();
    }

    @Override
    public float getTotalPrice() {
        float price = 0;
        for (Receipt r : receipts) {
            price += r.transactionValue;
        }
        return price;

    }

}
