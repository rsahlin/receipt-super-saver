package com.super2k.receiptcollector.data;

import java.util.ArrayList;
import java.util.List;

import com.super2k.receiptcollector.dao.Receipts;

/**
 * Singleton used to create {@link Receipts} in order to hide implementation.
 * This implementation uses the {@link DefaultReceipts} implementation
 *
 */
public class ReceiptsFactory {

    public class DefaultReceipts implements Receipts {

        private List<Receipt> receipts = new ArrayList<>();

        private DefaultReceipts() {

        }

        private DefaultReceipts(List<Receipt> receipts) {
            if (receipts != null) {
                this.receipts.addAll(receipts);
            }
        }

        private DefaultReceipts(Receipt[] receipts) {
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

        @Override
        public void addReceipt(Receipt receipt) {
            receipts.add(receipt);
        }

    }

    public static ReceiptsFactory factoryInstance;

    public static ReceiptsFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = createFactory();
        }
        return factoryInstance;
    }

    /**
     * Creates the default receipts factory
     * 
     * @return
     */
    protected static ReceiptsFactory createFactory() {
        return new ReceiptsFactory();
    }

    /**
     * Creates a new Receipts instance
     * 
     * @param list
     * @return
     */
    public Receipts createReceipts(List<Receipt> list) {
        return new DefaultReceipts(list);
    }

    /**
     * Creates a new Receipts instance
     * 
     * @param array
     * @return
     */
    public Receipts createReceipts(Receipt[] array) {
        return new DefaultReceipts(array);
    }

    /**
     * Creates a new Receipts instance
     * 
     * @return New empty receipts
     */
    public Receipts createReceipts() {
        return new DefaultReceipts();
    }

}
