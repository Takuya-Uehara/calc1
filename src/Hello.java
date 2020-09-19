import java.util.*;

public class Hello {

  public static void main(String[] args) {

    //起動時説明を出力する
    System.out.println("数式を入力してください。(例)1+1");
    System.out.println("プログラムを終了したい場合、endと書いてください。");
    caluculation();
  }
    //計算式を入力
    public static void caluculation (){
      Scanner scanner = new Scanner(System.in);
      System.out.print("計算式：");
      String formula = scanner.nextLine();
      System.out.println(formula);

      //endを検出しプログラムを終わらせる
      if (formula.contains("end")) {
        System.exit(0);
      }

      //数字だけを抽出してdouble型の配列として出力
      String[] numberList =formula.split("[¥+¥-¥*/]");
      double[] doubleNumber = new double[numberList.length];
      for (int i = 0; i < numberList.length; i++) {
        doubleNumber[i] = Double.parseDouble(numberList[i]);
      }

      //演算子だけを抽出
      String[] operatorList = formula.split("[1234567890¥.]+");
      System.out.println(Arrays.deepToString(operatorList));

      //計算をさせる
      int n = 1;
      double total = doubleNumber[0];

      for (n=1;n<operatorList.length;n++) {
        if (operatorList[n].contains("+")) {
          total = total + doubleNumber[n];
        } else if (operatorList[n].contains("-")) {
          total = total - doubleNumber[n];
        } else if (operatorList[n].contains("*")) {
          total = total * doubleNumber[n];
        } else if (operatorList[n].contains("/") && doubleNumber[n]!=0) {
          total = total / doubleNumber[n];
        } else if (operatorList[n].contains("/") && doubleNumber[n]==0) {
          System.out.println("0で除算をすることはできません。やり直してください");
          caluculation();
        }
      }
      int integer = 0;
      if (total % 1 == 0) {
        integer = (int) total;
        System.out.println(integer);
      } else {
        System.out.println(total);
      }

      //もう一度計算させる
      caluculation();
    }

 }


