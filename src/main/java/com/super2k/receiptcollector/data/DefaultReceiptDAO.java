package com.super2k.receiptcollector.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import com.super2k.receiptcollector.dao.ReceiptDAO;
import com.super2k.receiptcollector.dao.Receipts;

/**
 * Default non db implementation that uses java.util.List and java.util.Hashtable to store and sort data.
 * No persistence.
 *
 */
public class DefaultReceiptDAO implements ReceiptDAO {

    @Override
    public Receipts findByDate(Receipts receipts, SimpleDate begin, SimpleDate end) {
        return findByDate(receipts,
                begin != null ? new GregorianCalendar(begin.year, begin.month, begin.day).getTime() : null,
                end != null ? new GregorianCalendar(end.year, end.month, end.day).getTime() : null);
    }

    @Override
    public Receipts findByDate(Receipts receipts, Date begin, Date end) {
        List<Receipt> list = receipts.getReceipts();
        List<Receipt> result = new ArrayList<>();
        for (Receipt r : list) {
            if ((begin == null
                    || begin.before(r.transactionTimestamp) && (end == null || end.after(r.transactionTimestamp)))) {
                result.add(r);
            }
        }
        return new DefaultReceipts(result);
    }

    @Override
    public List<ItemCollection> sortByItemsPrice(Receipts receipts) {
        List<ItemCollection> result = sortByItems(receipts);
        Collections.sort(result);
        return result;
    }

    protected List<ItemCollection> sortByItems(Receipts receipts) {
        List<ItemCollection> result = new ArrayList<>();
        List<Receipt> receiptList = receipts.getReceipts();
        Hashtable<String, List<Item>> itemListTable = new Hashtable<>();
        for (Receipt r : receiptList) {
            for (Item i : r.items) {
                List<Item> iList = itemListTable.get(i.itemDesc);
                if (iList == null) {
                    iList = new ArrayList<>();
                    itemListTable.put(i.getItemDesc(), iList);
                }
                iList.add(i);
            }
        }
        for (List<Item> iList : itemListTable.values()) {
            result.add(new ItemCollection(iList));
        }
        return result;
    }

    @Override
    public List<Store> findStores(Receipts receipts) {
        List<Store> result = new ArrayList<>();
        List<Receipt> receiptList = receipts.getReceipts();
        for (Receipt r : receiptList) {
            // TODO - lazy method to sort by stores, improve.
            if (!result.contains(r.store)) {
                result.add(r.store);
            }
        }
        return result;
    }

    @Override
    public Receipts findByStore(Receipts receipts, String storeName) {
        List<Receipt> storeReceipts = new ArrayList<>();
        for (Receipt r : receipts.getReceipts()) {
            if (r.store.getName().contentEquals(storeName)) {
                storeReceipts.add(r);
            }
        }
        return new DefaultReceipts(storeReceipts);
    }

}
