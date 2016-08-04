package cn.itcast.huayu.menu;

/**
 * @author ln：zpf on 2016/7/1
 */
public class testone {


    public static void main(String[] args) {
        int InitProducts[] = {1, 2, 3, 4, 5};
        int deleteProducts[] = {3, 4};
        int i = 0;
        int newProducts[] = new int[deleteProducts.length];
        for (int initItem : InitProducts) {
            for (int deleteItem : deleteProducts) {
                if (deleteItem == initItem) {
                    newProducts[i++] = initItem;
                }
            }

        }
        for (int A : newProducts) {
            System.out.print("输出的内容"+A);
        }
    }


}
