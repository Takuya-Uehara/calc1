import java.util.Scanner;

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

      //演算子を探す&終了キーを探す

      String[] numberList =formula.split("[¥+¥-¥*/]");
      System.out.println(numberList[1]);

      int order = 0;
      int calc = 0;
      if (formula.contains("+")) {
        order = formula.indexOf("+");
        calc = 1;
      } else if (formula.contains("-")) {
        order = formula.indexOf("-");
        calc = 2;
      } else if (formula.contains("*")) {
        order = formula.indexOf("*");
        calc = 3;
      } else if (formula.contains("/")) {
        order = formula.indexOf("/");
        calc = 4;
      } else if (formula.contains("end")) {
        System.exit(0);
      } else {
        System.out.println("演算子が検出されません。数式を確認してください");
        caluculation();
      }

      //演算子の左右で入力を文字列として抽出する
      String input1 = formula.substring(0, order);
      String input2 = formula.substring(order + 1);

      //抽出した文字列を計算可能な型に変換する
      double newInput1 = Double.parseDouble(input1);
      double newInput2 = Double.parseDouble(input2);

      //計算する
      double result = 0;
      if (calc == 1) {
        result = newInput1 + newInput2;
      } else if (calc == 2) {
        result = newInput1 - newInput2;
      } else if (calc == 3) {
        result = newInput1 * newInput2;
      } else if (calc == 4 && newInput2 != 0) {
        result = newInput1 / newInput2;
      } else if (calc == 4 && newInput2 == 0) {
        System.out.println("0で除算をすることはできません。やり直してください");
        caluculation();
      }

      //計算結果が整数の場合、int型に直して結果を表示する
      int integer = 0;
      if (result % 1 == 0) {
        integer = (int) result;
        System.out.println(integer);
      } else {
        System.out.println(result);
      }

      //もう一度計算させる
      caluculation();
    }

}
