package com.dingdian.order.ui.main.order.unreceive;

import android.content.Context;
import com.dingdian.order.R;
import com.dingdian.order.bean.Op;
import com.dingdian.order.bean.OrderBean;
import com.dingdian.order.comm.LocalUser;
import com.dingdian.order.ui.widget.printutil.PrintDataMaker;
import com.dingdian.order.ui.widget.printutil.PrinterWriter;
import com.dingdian.order.ui.widget.printutil.PrinterWriter58mm;
import com.dingdian.order.ui.widget.printutil.PrinterWriter80mm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PrintOrderDataMaker implements PrintDataMaker {

    private String qr;
    private int width;
    private int height;
    Context btService;
    private OrderBean order;
    private String remark = "微点筷客推出了餐厅管理系统，可用手机快速接单（来自客户的预订订单），进行订单管理、后厨管理等管理餐厅。";


    public PrintOrderDataMaker(Context btService,OrderBean order,String qr, int width, int height) {
        this.qr = qr;
        this.width = width;
        this.height = height;
        this.btService = btService;
        this.order = order;
    }

    @Override
    public List<byte[]> getPrintData(int type) {
        ArrayList<byte[]> data = new ArrayList<>();

        try {
            PrinterWriter printer;
            printer = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
            printer.setAlignCenter();
            data.add(printer.getDataAndReset());



            printer.setAlignLeft();
            printer.printLine();
            printer.printLineFeed();
            String waiMai = "#"+order.getWaterNumber()+order.getTyp();
            printer.setFontSize(1);
            printer.print(waiMai);
            printer.setFontSize(0);
            printer.printLineFeed();
            printer.printLineFeed();
            //订单编号
            printer.print(order.getId()+"*");
            printer.printLineFeed();
            //店铺名字
            printer.print("*"+order.getShopName()+"*");
            printer.printLineFeed();
            //打印时间
            printer.print(order.getCreateTime());
            printer.printLineFeed();

            //一行星星
            printer.printLineStars();
            printer.printLineFeed();

            //打印商品
            printer.printLineFeed();
            printer.setAlignCenter();
            printer.printStringOtherLine("商品");
            printer.printLineFeed();
            printer.printLineFeed();

            printer.setAlignLeft();
//            printer.printLine();
//            printer.printLineFeed();

            //打印商品信息
            if (LocalUser.INSTANCE.isSmallFont()){
                for (int i = 0;i<order.getOp().size();i++){

                    Op op = order.getOp().get(i);
                    //打印名字
                    printer.print(op.getProductName());

                    //打印属性
                    printer.print("("+op.getAttributeName()+")");
                    printer.print("    ");

                    //打印份数
                    printer.print("*"+op.getProductCount());
                    //打印单价
                    printer.print("    ");

                    printer.print(""+op.getTotalPrice());
                    printer.printLineFeed();
                }
            }else {
                for (int i = 0;i<order.getOp().size();i++){

                    Op op = order.getOp().get(i);
                    //打印名字
                    printer.setFontSize(1);
                    printer.print(op.getProductName());

                    //打印属性
                    printer.setFontSize(0);
                    printer.print("("+op.getAttributeName()+")");
                    printer.print("    ");

                    //打印份数
                    printer.setFontSize(1);
                    printer.print("*"+op.getProductCount());
                    //打印单价
                    printer.setFontSize(0);
                    printer.print("    ");

                    printer.setFontSize(1);
                    printer.print(""+op.getTotalPrice());
                    printer.printLineFeed();
                }
            }

            printer.setFontSize(0);
            printer.printLineFeed();

            //打印其他
            printer.setAlignCenter();
            printer.printStringOtherLine("其他");
            printer.printLineFeed();
            //打印虚线
            printer.setAlignLeft();
//            printer.printLine();
            printer.printLineFeed();

            //配送费
            printer.printInOneLine("配送费：",order.getSendPrice()+"",0);
            printer.printLineFeed();
            //餐盒费
            printer.printInOneLine("餐盒费：",order.getBoxPrice()+"",0);
            printer.printLineFeed();

            //满减优惠
            if (order.getDiscountPrice() > 0){
                //餐盒费
                printer.printInOneLine("满减优惠：",order.getDiscountPrice()+"",0);
                printer.printLineFeed();
            }

            //原价格
            printer.printInOneLine("原价格：",(order.getPayPrice() + order.getDiscountPrice())+"",0);
            printer.printLineFeed();
            //实际支付
            printer.printInOneLine("实际支付：",order.getPayPrice()+"",0);
            printer.printLineFeed();
            printer.printLineFeed();

            printer.setAlignLeft();
            printer.printLine();
            printer.printLineFeed();
            printer.printLineFeed();

            //备注
            printer.setAlignLeft();
            printer.setFontSize(1);
            printer.print("备注："+order.getRemark());
            printer.setFontSize(0);
            printer.printLineFeed();

            printer.printLineFeed();
            printer.print(order.getAddressDetail()+","+order.getAddressPhone()+","+order.getAddressName());


            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.feedPaperCutPartial();

            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
