package ua.lpnuai.oop.gordon06;

import ua.lpnuai.oop.gordon04.Container;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Container<Integer> mas = new Container<>();

        long[] resOneSum = new long[1];
        long[] resMin = new long[1];
        long[] resMax = new long[1];

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt")))) {
            String strLine;
            int i = 0;

            while ((strLine = reader.readLine()) != null){
                mas.add(Integer.parseInt(strLine));
                i++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter limit");

        double n = scanner.nextDouble();

        Thread oneSum = new Thread(() -> sum(mas, resOneSum));

        Thread minSum = new Thread(()-> min(mas, resMin));

        Thread maxSum = new Thread(()-> max(mas, resMax));

        Thread pramiy = new Thread(()->{
            sum(mas, resOneSum);

            min(mas, resMin);

            max(mas, resMax);

        });

        long nowTime = System.nanoTime();

        oneSum.start();
        minSum.start();
        maxSum.start();

        if(n == 0){

            try {
                oneSum.join();
                minSum.join();
                maxSum.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println((System.nanoTime() - nowTime)/1000000 + "ms");

            System.out.println(resOneSum[0]);
            System.out.println(resMin[0]);
            System.out.println(resMax[0]);

            nowTime = System.nanoTime();

            pramiy.start();

            try {
                pramiy.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println((System.nanoTime() - nowTime)/1000000 + "ms");

        }
        else
        {
            try {
                Thread.sleep((long)(n * 1000));
                if(oneSum.isAlive() || minSum.isAlive() || maxSum.isAlive())
                {
                    oneSum.interrupt();
                    minSum.interrupt();
                    maxSum.interrupt();
                }
                    System.out.println(resOneSum[0]);
                    System.out.println(resMin[0]);
                    System.out.println(resMax[0]);



                System.out.println((System.nanoTime() - nowTime)/1000000 + "ms");
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }


    }

    private static void sum(Container<Integer> mas, long[] resOneSum){
        for (int i = 0; i < 1000; i++)
        {
            for(int j = i+1; j < 1000; j++){
                resOneSum[0] += (mas.get(i) + mas.get(j));
            }
        }
    }

    private static void max(Container<Integer> mas, long[] resMax) {
        for(int i = 0; i < 1000; i++){
            for(int j = i + 1; j < 1000; j++){
                long tmp = mas.get(i) + mas.get(j);

                if(resMax[0] < tmp) resMax[0] = tmp;
            }
        }
    }

    private static void min(Container<Integer> mas, long[] resMin) {
        for(int i = 0; i < 1000; i++){
            for(int j = i + 1; j < 1000; j++){
                long tmp = mas.get(i) + mas.get(j);

                if(resMin[0] > tmp) resMin[0] = tmp;
            }
        }
    }
}
