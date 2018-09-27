package com.super2k.receiptcollector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.super2k.receiptcollector.dao.ReceiptLoader;
import com.super2k.receiptcollector.dao.Receipts;
import com.super2k.receiptcollector.json.ICAReceiptCollection;
import com.super2k.receiptcollector.json.ICAReceiptRows;
import com.super2k.receiptcollector.json.ICAReceipts;

/**
 * Implementation of receiptloader for ICA receipts
 *
 */
public class ICAReceiptLoader implements ReceiptLoader {

    protected static ReceiptLoader loader;

    public static ReceiptLoader getInstance() {
        if (loader == null) {
            loader = new ICAReceiptLoader();
        }
        return loader;
    }

    @Override
    public Receipts loadReceipts(String path, String[] filenames)
            throws IOException {
        InputStream receiptIs = new FileInputStream(path + filenames[0]);
        InputStream rowIs = new FileInputStream(path + filenames[1]);
        return loadReceipts(new InputStream[] { receiptIs, rowIs });
    }

    @Override
    public Receipts loadReceipts(InputStream[] inputStreams)
            throws IOException {
        try {
            Reader reader = new InputStreamReader(inputStreams[0], "UTF-8");
            // 2018-02-11 09:18:00
            GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
            Gson gson = builder.create();
            ICAReceipts receipts = gson.fromJson(reader, ICAReceipts.class);
            reader = new InputStreamReader(inputStreams[1], "UTF-8");
            ICAReceiptRows rows = gson.fromJson(reader, ICAReceiptRows.class);
            ICAReceiptCollection loaded = new ICAReceiptCollection(receipts, rows);
            return loaded.convertReceipts();
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}
