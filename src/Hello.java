import java.util.*;

public class Hello {

  public static void main(String[] args) {

    //起動時説明を出力する
    System.out.println("数式を入力してください。(例)1+1");
    System.out.println("プログラムを終了したい場合、endと書いてください。");
    calculation();
  }


    //計算式を入力
    public static void calculation (){
      Scanner scanner = new Scanner(System.in);
      System.out.print("計算式：");
      String formula = scanner.nextLine();
      System.out.println(formula);

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
            calculation();
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
      if (total % 1 == 0) {
        integer = (int) total;
        System.out.println("計算結果:"+integer);
      } else {
        System.out.println("計算結果:"+total);
      }

      calculation();
    }



  }


