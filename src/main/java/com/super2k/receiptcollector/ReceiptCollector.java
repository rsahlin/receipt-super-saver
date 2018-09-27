package com.super2k.receiptcollector;

import java.io.IOException;
import java.util.List;

import com.super2k.receiptcollector.dao.ReceiptDAO;
import com.super2k.receiptcollector.dao.ReceiptDAO.SimpleDate;
import com.super2k.receiptcollector.dao.Receipts;
import com.super2k.receiptcollector.data.DefaultReceiptDAO;
import com.super2k.receiptcollector.data.ItemCollection;

/**
 * Simple main class to load ICA receipt data and display most purchased items, sorted by total sum.
 * TODO Check arguments for name of file(s) to load, what converter to use and timespan
 */
public class ReceiptCollector {

    public static void main(String[] args) {
        try {

            // Currently hardcoded
            Receipts receipts = ICAReceiptLoader.getInstance().loadReceipts("C:\\ica\\",
                    new String[] { "Butik_kvitto.json",
                            "Butik_kvittorader.json" });
            System.out.println("Loaded " + receipts.getCount() + " receipts");
            ReceiptDAO dao = new DefaultReceiptDAO();
            Receipts result = dao.findByDate(receipts, new SimpleDate(2018, 7, 1), new SimpleDate(2018, 9, 1));
            print(result);
            List<ItemCollection> itemList = dao.sortByItemsPrice(result);
            System.out.println("Found " + itemList.size() + " items");
            float totalPrice = 0;
            float totalDiscount = 0;
            for (ItemCollection ic : itemList) {
                System.out.println(ic.toString());
                totalPrice += ic.getTotalPrice() - ic.getTotalDiscount();
                totalDiscount += ic.getTotalDiscount();
            }
            System.out.println("Total price: " + totalPrice + ", total discount: " + totalDiscount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(Receipts receipts) {
        System.out.println("Found " + receipts.getCount() + " receipts, total price: " + receipts.getTotalPrice());
    }

}
