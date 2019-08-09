package com.dingdian.order.ui.bluetooth;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.dingdian.order.bean.OrderBean;
import com.dingdian.order.ui.widget.print.GPrinterCommand;
import com.dingdian.order.ui.widget.print.PrintPic;
import com.dingdian.order.ui.widget.print.PrintQueue;
import com.dingdian.order.ui.widget.print.PrintUtil;
import com.dingdian.order.ui.widget.printutil.PrintOrderDataMaker;
import com.dingdian.order.ui.widget.printutil.PrinterWriter;
import com.dingdian.order.ui.widget.printutil.PrinterWriter58mm;
import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by liuguirong on 8/1/17.
 * <p/>
 * print ticket service
 */
public class BtService extends IntentService {

    public BtService() {
        super("BtService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BtService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(PrintUtil.ACTION_PRINT_TEST)) {
            printTest();
        } else if (intent.getAction().equals(PrintUtil.ACTION_PRINT_TEST_TWO)) {
            printTesttwo(3);
        }else if (intent.getAction().equals(PrintUtil.ACTION_PRINT_BITMAP)) {
            printBitmapTest();
        }else if (intent.getAction().equals(PrintUtil.ACTION_PRINT_TICKET)){
           OrderBean orderBean = (OrderBean)intent.getSerializableExtra("order");
           printOrder(orderBean);
        }

    }

    private void printTest() {
            PrintOrderDataMaker printOrderDataMaker = new PrintOrderDataMaker(this,"", PrinterWriter58mm.TYPE_58, PrinterWriter.HEIGHT_PARTING_DEFAULT);
            ArrayList<byte[]> printData = (ArrayList<byte[]>) printOrderDataMaker.getPrintData(PrinterWriter58mm.TYPE_58);
            PrintQueue.getQueue(getApplicationContext()).add(printData);

    }

    private void printOrder(OrderBean orderBean){
        com.dingdian.order.ui.main.order.unreceive.PrintOrderDataMaker printOrderDataMaker = new com.dingdian.order.ui.main.order.unreceive.PrintOrderDataMaker(this,orderBean,"", PrinterWriter58mm.TYPE_58, PrinterWriter.HEIGHT_PARTING_DEFAULT);
        ArrayList<byte[]> printData = (ArrayList<byte[]>) printOrderDataMaker.getPrintData(PrinterWriter58mm.TYPE_58);
        PrintQueue.getQueue(getApplicationContext()).add(printData);
    }

    /**
     * 打印几遍
     * @param num
     */
  private void printTesttwo(int num) {
        try {
            ArrayList<byte[]> bytes = new ArrayList<byte[]>();
            for (int i = 0; i < num; i++) {
                String message = "蓝牙打印测试\n蓝牙打印测试\n蓝牙打印测试\n\n";
                bytes.add(GPrinterCommand.reset);
                bytes.add(message.getBytes("gbk"));
                bytes.add(GPrinterCommand
                        .print);
                bytes.add(GPrinterCommand.print);
                bytes.add(GPrinterCommand.print);
            }
            PrintQueue.getQueue(getApplicationContext()).add(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void print(byte[] byteArrayExtra) {
        if (null == byteArrayExtra || byteArrayExtra.length <= 0) {
            return;
        }
        PrintQueue.getQueue(getApplicationContext()).add(byteArrayExtra);
    }

    private void printBitmapTest() {
        BufferedInputStream bis;
        try {
            bis = new BufferedInputStream(getAssets().open(
                    "icon_empty_bg.bmp"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(bis);
        PrintPic printPic = PrintPic.getInstance();
        printPic.init(bitmap);
        if (null != bitmap) {
            if (bitmap.isRecycled()) {
                bitmap = null;
            } else {
                bitmap.recycle();
                bitmap = null;
            }
        }
        byte[] bytes = printPic.printDraw();
        ArrayList<byte[]> printBytes = new ArrayList<byte[]>();
        printBytes.add(GPrinterCommand.reset);
        printBytes.add(GPrinterCommand.print);
        printBytes.add(bytes);
        Log.e("BtService", "image bytes size is :" + bytes.length);
        printBytes.add(GPrinterCommand.print);
        PrintQueue.getQueue(getApplicationContext()).add(bytes);
    }
//
//    private void printPainting() {
//        byte[] bytes = PrintPic.getInstance().printDraw();
//        ArrayList<byte[]> printBytes = new ArrayList<byte[]>();
//        printBytes.add(GPrinterCommand.reset);
//        printBytes.add(GPrinterCommand.print);
//        printBytes.add(bytes);
//        Log.e("BtService", "image bytes size is :" + bytes.length);
//        printBytes.add(GPrinterCommand.print);
//        PrintQueue.getQueue(getApplicationContext()).add(bytes);
//    }
}