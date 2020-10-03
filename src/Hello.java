import java.util.*;
import java.text.*;

public class Hello {

  public static void main(String[] args) {

    //起動時説明を出力して、計算式を入力させる
    System.out.println("数式を入力してください。(例)1+1");
    System.out.println("プログラムを終了したい場合、endと書いてください。");
    input();

  }

  //計算式を入力させて、式を整えるメソッド
  public static void input (){
    Scanner scanner = new Scanner(System.in);
    System.out.print("計算式：");
    String inputFormula = scanner.nextLine();
    System.out.println(inputFormula);
    while (inputFormula.contains("(")) {
      inputFormula = findPriority(inputFormula);
      continue;
    }
    System.out.println("計算結果:"+calculation(inputFormula));
    input();
  }


    //計算を行うメソッド String型の数式を受け取り、String型の数字で返す
    public static String calculation (String formula){

      //endを検出しプログラムを終わらせる
      if (formula.contains("end")) {
        System.exit(0);
      }

      //+-で分割する
      String[] splitFormula =formula.split("[¥+-]");
      //System.out.println(Arrays.deepToString(splitFormula));

      //+-の演算子を記録しておく
      String[] additionAndSubtraction = formula.split("[ 1234567890/¥.*]+");
      //System.out.println(Arrays.deepToString(additionAndSubtraction));

      //分割された中身で掛け算割り算を行う
      Double[] splitTotal = new Double [splitFormula.length];
      for(int a= 0; a<splitFormula.length ;a++) {

        //分割されたから数字のみを抽出
        String[] numberList=splitFormula[a].split("[/¥*]");


        //抽出した数字をdoubleになおす
        double[] doubleNumber = new double[numberList.length];
        for (int i = 0; i < numberList.length; i++) {
          doubleNumber[i] = Double.parseDouble(numberList[i]);
        }

        //数字が一つの場合、処理を中断してsplitTotalに代入
        if (doubleNumber.length==1){
          splitTotal[a]=doubleNumber[0];
          continue;
        }

        //演算子だけを抽出
        String[] operatorList = splitFormula[a].split("[1234567890¥.]+");


        //計算をさせ、結果をsplitTotalに保存
        double semiTotal = doubleNumber[0];
        for (int n=1;n<operatorList.length;n++) {
          if (operatorList[n].contains("*")) {
            semiTotal = semiTotal * doubleNumber[n];
          } else if (operatorList[n].contains("/") && doubleNumber[n]!=0) {
            semiTotal= semiTotal / doubleNumber[n];
          } else if (operatorList[n].contains("/") && doubleNumber[n]==0) {
            System.out.println("0で除算をすることはできません。やり直してください");
            input();
          }
          splitTotal[a]=semiTotal;
        }
      }

      //最終計算を行う
      double total = splitTotal[0];
      for (int p=1 ;p< additionAndSubtraction.length;p++ ){
        if (additionAndSubtraction[p].contains("+")){
          total+=splitTotal[p];
        }else if(additionAndSubtraction[p].contains("-")){
          total-=splitTotal[p];
        }
      }

      //整数を選別し型を変換する
      int integer = 0;
      String answer;
      if (total % 1 == 0) {
        integer = (int) total;
        answer = String.valueOf(integer);
      } else {
        answer = String.valueOf(total);
      }
      return answer;
  }


  //()をセット検出して中身を計算して書き換えるメソッド
     public static String findPriority(String formula) {

         // 括弧の始まりと終わりを対応させて括弧内を抽出
         int start = formula.indexOf("(");
         int startCounter = 1;
         int endCounter = 0 ;
         int x = formula.indexOf("(");;
         while (startCounter > endCounter){
           if (formula.indexOf("(",x+1)<formula.indexOf(")",x+1) && formula.indexOf("(",x+1) != -1){
             x = formula.indexOf("(",x+1);
             startCounter++;
           }else if (formula.indexOf("(",x+1)>formula.indexOf(")",x+1) || formula.indexOf("(",x+1) == -1) {
             x = formula.indexOf(")", x+1);
             endCounter++;
           }
         }
         String changeFormula = formula.substring(start+1, x);

         //抽出した括弧の中にさらに括弧があればもう一度()を探させる
         while (changeFormula.contains("(")){
           changeFormula = findPriority(changeFormula);
         }

         //抽出した式を計算させての前後と組み合わせる
         changeFormula = calculation(changeFormula);
         String beforeFormula = formula.substring(0,start);
         String afterFormula = formula.substring(x+1);
         formula = beforeFormula+changeFormula+afterFormula;


       return formula;
     }

  }


