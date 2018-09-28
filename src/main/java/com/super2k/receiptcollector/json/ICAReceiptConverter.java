package com.super2k.receiptcollector.json;

import java.util.ArrayList;
import java.util.List;

import com.super2k.receiptcollector.dao.ReceiptLoader;
import com.super2k.receiptcollector.dao.Receipts;
import com.super2k.receiptcollector.data.Receipt;
import com.super2k.receiptcollector.data.ReceiptsFactory;
import com.super2k.receiptcollector.json.ICAReceiptRows.RowTransaction;
import com.super2k.receiptcollector.json.ICAReceipts.ReceiptTransaction;

/**
 * Container for receipts and receipt rows
 *
 */
public class ICAReceiptConverter implements ReceiptLoader.ReceiptConverter {

    public final static String ICA_BRAND = "ICA";

    protected ICAReceipts receipts;
    protected ICAReceiptRows rows;

    public ICAReceiptConverter(ICAReceipts receipts, ICAReceiptRows rows) {
        this.receipts = receipts;
        this.rows = rows;
    }

    @Override
    public Receipts convertReceipts() {

        List<RowTransaction> rowList = new ArrayList<>();
        Receipt[] result = new Receipt[receipts.getReceiptCount()];
        int index = 0;
        for (ReceiptTransaction receipt : receipts.getReceipts()) {
            if (receipt != null) {
                rowList.clear();
                this.rows.findRows(receipt, rowList);
                result[index] = receipt.createReceipt(receipts.transactionHeader.customerId, rowList);
                index++;
            }
        }
        return ReceiptsFactory.getInstance().createReceipts(result);
    }

}
