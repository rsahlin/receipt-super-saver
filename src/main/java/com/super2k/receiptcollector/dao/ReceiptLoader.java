package com.super2k.receiptcollector.dao;

import java.io.IOException;
import java.io.InputStream;

/**
 * Load and convert receipts in an agnostic manner.
 *
 */
public interface ReceiptLoader {

    /**
     * Loads receipts from the specied path and filenames, converts to {@link Receipts} and returns.
     * 
     * @param path
     * @param filenames Implementation dependant, receipt data may be split in multiple files
     * @return Loaded and converted receipts.
     * @throws IOException
     */
    public Receipts loadReceipts(String path, String[] filenames) throws IOException;

    /**
     * 
     * @param inputStreams Implementation dependant, receipt data may be split in multiple files
     * @return Loaded and converted receipts.
     * @throws IOException
     */
    public Receipts loadReceipts(InputStream[] inputStreams) throws IOException;

    /**
     * Used to expose common interface for converting receipt to a known format.
     *
     */
    public interface ReceiptConverter {

        /**
         * Converts and returns all receipts, no specific ordering
         * 
         * @return
         */
        public Receipts convertReceipts();

    }

}
