package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String arr[] = new String[n];

        for(int i=0;i<n;i++){
            arr[i] = sc.nextLine();
        }

//         ["add triangle t1 2 3", "area t1"]

        String ans = executeCommands(arr);

        System.out.println(ans);

//        for(String s: arr){
//            System.out.print(s+",");
//        }

    }

    public static String executeCommands(String commands[]){
        StringBuilder ans = new StringBuilder();
        Map<String,String> commandsHistory = new HashMap<>();
        Map<String,Param> commandParams = new HashMap<>();

        for(String command : commands){
            String parameters[] = command.split(" ");
            if(parameters[0].equals("add")){
                if(!commandsHistory.containsKey(parameters[2])) {
                    commandsHistory.put(parameters[2], parameters[1]);
                    commandParams.put(parameters[2], new Param(Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4])));
                }
            }
            if(parameters[0].equals("area")){
                if(!commandsHistory.containsKey(parameters[1])){
                    ans.append("error,");
                    continue;
                }
                String area="";
                Param param = commandParams.get(parameters[1]);
                if(commandsHistory.get(parameters[1]).equals("rectangle")){
                    area = String.valueOf(areaOfRectangle(param.getParam1(),param.getParam2()));
                }else{
                    area = String.valueOf(areaOfTriangle(param.getParam1(),param.getParam2()));
                }
                ans.append(area+",");
            }
        }

        return ans.deleteCharAt(ans.length()-1).toString();
    }

    public static int areaOfTriangle(int base, int height){
        return (int) (0.5* base * height);
    }

    public static int areaOfRectangle(int width, int height){
        return width * height;
    }

    static class Param{
        private int param1;
        private int param2;

        public Param(int param1, int param2){
            this.param1=param1;
            this.param2=param2;
        }

        public int getParam1(){
            return  param1;
        }

        public int getParam2(){
            return  param2;
        }
    }
}